package com.example.spring_lec71;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.lang.Nullable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Setter
@Getter
@ToString
public class Person {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable=false)
	@NonNull
	private String firstName;
	
	@Column(nullable=false)
	@NonNull
	private String lastName;
	
	@Column(nullable=false)
	@NonNull
	private Integer age;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	@NonNull
	private Date birthDay;
	
	@Column(unique=true)
	@Nullable
	private String email;
}