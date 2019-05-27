package com.example.spring_lec5.rest;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice("com.example.spring_lec5.example.rest")
public class RestGlobalExceptionHandler {
	
    @ExceptionHandler(value=Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody Map<String, Object> globalErrorHandler() throws Exception {
	    Map<String, Object> map = new HashMap<String, Object>();
	    map.put("cause", "something");
	    return map;
    }
    
}
