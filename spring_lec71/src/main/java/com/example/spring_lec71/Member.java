package com.example.spring_lec71;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(unique = true, nullable = false)
	@NonNull
	private String username;
	
	@Column(nullable = false)
	@NonNull
	private String password;
	
	@Column(nullable = false)
	private String name;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
}
