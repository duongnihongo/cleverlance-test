package com.cleverlance.MyAirports.service;

import com.cleverlance.MyAirports.DTO.ResponseObject;

public interface AirportService {
    ResponseObject myAirports(String apiKey);

    ResponseObject getAirportByCode(String code);
}
