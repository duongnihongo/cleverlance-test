package com.cleverlance.MyAirports.controller;

import com.cleverlance.MyAirports.DTO.ResponseObject;
import com.cleverlance.MyAirports.service.PingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/ping/*")
@Api(value = "PING SYSTEM")
public class PingController {
    @Autowired
    PingService pingService;

    @ApiOperation(value = "Get all airports from Database and print out them to console")
    @GetMapping(value = "/ping")
    public ResponseObject printOutAllAirports() {
        return pingService.printOutAllAirports();
    }
}
