package com.example.spring_lec71;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Entity
//테이블 이름 변경하고 싶은 경우 @Table 어노테이션 사용 가능
//@Table(name="hello_entity")
public class MyEntity {
	enum MyEnum {
		HELLO, WORLD
	}

	enum MyAnotherEnum {
		VALUE1, VALUE2
	}

	// 엔티티를 유일하게 식별하는 주 키(Primary Key)가 반드시 필요 (없으면 No identifier specified for
	// entity 에러 발생)
	@Id
	// AUTO INCREMENT 옵션을 주기 위하여 필요
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	// unique, not null constraint 추가
	@Column(unique = true, nullable = false)
	@NotNull
	private String uniqueStringColumn;

	// name에 전달하는 값으로 직접 칼럼 이름 지정 가능
	@Column(name = "my_int_column")
	private Integer intColumn;

	// 자바의 타입을 쓰면 적절한 대응되는 데이터베이스의 타입으로 칼럼 생성
	@Column
	private Double doubleColumn;

	// Temporal 어노테이션 사용하여 타임존이 포함된 시간을 입력 가능
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateColumn;

	// Enumerated 어노테이션으로 enum 타입값 지정 가능 (단, MySQL에서 지원하는 enum 타입으로 만들지는 않음)
	// @Column(columnDefinition="ENUM('HELLO', 'WORLD')")
	// @Enumerated(EnumType.STRING)
	@Column
	@Enumerated(EnumType.STRING)
	private MyEnum enum1;

	@Column
	@Enumerated(EnumType.ORDINAL)
	private MyAnotherEnum enum2;

	// longtext
	@Lob
	private String lobString;

	// longblob
	@Lob
	private byte[] lobBytes;

	// 테이블 칼럼 생성에서 제외할 멤버 필드는 @Transient 어노테이션 붙이기
	@Transient
	private String transientValue;
}