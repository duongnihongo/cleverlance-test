package com.cleverlance.MyAirports;

import com.cleverlance.MyAirports.DTO.ResGetAirportByCodeDTO;
import com.cleverlance.MyAirports.DTO.ResponseObject;
import com.cleverlance.MyAirports.common.CommonConfig;
import com.cleverlance.MyAirports.entity.Airport;
import com.cleverlance.MyAirports.repository.AirportRepository;
import com.cleverlance.MyAirports.service.AirportService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AirportServiceTest {
    @Autowired
    AirportService airportService;

    @MockBean
    AirportRepository airportRepository;

    @Autowired
    CommonConfig commonConfig;

    @Test
    public void testGetAirportByCode() {
        //Setup
        ResponseObject found = new ResponseObject();
        Mockito.when(airportRepository.getAirportByCode("CDG")).thenReturn(new Airport("CDG", "Charles De Gaulle"));

        // Test
        // Case 1: Null or Empty parameter CODE
        found = airportService.getAirportByCode(null);
        assertEquals(400, found.getResponseCode());
        found = airportService.getAirportByCode("");
        assertEquals(400, found.getResponseCode());
        // Case 2: Valid Parameter CODE
        found = airportService.getAirportByCode("CDG");
        ResGetAirportByCodeDTO foundAirport = (ResGetAirportByCodeDTO) found.getResponseObject();
        assertEquals("CDG", foundAirport.getCode());
        // Case 3: No match airport
        found = airportService.getAirportByCode("ABC");
        assertEquals(300, found.getResponseCode());
    }

    @Test
    public void testMyAirports() {
        // Setup
        ResponseObject found = new ResponseObject();
        String apiKey = commonConfig.getApiKey();

        // Test
        // Case 1: Null or Empty parameter CODE
        found = airportService.MyAirports(null);
        assertEquals(400, found.getResponseCode());
        found = airportService.MyAirports("");
        assertEquals(400, found.getResponseCode());
        // Case 2: Valid apiKey
        found = airportService.MyAirports(apiKey);
        List<Airport> airportList = (List<Airport>) found.getResponseObject();
        assertTrue(airportList.size() > 0);
        airportRepository.deleteAll();
    }

    @Test
    public void testGetListAirportsFromClientService() {
        // Setup
        List<Airport> airportList = new ArrayList<>();
        String apiKey = commonConfig.getApiKey();
        // Test
        // Case 1: Empty apiKey
        airportList = airportService.getListAirportsFromClientService(null);
        assertEquals(0, airportList.size());
        airportList = airportService.getListAirportsFromClientService("");
        assertTrue(airportList.size() == 0);
        // Case 2: Valid apiKey
        airportList = airportService.getListAirportsFromClientService(apiKey);
        assertTrue(airportList.size() > 0);
    }
}
