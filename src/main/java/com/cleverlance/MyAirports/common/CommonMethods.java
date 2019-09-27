package com.cleverlance.MyAirports.common;

import com.cleverlance.MyAirports.DTO.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommonMethods {
    @Autowired
    Messages messages;

    public ResponseObject responseObjectBuilder(int code, String message, Object object) {
        ResponseObject responseObject = new ResponseObject();
        responseObject.setResponseCode(code);
        responseObject.setResponseMessage(message);
        responseObject.setResponseObject(object);
        return responseObject;
    }
}
