package com.ws.util;

import com.ws.model.dto.AppResponse;

import static com.ws.util.ResponseEnum.OK;

public class Utils {


    public static AppResponse buildReponse(Long id){

        return  AppResponse.builder().id(id).success(Boolean.TRUE).cause(OK.getMsg()).build();
    }

    public static AppResponse buildReponseError(Object msg){
        return  AppResponse.builder().id(null).success(Boolean.FALSE).cause(msg).build();
    }
}
