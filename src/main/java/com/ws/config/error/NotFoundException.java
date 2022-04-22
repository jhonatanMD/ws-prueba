package com.ws.config.error;


public class NotFoundException extends RuntimeException{

    private static final String DESCRIPTION = "NOT FOUND (404)";

    public NotFoundException(String detail){
        super(DESCRIPTION + " . "+detail);
    }
}
