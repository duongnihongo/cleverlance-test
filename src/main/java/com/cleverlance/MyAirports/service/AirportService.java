package com.cleverlance.MyAirports.service;

import com.cleverlance.MyAirports.DTO.ResponseObject;
import com.cleverlance.MyAirports.entity.Airport;

import java.util.List;

public interface AirportService {
    ResponseObject MyAirports(String apiKey);

    ResponseObject getAirportByCode(String code);

    List<Airport> getListAirportsFromClientService(String apiKey);
}
