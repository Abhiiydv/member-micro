package com.member.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.member.entity.Member;
import com.member.entity.Physician;
import com.member.repository.MemberRepository;
import com.member.repository.Physician_Repository;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	private MemberRepository memberRepo;
	
	@Autowired
	private Physician_Repository physicianRepo;
	
	/*
	 * @Override public Member saveMemberDetails(Member member) { // TODO
	 * Auto-generated method stub List<Physician> list = physicianRepo.findAll();
	 * 
	 * member.setPhysician_id(1 + (int)(Math.random() * ((list.size() - 1) + 1)));
	 * member.setCreatedTime(LocalDateTime.now());
	 * member.setModification_date(LocalDateTime.now());
	 * 
	 * return memberRepo.save(member); }
	 */
	@Override
	public Optional<Member> findByUsername(String userName) {
		// TODO Auto-generated method stub
		return memberRepo.findByUsername(userName);
	}

	@Override
	public Optional<Member> findMemberById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}