package com.cleverlance.MyAirports.service;

public interface AirlabsService {
    /** Service to send HTTP request to get Airport List from Airlabs.co by Api-key
     *
     * @param apiKey
     * @return String
     */
    String getListAirportsFromClientService(String apiKey);
}
