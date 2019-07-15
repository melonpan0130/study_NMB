package com.example.spring_lec71;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/persons")
public class PersonController {
	@Autowired PersonRepository repository;
	
	@GetMapping("test1")
	public void test1() {
		System.out.println("\nfindByEmail(\"kim@hello.com\") ------------------------------");
		System.out.println(repository.findByEmail("kim@hello.com").get()); // .get()으로 하면 null일 가능성이 있음
		
		System.out.println("\nfindByAgeGreaterThan(40) ------------------------------");
		for(Person p : repository.findByAgeGreaterThan(40)) {
			System.out.println(p);
		}
		
		System.out.println("\nfindByAgeGreaterThanEqual(40) ------------------------------");
		for(Person p : repository.findByAgeGreaterThanEqual(40)) {
			System.out.println(p);
		}
		
		System.out.println("\nfindByAgeGreaterThanEqual(40) ------------------------------");
		for(Person p : repository.findByAgeGreaterThanEqual(40)) {
			System.out.println(p);
		}
		
		System.out.println("\nfindByAgeBetween(40, 50) ------------------------------");
		for(Person p : repository.findByAgeBetween(40, 50)) {
			System.out.println(p);
		}
		
		System.out.println("\nfindByAgeBetween(20, 40) ------------------------------");
		for(Person p : repository.findByAgeBetween(20, 40)) {
			System.out.println(p);
		}
		
		System.out.println("\nfindByEmailEndingWith(\"hello.com\") ------------------------------");
		for(Person p : repository.findByEmailEndingWith("hello.com")) {
			System.out.println(p);
		}
		
		System.out.println("\nfindByFirstNameAndLastName(\"철수\", \"김\") ------------------------------");
		for(Person p : repository.findByFirstNameAndLastName("철수", "김")) {
			System.out.println(p);
		}
	}
	
	@GetMapping("test2")
	public void test2() {
		System.out.println("\nmyFindByEmail(\"kim@hello.com\") ------------------------------");
		System.out.println(repository.myFindByEmail("kim@hello.com").get());
	}
}