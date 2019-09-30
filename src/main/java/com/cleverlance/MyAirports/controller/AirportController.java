package com.cleverlance.MyAirports.controller;

import com.cleverlance.MyAirports.DTO.ResponseObject;
import com.cleverlance.MyAirports.service.AirportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/airport/*")
@Api(value = "AIRPORT MANAGEMENT")
public class AirportController {
    @Autowired
    AirportService airportService;

    @ApiOperation(value = "Get airport data from Airlabs.co and save it to Database")
    @GetMapping(value = "/myAirports")
    public ResponseObject myAirports(@RequestParam(name = "apiKey") String apiKey) {
        return airportService.myAirports(apiKey);
    }

    @ApiOperation(value = "Get specific airport by its code")
    @GetMapping(value = "/getAirportByCode")
    public ResponseObject getAirportByCode(@RequestParam(name = "code") String code) {
        return airportService.getAirportByCode(code);
    }
}
