package com.example.spring_lec3;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Code3XX {
	@RequestMapping("/c301")
	public String c301(HttpServletResponse response) {
	    response.setStatus(301);
	    response.setHeader("Location", "http://www.naver.com");
	    
	    // 일반적으로 redirect 응답에는 바디를 포함하지 않음 (그러나 금지는 아님)
	    // https://stackoverflow.com/questions/8059662/http-302-redirect-is-a-message-body-needed
	    return "Moved Permanently";
	}
}