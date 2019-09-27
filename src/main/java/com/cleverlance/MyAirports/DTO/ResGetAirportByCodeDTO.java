package com.cleverlance.MyAirports.DTO;

public class ResGetAirportByCodeDTO {
    private String code;
    private String name;

    public ResGetAirportByCodeDTO(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public ResGetAirportByCodeDTO() {
        super();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
