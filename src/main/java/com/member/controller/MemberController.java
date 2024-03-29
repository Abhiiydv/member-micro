package com.member.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.member.entity.Member;
import com.member.service.MemberService;

@RestController
@RequestMapping("api/member")
public class MemberController {

	@Autowired
	private MemberService memberServ;

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/details/{username}")
	public Optional<Member> findMemberByUsername(@PathVariable String username) {
		return memberServ.findByUsername(username);

	}
	
	// fetch claim for memberID
	@GetMapping("/claim/member/{id}")
	public Object getClaim(@PathVariable Integer id) {

		Object r = restTemplate.getForObject("http://claim-service//claim/member/" + id, Object.class);
		return r;
	}


}
