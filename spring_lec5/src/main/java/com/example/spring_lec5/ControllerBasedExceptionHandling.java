package com.example.spring_lec5;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.cert.CertificateEncodingException;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.URIReferenceException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import jdk.internal.org.xml.sax.SAXException;

@Controller
public class ControllerBasedExceptionHandling {
	// 메소드 레벨에서 예외 처리 (일종의 컨트롤러 기반 에러 처리)
    @GetMapping("/error_handle_from_method")
    @ResponseBody
    public String errorHandleFromMethod(@RequestParam("num") Integer num, HttpServletResponse response) throws Exception {
        try {
            return "계산 결과 : " + (10 / num);
        } catch(Exception e) {
            response.setStatus(400);
            return "잘못된 파라미터 전달";
        }
    }
    
    @GetMapping("/error_make")
    @ResponseBody
    public String doMakeError() throws Exception {
        ArrayList<Exception> es = new ArrayList<>();
        
        es.add(new CertificateEncodingException());
        es.add(new URIReferenceException());
        es.add(new SAXException());
        
        // 아무 예외나 출력        
        throw es.get(new Random().nextInt(es.size()));
    }
    
    @GetMapping("/error_io")
    @ResponseBody
    public String doIO() throws IOException {
        FileInputStream fis = new FileInputStream("nonono.txt");
        fis.close();
        
        return "...";
    }
    
    @GetMapping("/error_classcast")
    @ResponseBody
    public String doClassCast() {
        Object o = "Hello";
        return ((Integer) o) + "";
    }
    
    @GetMapping("/error_arithmetic")
    @ResponseBody
    public String doArithmetic() {
        return (10 / 0) + "";
    }
    
    @ExceptionHandler({ ArithmeticException.class, ClassCastException.class })
    // 돌려줄 HTTP 코드 (에러와 관련된 코드 권장, 생략하면 200 OK가 반환되므로 주의!)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String arithmeticAndClassCastError(Model m, Exception ex) {
    	System.out.println();
        m.addAttribute("cause", ex.getMessage());
        
        // 에러가 일어났을 때 이동할 뷰 지정
        if(ex instanceof ArithmeticException) {
            return "myerror/error_arithmetic";
        } else {
            return "myerror/error_classcast";
        }
    }
    
    // 그 외의 모든 예외 처리 (예외 클래스가 Exception임을 주목)
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String allError(Model m, Exception ex) {
        System.out.println(ex);
        m.addAttribute("cause", ex.getClass().toString());
        
        return "/myerror/error_all";
    }
}
