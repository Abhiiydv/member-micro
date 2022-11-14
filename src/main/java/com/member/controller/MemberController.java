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

import com.member.entity.Member;
import com.member.service.MemberService;

@RestController
@RequestMapping("api/member")
public class MemberController {

	@Autowired
	private MemberService memberServ;
	
	@GetMapping("/test")
	public String greetings() {
		return "Hello, I am member";
	}
	//save member details
	
	@PostMapping("/create")
	public ResponseEntity<Member> saveMemberDetails(@RequestBody Member member) {
		if (memberServ.findByUsername(member.getUsername()).isPresent())
        {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
            //we will use this code to show alert for already exists errorcode409
        }
        return new ResponseEntity<>(memberServ.saveMemberDetails(member), HttpStatus.CREATED);
		
	}
	
	@GetMapping("/details/{username}")
	public Optional<Member> findMemberByUsername(@PathVariable String username)
	{
		return memberServ.findByUsername(username);
		
	}
	//submit claim
	
	//fetch claim
	
	//fetch member details
	
}
