package com.cleverlance.MyAirports.controller;

import com.cleverlance.MyAirports.DTO.ResponseObject;
import com.cleverlance.MyAirports.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/airport/*")
public class AirportController {
    @Autowired
    AirportService airportService;

    @GetMapping(value = "/myAirports")
    public ResponseObject myAirports(@RequestParam(name = "apiKey") String apiKey) {
        return airportService.myAirports(apiKey);
    }

    @GetMapping(value = "/getAirportByCode")
    public ResponseObject getAirportByCode(@RequestParam(name = "code") String code) {
        return airportService.getAirportByCode(code);
    }
}
