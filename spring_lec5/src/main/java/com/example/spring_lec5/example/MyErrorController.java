package com.example.spring_lec5.example;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyErrorController implements ErrorController{
	
    private static final String ERROR_PATH = "/error";
    
    @RequestMapping(value=ERROR_PATH)
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        System.out.println(status);
        if(status != null) {
            String code = String.valueOf(status);
            System.out.println("status code : " + code);
            
            // 에러 코드 별로 주어진 에러 템플릿을 활용
            if(code.equals("404") || code.equals("500") || code.equals("503")) {
                return "/myerror/" + code;
            }
        }
        
        // 지원하는 에러 코드 외 에러 상황 발생 시 기본 에러 페이지를 출력
        return "/myerror/error";
    }
    
    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}
