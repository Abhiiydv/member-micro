package com.member.service;

import java.util.Optional;

import com.member.entity.Member;

public interface AdminService {

	Member saveMember(Member member);
	
	Optional<Member> findByUsername(String userName);
	
	Optional<Member> findMemberById(Long id);
}