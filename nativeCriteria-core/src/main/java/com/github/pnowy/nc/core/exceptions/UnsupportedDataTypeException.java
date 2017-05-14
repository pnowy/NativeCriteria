package com.github.pnowy.nc.core.exceptions;

public class UnsupportedDataTypeException extends RuntimeException {

    private String type;

    public UnsupportedDataTypeException(String message, String type) {
        super(message);
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
