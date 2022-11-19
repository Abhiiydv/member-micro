package com.member;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.member.controller.MemberController;
import com.member.entity.Member;
import com.member.service.MemberService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberControllerTest {

	@Autowired 
	private MemberController memberController;
	
	@MockBean
	private MemberService memberService;
	
	
	Member m = new Member((long) 13, "Raghav", "singh", "Raghav123", "123@Raghav", "123@Raghav", "Dwarka",
			"Delhi", 12, "Delhi", new Date(12 - 12 - 2001), "Raghav@gmail.com", LocalDateTime.now(),
			LocalDateTime.now());
	
	@Test
	void contextLoads() {
	}
	
	@Test
	public void findByUsername() {
		
		 String username="Raghav123";
		
		 Optional<Member> o = Optional.ofNullable(m);
		 
		 when(memberService.findByUsername(username)).thenReturn(o);
		 assertEquals("Raghav123", memberController.findMemberByUsername(username).get().getUsername());
	}
	
	
}
