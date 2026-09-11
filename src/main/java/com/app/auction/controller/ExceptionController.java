package com.app.auction.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.app.auction.dto.BaseResponse;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class ExceptionController {
    
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> runtimeException(RuntimeException ex) {
        BaseResponse out = new BaseResponse();
        String[] exMessage = ex.getMessage().split(":");
        out.setResponseCode(exMessage[0]);
        out.setResponseDesc(exMessage[1]);
        return ResponseEntity.internalServerError().body(out);
    }
    
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<Object> illegalStateException(IllegalStateException ex) {
        BaseResponse out = new BaseResponse();
        String[] exMessage = ex.getMessage().split(":");
        out.setResponseCode(exMessage[0]);
        out.setResponseDesc(exMessage[1]);
        return ResponseEntity.badRequest().body(out);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> exception(Exception ex) {
        BaseResponse out = new BaseResponse();
        out.setResponseCode("99");
        out.setResponseDesc("System Error");
        return ResponseEntity.internalServerError().body(out);
    }

}
