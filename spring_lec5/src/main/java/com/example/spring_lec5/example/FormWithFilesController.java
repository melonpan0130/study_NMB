package com.example.spring_lec5.example;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

class FormData implements Serializable {
    @NotBlank(message="이름은 필수 입력 사항입니다.")
    private String name;
    
    @NotNull
    @NotBlank(message="이메일은 필수 입력 사항입니다.")
    @Email
    private String email;
    
    @NotNull
    @Pattern(regexp="\\d{11}", message="11자리 폰 번호를 입력해주세요. (숫자만 입력)")
    private String phoneNumber;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    @Override
    public String toString() {
        return "FormData [name=" + name + ", email=" + email + ", phoneNumber=" + phoneNumber + "]";
    }
}

@Controller
public class FormWithFilesController {
    @GetMapping("/apply")
    public String getMemberJoinPage(FormData d) {
        return "apply";
    }
    
    @PostMapping(value="/apply_form", produces="text/plain")
    public String postApplyForm(@Valid FormData d, BindingResult result, @RequestParam("files") MultipartFile[] files, Model m) {
        if(result.hasErrors()) {
            return "apply";
        }

        m.addAttribute("data", d);
        m.addAttribute("files", files);
        
        return "apply_result";
    }
}