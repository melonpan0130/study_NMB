package com.example.spring_lec3;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Code2XX {
	private Integer percent = 0;
	private Integer id = 1;
	
	@RequestMapping("/c200")
	public String c200(HttpServletResponse response) {
		response.setStatus(200);
		return "OK";
	}

	@RequestMapping("/c201")
	public String c201(HttpServletResponse response) {
		response.setStatus(201);
		// 3XX 코드가 아니므로 브라우저 상 리다이렉션은 일어나지 않으나 응답 헤더에는 포함됨
		response.setHeader("Location", "http://www.naver.com");
		// naver로 이동하지 않음
		return "Created";
	}

	@RequestMapping("/c202")
	public void c202(HttpServletResponse response) throws IOException {
	    response.setStatus(202);
	    // response.setContentType("application/json");
	    response.setCharacterEncoding("utf-8");
	    
	    response.setHeader("Content-Type", "application/json; charset=utf-8");
	    response.getWriter().append("{ \"url\": \"/async_job/" + id + "\" }");
	    id++;

	    new Thread(new Runnable() {
	        @Override
	        public void run() {
	            try {
	                while(true) {
	                    Thread.sleep(1000);
	                    percent += 10;
	                    System.out.println("percent : " + percent);
	                    if(percent == 100) break;
	                }
	            } catch (InterruptedException e) {}
	        }
	    }).start();

	    return;
	}
	
	@RequestMapping("/async_job/{id}")
	public String async_job(@PathVariable Integer id) {
		String ret = percent + "%";
		if(percent == 100) {
			percent = 0;
			ret = id + " 작업 완료";
		}
		return ret;
	}


}
