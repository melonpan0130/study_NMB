package com.example.spring_lec5.normal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice("com.example.spring_lec5.example.normal")
public class NormalGlobalExceptionHandler {
    @ExceptionHandler(value=Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String globalErrorHandler(Model m, HttpServletRequest req, Exception ex) throws Exception {
        System.out.println("from Global error handler");
        
        m.addAttribute("cause", ex.getMessage());
        
        return "/myerror/error_global";
    }
}
