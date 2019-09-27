package com.cleverlance.MyAirports.DTO;

public class ResponseObject {
    private int responseCode;
    private String responseMessage;
    private Object responseObject;

    public ResponseObject(int responseCode, String responseMessage, Object responseObject) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.responseObject = responseObject;
    }

    public ResponseObject() {
        super();
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public Object getResponseObject() {
        return responseObject;
    }

    public void setResponseObject(Object responseObject) {
        this.responseObject = responseObject;
    }
}
