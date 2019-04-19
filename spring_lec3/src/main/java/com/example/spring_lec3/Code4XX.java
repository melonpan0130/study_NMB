package com.example.spring_lec3;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Code4XX {
    @RequestMapping("/c400")
    public void c400(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String secret = request.getParameter("secret");
        
        if(secret != null && secret.equals("1234")) {
            response.setStatus(200);
            //response.setContentType("text/plain");
            response.setContentType("text/html");
            response.setCharacterEncoding("utf-8");
            
            // HTML 태그로 인식하지 않음? 왜?
            response.getWriter().append("<h1>Perfect!</h1>"); // html이 아닌 plan이 됨
        } else {
            response.setStatus(400);
            response.setContentType("application/xml");
            response.setCharacterEncoding("utf-8");
            
            // 에러 해결 방법에 대한 메시지 전달 
            response.getWriter().append("<?xml version=\"1.0\" encoding=\"utf-8\" ?><cause>You missed a secret. (hint : 1234)</cause>");
        }
        
        return;
    }
}
