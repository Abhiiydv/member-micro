package com.member.service;

import java.util.List;
import java.util.Optional;

import com.member.entity.Member;

public interface AdminService {

	Member saveMember(Member member);

	List<Member> getAllMembers();

	Optional<Member> findByUsername(String userName);

	Optional<Member> findMemberById(Long id);

	List<Member> getAllMembersByPhysicianId(Integer pId);

	List<Member> findMembersbyFirstLastname(String first_name, String last_name);

    Optional<Member> findMemberByEmail(String email);
	
	Boolean ifExistsingMember(String email);
	
	// List<Member> findMembersByPhysicianName(String pName);
}