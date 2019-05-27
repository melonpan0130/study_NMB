package com.example.spring_lec5.rest;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@Controller("MyRestController")//bean 이름 지정(중복 방지)
@RequestMapping("/rest")
public class MyController {
	@GetMapping("/error_artithemetic")
	@ResponseBody
	public String doArithmetic() {
		return (10/0)+"";
	}
}