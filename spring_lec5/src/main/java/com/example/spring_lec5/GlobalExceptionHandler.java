package com.example.spring_lec5;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice("com.example.spring_lec5.example")
public class GlobalExceptionHandler {
	@ExceptionHandler(value=ArithmeticException.class)
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    public String globalArithmeticError(Model m, Exception ex) {
        System.out.println("Global arithmetic error handler");
        
        m.addAttribute("cause", ex.getMessage());
        
        return "/myerror/error_global_arithmetic";
    }
    
    @ExceptionHandler(value=Exception.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String globalErrorHandler(Model m, HttpServletRequest req, Exception ex) throws Exception {
        System.out.println("from Global error handler");
        
        m.addAttribute("cause", ex.getMessage());
        
        return "/myerror/error_global";
    }
}
