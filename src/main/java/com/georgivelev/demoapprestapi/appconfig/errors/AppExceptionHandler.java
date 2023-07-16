package com.georgivelev.demoapprestapi.appconfig.errors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorRepresentationModel> handleUserServiceException(UserServiceException ex, WebRequest webRequest){
        ErrorRepresentationModel response = ErrorRepresentationModel.builder()
                .message(ex.getMessage())
                .date(new Date())
                .build();
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}
