package com.cleverlance.MyAirports.service.serviceImpl;

import com.cleverlance.MyAirports.DTO.ResGetAirportByCodeDTO;
import com.cleverlance.MyAirports.DTO.ResponseObject;
import com.cleverlance.MyAirports.common.CommonMethods;
import com.cleverlance.MyAirports.common.Messages;
import com.cleverlance.MyAirports.entity.Airport;
import com.cleverlance.MyAirports.repository.AirportRepository;
import com.cleverlance.MyAirports.service.AirlabsService;
import com.cleverlance.MyAirports.service.AirportService;
import com.cleverlance.MyAirports.util.Utils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AirportServiceImpl implements AirportService {
    private static final Logger logger = LogManager.getLogger(AirportServiceImpl.class);

    @Autowired
    CommonMethods commonMethods;

    @Autowired
    AirportRepository airportRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    Messages messages;

    @Autowired
    AirlabsService airlabsService;

    @Override
    public ResponseObject myAirports(String apiKey) {
        if (apiKey == null || apiKey.isEmpty()) {
            return commonMethods.responseObjectBuilder(Utils.ResponseCode.FAIL.getValue(), messages.get("request.responseCode.error"), null);
        } else {
            String airlabsResponse = airlabsService.getListAirportsFromClientService(apiKey);
            List<Airport> airportList = parseAirlabsResponseStringToAirportList(airlabsResponse);
            if (!airportList.isEmpty()) {
                airportRepository.saveAll(airportList);
                return commonMethods.responseObjectBuilder(Utils.ResponseCode.SUCCESS.getValue(), messages.get("request.responseCode.success"), airportList);
            } else {
                return commonMethods.responseObjectBuilder(Utils.ResponseCode.EMPTY.getValue(), messages.get("request.responseCode.empty"), airportList);
            }
        }
    }

    private List<Airport> parseAirlabsResponseStringToAirportList(String airlabsResponse) {
        List<Airport> airportList = new ArrayList<>();
        try {
            JsonNode jsonNode = objectMapper.readTree(airlabsResponse);
            if (!airlabsResponse.contains("error")) {
                String responseList = jsonNode.get("response").toString();
                airportList = objectMapper.readValue(responseList, new TypeReference<List<Airport>>() {
                });
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return airportList;
    }

    @Override
    public ResponseObject getAirportByCode(String code) {
        if (code == null || code.isEmpty()) {
            return commonMethods.responseObjectBuilder(Utils.ResponseCode.FAIL.getValue(), messages.get("request.responseCode.error"), null);
        } else {
            Airport airport = airportRepository.getAirportByCode(code.toUpperCase().trim());
            if (airport != null) {
                ResGetAirportByCodeDTO resGetAirportByCodeDTO = modelMapper.map(airport, ResGetAirportByCodeDTO.class);
                return commonMethods.responseObjectBuilder(Utils.ResponseCode.SUCCESS.getValue(), messages.get("request.responseCode.success"), resGetAirportByCodeDTO);
            } else {
                return commonMethods.responseObjectBuilder(Utils.ResponseCode.EMPTY.getValue(), messages.get("request.responseCode.empty"), null);
            }
        }
    }
}
