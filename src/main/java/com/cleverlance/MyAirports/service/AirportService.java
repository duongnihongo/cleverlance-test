package com.cleverlance.MyAirports.service;

import com.cleverlance.MyAirports.DTO.ResponseObject;

public interface AirportService {
    /** Service to receive response data from Airlabs.co API and save them to Database
     *
     * @param apiKey
     * @return Response Object
     */
    ResponseObject myAirports(String apiKey);

    /** Service to get specific Airport from Database by its code
     *
     * @param code
     * @return Response Object
     */
    ResponseObject getAirportByCode(String code);
}
