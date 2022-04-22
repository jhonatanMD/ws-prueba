package com.ws.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class TestUtil {

    private static final ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
    }

    public static <T> String writeDataToJson(T value) throws JsonProcessingException {
        return mapper.writeValueAsString(value);
    }
}
