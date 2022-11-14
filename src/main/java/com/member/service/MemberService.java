package com.member.service;

import java.util.Optional;

import com.member.entity.Member;

public interface MemberService {

   // Member saveMemberDetails(Member member);
	
	Optional<Member> findByUsername(String userName);
	
	Optional<Member> findMemberById(Long id);
	
}