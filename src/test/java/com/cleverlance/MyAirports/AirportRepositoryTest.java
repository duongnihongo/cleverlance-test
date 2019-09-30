package com.cleverlance.MyAirports;

import com.cleverlance.MyAirports.entity.Airport;
import com.cleverlance.MyAirports.repository.AirportRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AirportRepositoryTest {
    @Autowired
    AirportRepository airportRepository;

    @Test
    public void getAirportByCode() {
        // Setup
        airportRepository.save(new Airport("CDG", "Charles De Gaulle"));

        // Test
        //Case 1: Valid CODE
        Airport found = airportRepository.getAirportByCode("CDG");
        assertEquals("CDG", found.getCode());
        airportRepository.delete(found);
        // Case 2: CODE is null or empty
        found = airportRepository.getAirportByCode(null);
        assertEquals(null, found);
        found = airportRepository.getAirportByCode("");
        assertEquals(null, found);
    }
}
