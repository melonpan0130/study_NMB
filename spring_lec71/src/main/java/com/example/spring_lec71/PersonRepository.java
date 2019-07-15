package com.example.spring_lec71;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PersonRepository extends PagingAndSortingRepository<Person, Integer> {
	// Method Query
	// http://arahansa.github.io/docs_spring/jpa.html#jpa.query-methods
	
	// email은 unique 제약걸린 칼럼, 따라서 나올 수 있는 Person은 하나 (단수)
	// Java8의 Optional 타입 지정 가능
	public Optional<Person> findByEmail(String email);
	// 함수명대로 쿼리문이 자동으로 작성됨
	// (unique=true)optional 은 person 에서 get 메소드를 사용
	
	// 이름 매개변수 반환타입
	
	// 꼭 Optional을 사용할 필요는 없음 (이 경우 찾는 값이 없으면 null을 반환)
	// public Person findByEmail(String email); -> 이렇게도 쓸 수 있으나 잘 안씀

	// And
	public List<Person> findByFirstNameAndLastName(String first, String last);
	
	// Between
	public List<Person> findByAgeBetween(Integer s, Integer e);
	
	// Like 종류 (EndingWith, StartingWith, Like 등등)
	public List<Person> findByEmailEndingWith(String criteria);
	
	// LessThan, GreaterThan, LessThanEqual, GreaterThanEqual
	// 나이에 따라서 나올 수 있는 사람은 여러 Person (복수)
	public List<Person> findByAgeGreaterThan(Integer criteria);
	public List<Person> findByAgeGreaterThanEqual(Integer criteria);
	
	// @Query 어노테이션의 사용 -- 원하는 쿼리 작성 가능
	@Query("SELECT p FROM Person p WHERE p.email = ?1") 
	// table이름이 아닌 엔티티 클래스이름(Person) 별칭을 꼭 줘야함(p). 필드이름(p.email), ?1은 첫번째 인자. -> 근데 잘 안씀
	public Optional<Person> myFindByEmail(String email);
    
	// 이름 파라미터를 이용 가능 (단, 메소드 전달 값에 @Param 어노테이션 명시 필요)
	/*
	@Query("SELECT p FROM Person p WHERE p.email = :param_email") // -> 이름으로 접근
	public Optional<Person> myFindByEmail(@Param("param_email") String email);
	*/
}