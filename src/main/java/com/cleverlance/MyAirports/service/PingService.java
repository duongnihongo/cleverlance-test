package com.cleverlance.MyAirports.service;

import com.cleverlance.MyAirports.DTO.ResponseObject;

public interface PingService {
    /** Service to get all airports from Database and print out them to console
     *
     * @return Response Object
     */
    ResponseObject printOutAllAirports();
}
