package com.example.spring_lec71;

import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member, Integer> {}