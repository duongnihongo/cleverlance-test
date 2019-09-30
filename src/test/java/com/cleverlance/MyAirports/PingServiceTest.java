package com.cleverlance.MyAirports;

import com.cleverlance.MyAirports.entity.Airport;
import com.cleverlance.MyAirports.repository.AirportRepository;
import com.cleverlance.MyAirports.service.PingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PingServiceTest {
    @MockBean
    AirportRepository airportRepository;

    @Autowired
    PingService pingService;

    @Test
    public void successShouldLogSuccessMessages() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        // Setup
        List<Airport> airportList = new ArrayList<>();
        airportList.add(new Airport("AT1", "Airport Test 01"));
        airportList.add(new Airport("AT2", "Airport Test 02"));

        // Test
        // Case 1: Valid return
        Mockito.when(airportRepository.findAll()).thenReturn(airportList);
        pingService.printOutAllAirports();
        assertTrue(outContent.toString().contains("Airport Test 01"));
        outContent.reset();
        // Case 2: Return null
        Mockito.when(airportRepository.findAll()).thenReturn(null);
        pingService.printOutAllAirports();
        assertEquals("", outContent.toString());
    }

}
