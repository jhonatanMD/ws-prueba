package com.ws.config;

import com.ws.config.error.DefaultException;
import com.ws.model.dto.AppResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import lombok.extern.slf4j.Slf4j;

import static com.ws.util.Utils.buildReponseError;

@Slf4j
@ControllerAdvice
public class ControllerError extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) ->{

            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });


        return new ResponseEntity<Object>(buildReponseError(errors), HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({
            DefaultException.class,
    })
    @ResponseBody
    public AppResponse defaultException(Exception exception){
        return buildReponseError(exception.getMessage());
    }


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({
            NoSuchElementException.class,
            NullPointerException.class,
    })
    @ResponseBody
    public AppResponse exception(Exception exception){
        return buildReponseError(exception.getMessage());
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            IllegalArgumentException.class
    })
    @ResponseBody
    public AppResponse badRequest(Exception exception){
        return buildReponseError(exception.getLocalizedMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({
          Throwable.class
    })
    @ResponseBody
    public AppResponse serverError(Exception exception){
        return buildReponseError(exception.getLocalizedMessage());
    }

}
