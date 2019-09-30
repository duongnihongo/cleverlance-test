package com.cleverlance.MyAirports.service.serviceImpl;

import com.cleverlance.MyAirports.service.AirlabsService;
import com.cleverlance.MyAirports.util.UrlConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AirlabsServiceImpl implements AirlabsService {
    private static final Logger logger = LogManager.getLogger(AirlabsServiceImpl.class);

    @Override
    public String getListAirportsFromClientService(String apiKey) {
        String response = "";
        try {
            if (apiKey != null && !apiKey.isEmpty()) {
                String uri = UrlConstants.AIRPORT_GET_ALL_URL + apiKey;

                RestTemplate restTemplate = new RestTemplate();
                response = restTemplate.getForObject(uri, String.class);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return response;
    }
}
