package com.cleverlance.MyAirports;

import com.cleverlance.MyAirports.DTO.ResGetAirportByCodeDTO;
import com.cleverlance.MyAirports.DTO.ResponseObject;
import com.cleverlance.MyAirports.common.CommonConfig;
import com.cleverlance.MyAirports.entity.Airport;
import com.cleverlance.MyAirports.repository.AirportRepository;
import com.cleverlance.MyAirports.service.AirportService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyAirportsApplicationTests {
	@Autowired
	AirportService airportService;

	@Autowired
	AirportRepository airportRepository;

	@Autowired
	EntityManager entityManager;

	@Autowired
	CommonConfig commonConfig;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testGetAirportByCode() {
		// given
		Airport cdg = new Airport("CDG", "Charles De Gaulle");
		airportRepository.save(cdg);

		// when
		ResponseObject found = airportService.getAirportByCode(cdg.getCode());
		ResGetAirportByCodeDTO foundAirport = (ResGetAirportByCodeDTO) found.getResponseObject();

		// then
		assertEquals(foundAirport.getCode(), cdg.getCode());

		// clean
		airportRepository.delete(cdg);
	}

	@Test
	public void testMyAirports() {
		String apiKey = commonConfig.getApiKey();
		// when
		ResponseObject found = airportService.myAirports(apiKey);
		List<Airport> airportList = (List<Airport>) found.getResponseObject();

		// then
		assertTrue(airportList.size() > 0);

		airportRepository.deleteAll();
	}

}
