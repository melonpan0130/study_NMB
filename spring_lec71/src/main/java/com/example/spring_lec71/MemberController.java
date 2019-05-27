package com.example.spring_lec71;

import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/members")
public class MemberController {
	
	@Autowired
	private MemberRepository repository;
	@GetMapping("/register")
	public String registerGET() {
	return "member/register";
	}
	
	@PostMapping("/register")
	@ResponseStatus(HttpStatus.CREATED)
	public String registerPOST(Member member, HttpServletResponse response) {
		member.setCreatedAt(new Date());
		repository.save(member);
		return "member/register_success";
	}

}
