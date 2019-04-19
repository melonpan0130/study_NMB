package com.example.spring_lec5;

import java.io.Serializable;
import java.util.ArrayList;

import javax.validation.Valid;
import javax.validation.constraints.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

class Member implements Serializable {
	@NotBlank(message = "공백은 싫어...") // 공백이면 안됨
	@Size(min = 2, max = 10, message="2자 이상 10자 이하로,,, 해줘") // 2~10자 이내이어야 함
	private String name;
	
	@Positive // 양수이어야 함
	@Min(1) // 1 이상
	@Max(130) // 130 이하 
	private int age;
	
	@NotNull // null 불가 ->값이 있어야함
	@NotBlank // null, 빈 문자열, 공백인가? -> 내용이 있어야함
	@Email // 값이 이메일 형식인가??
	private String email;
	
	// 4자 이상 8자 이하이어야함
	@Pattern(regexp = "[a-zA-Z1-9]{4,8}", message = "비밀번호 양식이 틀렸습니다.")
	private String password;
	
	private String gender;
	@AssertTrue(message = "성별은 여성이거나 남성이어야 합니다.")
	public boolean isValidGender() {
		if (gender == null)
			return true;
		return gender.equals("남성") || gender.equals("여성");
	}
	
	private ArrayList<String> hobbies = new ArrayList<String>();
	private ArrayList<String> interests = new ArrayList<String>();
	
	//getters and setters
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	public int getAge() { return age; }
	public void setAge(int age) { this.age = age; }
	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }
	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }
	public String getGender() { return gender; }
	public void setGender(String gender) { this.gender = gender; }
	public ArrayList<String> getHobbies() { return hobbies; }
	public void setHobbies(ArrayList<String> hobbies) { this.hobbies = hobbies; }
	public ArrayList<String> getInterests() { return interests; }
	public void setInterests(ArrayList<String> interests) { this.interests = interests; }
	
	@Override
	public String toString() {
		return "Member [name=" + name + ", age=" + age + ", email=" + email + ", password=" + password + ", gender="
				+ gender + ", hobbies=" + hobbies + ", interests=" + interests + "]";
	}
	
}

@Controller
public class ValidationJController {
	// 커맨드 오브젝트 => Form-backing bean
	@GetMapping("/member_register")
	public String getMemberJoinPage(Member m) {
		// member는 카멜케이스 타입만 가져옴
		return "member_register";
	}
	
	@PostMapping("/member")
	// @Valid 유효성 검사를 함, BindingResult는 그 결과를 받아줌
	public String addMember(@Valid Member member, BindingResult result, Model model) {
		// BindingResult의 hasErrors 메소드 사용하여 에러 여부 확인
		if (result.hasErrors()) { // 에러가 하나라도 있으면 돌려줌; 다시 써!
			return "member_register"; // 다시 가!; 다시 폼이 있는 템플릿을 돌려줌 
			// (오류 값이 포함된 Form-backing bean이 다시 전달됨)
		}
		// 회원 가입 로직 처리 (ex: 데이터베이스에 데이터 삽입)
		return "member_congratulation";
	}
}
