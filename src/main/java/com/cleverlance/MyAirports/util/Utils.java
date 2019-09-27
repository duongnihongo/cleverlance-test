package com.cleverlance.MyAirports.util;

public class Utils {
    public enum ResponseCode {
        SUCCESS(200),
        FAIL(400),
        EMPTY(300);

        private final int value;

        ResponseCode(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }
    }
}
