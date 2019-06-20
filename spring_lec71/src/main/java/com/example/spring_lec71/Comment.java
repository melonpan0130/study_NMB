package com.example.spring_lec71;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@ToString(exclude="article") // 서로 출력하려다 overflow 발생
@Setter
@Getter
public class Comment {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer cid;
	
	@Column(nullable=false)
	@NonNull
	@NotBlank
	private String writer;
	
	@Column(nullable=false)
	@NonNull
	private String content;
	
	@ManyToOne
	@JoinColumn(name="article_id") // 외래키column 이름
	private Article article;
}