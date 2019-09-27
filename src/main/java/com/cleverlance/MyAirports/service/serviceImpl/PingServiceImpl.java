package com.cleverlance.MyAirports.service.serviceImpl;

import com.cleverlance.MyAirports.DTO.ResponseObject;
import com.cleverlance.MyAirports.common.CommonMethods;
import com.cleverlance.MyAirports.common.Messages;
import com.cleverlance.MyAirports.entity.Airport;
import com.cleverlance.MyAirports.repository.AirportRepository;
import com.cleverlance.MyAirports.service.PingService;
import com.cleverlance.MyAirports.util.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PingServiceImpl implements PingService {
    private static final Logger logger = LogManager.getLogger(PingServiceImpl.class);

    @Autowired
    AirportRepository airportRepository;

    @Autowired
    CommonMethods commonMethods;

    @Autowired
    Messages messages;

    @Override
    public ResponseObject printOutAllAirports() {
        List<Airport> airportList = airportRepository.findAll();
        if (!airportList.isEmpty()) {
            logger.info(airportList.toString());
        }
        return commonMethods.responseObjectBuilder(Utils.ResponseCode.SUCCESS.getValue(), messages.get("request.responseCode.success"), airportList.size());
    }
}
