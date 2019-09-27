package com.cleverlance.MyAirports.controller;

import com.cleverlance.MyAirports.DTO.ResponseObject;
import com.cleverlance.MyAirports.service.PingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/ping/*")
public class PingController {
    @Autowired
    PingService pingService;

    @GetMapping(value = "/ping")
    public ResponseObject printOutAllAirports() {
        return pingService.printOutAllAirports();
    }
}
