package com.cleverlance.MyAirports;

import com.cleverlance.MyAirports.DTO.ResGetAirportByCodeDTO;
import com.cleverlance.MyAirports.DTO.ResponseObject;
import com.cleverlance.MyAirports.common.CommonConfig;
import com.cleverlance.MyAirports.entity.Airport;
import com.cleverlance.MyAirports.repository.AirportRepository;
import com.cleverlance.MyAirports.service.AirlabsService;
import com.cleverlance.MyAirports.service.AirportService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

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

    @MockBean
    AirlabsService airlabsService;

    @Autowired
    CommonConfig commonConfig;

    static final String AIRLABS_RESPONSE_OK_MOCK = "{\"response\":[{\"code\":\"AAA\",\"name\":\"Anaa\"},{\"code\":\"AAB\",\"name\":\"Arrabury\"}]}";
    static final String AIRLABS_RESPONSE_ERROR_MOCK = "{\"error\":{\"message\":\"unknown api_key\"}}";

    @Before
    public void setupMock() {
        Mockito.when(airportRepository.getAirportByCode("CDG")).thenReturn(new Airport("CDG", "Charles De Gaulle"));
    }

    @Test
    public void testGetAirportByCode_whenCodeNull_thenResponse400() {
        ResponseObject found = airportService.getAirportByCode(null);
        assertEquals(400, found.getResponseCode());
    }

    @Test
    public void testGetAirportByCode_whenCodeEmpty_thenResponse400() {
        ResponseObject found = airportService.getAirportByCode("");
        assertEquals(400, found.getResponseCode());
    }

    @Test
    public void testGetAirportByCode_whenCodeValid_thenResponseAirport() {
        ResponseObject found = airportService.getAirportByCode("CDG");
        ResGetAirportByCodeDTO foundAirport = (ResGetAirportByCodeDTO) found.getResponseObject();
        assertEquals("CDG", foundAirport.getCode());
    }

    @Test
    public void testGetAirportByCode_whenCodeWrong_thenResponseEmpty() {
        ResponseObject found = airportService.getAirportByCode("ABC");
        assertEquals(300, found.getResponseCode());
    }

    @Test
    public void testMyAirports_whenApiKeyNull_thenResponse400() {
        // Test
        ResponseObject found = airportService.myAirports(null);
        assertEquals(400, found.getResponseCode());

    }

    @Test
    public void testMyAirports_whenApiKeyEmpty_thenResponse400() {
        // Test
        ResponseObject found = airportService.myAirports("");
        assertEquals(400, found.getResponseCode());
    }

    @Test
    public void testMyAirports_whenApiKeyValid_thenResponseAirpostList() {
        Mockito.when(airlabsService.getListAirportsFromClientService(commonConfig.getApiKey())).thenReturn(AIRLABS_RESPONSE_OK_MOCK);

        ResponseObject found = airportService.myAirports(commonConfig.getApiKey());
        List<Airport> airportList = (List<Airport>) found.getResponseObject();
        assertTrue(airportList.size() > 0);
    }

    @Test
    public void testMyAirports_whenApiKeyInvalid_thenResponseEmptyAirpostList() {
        Mockito.when(airlabsService.getListAirportsFromClientService("ABC")).thenReturn(AIRLABS_RESPONSE_ERROR_MOCK);

        ResponseObject found = airportService.myAirports("ABC");
        List<Airport> airportList = (List<Airport>) found.getResponseObject();
        assertTrue(airportList.size() == 0);
    }
}
