package com.example.spring_lec5.example;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/*
@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="No Odd Number Allowed.")
class NoOddNumberException extends Exception {
    public NoOddNumberException(int num) {
        super(num + "은 홀수입니다.");
    }
}

@RestController
public class MyController {
	@GetMapping("/no_odd")
	public String noOdd(@RequestParam("num") Integer num) throws NoOddNumberException{
		if(num % 2 == 0) {
			return "good";
		}
		else {
			// throw new NoOddNumberException(num);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No odd number allowed. (inserted value : "+num+")");
		}
	}

}
*/