package com.member.service;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.member.entity.Member;
import com.member.repository.MemberRepository;


@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberRepository memberRepo;



	@Override
	public Optional<Member> findByUsername(String userName) {
		// TODO Auto-generated method stub
		return memberRepo.findByUsername(userName);
	}

	
}