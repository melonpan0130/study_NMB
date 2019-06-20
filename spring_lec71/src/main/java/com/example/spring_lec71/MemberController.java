package com.example.spring_lec71;

import java.util.Date;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/members")
public class MemberController {
	
	@Autowired
	private MemberRepository repository;
	@GetMapping("/register")
	public String registerGET() { // 커멘드 객체 -> 폼 객체
	return "member/register";
	}
	
	@PostMapping("/register")
	@ResponseStatus(HttpStatus.CREATED)
	public String registerPOST(Member member, HttpServletResponse response) {
		member.setCreatedAt(new Date());
		repository.save(member);
		return "member/register_success";
	}
	
	@GetMapping("/{id}")
	public String getMember(@PathVariable("id") Integer id, Model m) {
		Optional<Member> member = repository.findById(id);
		m.addAttribute("member", member.isPresent() ? member.get() : null);
		return "member/status";
	}

	@GetMapping("/{id}/modify")
	public String getModifyMember(@PathVariable("id") Integer id, Model m) {
		Optional<Member> member = repository.findById(id);
		m.addAttribute("member", member.isPresent() ? member.get() : null);
		return "member/modify";
	}

	@PutMapping("/{id}")
	@ResponseBody
	public String modifyMember(@PathVariable("id") Integer id, Member m, HttpServletResponse response) {
		if (repository.existsById(id)) {
			repository.save(m);
			return "성공";
		} else {
			response.setStatus(404);
			return "회원 미존재";
		}
	}

	@GetMapping("/{id}/delete")
	public String getDeleteMember(@PathVariable("id") Integer id, Model m) {
		Optional<Member> member = repository.findById(id);
		m.addAttribute("member", member.isPresent() ? member.get() : null);
		return "member/delete";
	}
	
	@DeleteMapping("/{id}")
	@ResponseBody
	public String deleteMember(@PathVariable("id") Integer id, HttpServletResponse response) {
		if (repository.existsById(id)) {
			repository.deleteById(id);
			return "성공";
		} else {
			response.setStatus(404);
			return "회원 미존재";
		}
	}

	@GetMapping("")
	public String getMemberList(Model m) {
		m.addAttribute("count", repository.count());
		m.addAttribute("members", repository.findAll());
		return "member/list";
	}

}
