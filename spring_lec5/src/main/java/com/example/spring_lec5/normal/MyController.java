package com.example.spring_lec5.normal;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@Controller("MyNormalController")
@RequestMapping("/normal")
public class MyController {
	// localhost:1234/normal/error_artithemetic
	@GetMapping("/error_artithemetic")
	@ResponseBody
	public String doArithmetic() {
		return (10/0)+"";
	}
}