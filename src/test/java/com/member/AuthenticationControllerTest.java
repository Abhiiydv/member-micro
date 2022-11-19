package com.member;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.member.controller.AuthenticationController;
import com.member.entity.Member;
import com.member.entity.Physician;
import com.member.entity.Role;
import com.member.entity.User;
import com.member.service.AdminService;
import com.member.service.AuthenticationService;
import com.member.service.PhysicianService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthenticationControllerTest {

	
	@Autowired
	private AuthenticationController authcontroller;
	
	@MockBean
	private AdminService adminService;
	
	@MockBean
	private AuthenticationService authService;

	@MockBean
	private PhysicianService physicianService;
	
	@Test
	void contextLoads() {
	}
	
	
	Member m = new Member((long) 11, "Abhishek", "yadav", "Abhishek123", "123@Abhishek", "123@Abhishek", "Dwarka",
			"Delhi", 12, "Delhi", new Date(12 - 11 - 2001), "Abhsiehkydv99@gmail.com", LocalDateTime.now(),
			LocalDateTime.now());
	Member m2 = new Member((long) 12, "Aradhya", "yadav", "", "123@Aradhya", "123@Aradhya", "Dwarka",
			"Delhi", 12, "Delhi", new Date(12 - 11 - 2001), "Aradhya99@gmail.com", LocalDateTime.now(),
			LocalDateTime.now());
	Member m3 = new Member((long) 13, "Aditi", "yadav", "Aditi123", "123@Aditi", "123@Aradhya", "Dwarka",
			"Delhi", 12, "Delhi", new Date(12 - 11 - 2001), "Aditi99@gmail.com", LocalDateTime.now(),
			LocalDateTime.now());
	
	@Test
	public void signupMember() {
		
		when(adminService.saveMember(m)).thenReturn(m);
		assertEquals(new ResponseEntity<>(m, HttpStatus.CREATED), authcontroller.signUp(m));
		
	}
	
	@Test
	public void signupMemberFailure() {
		
		when(adminService.saveMember(m2)).thenReturn(m2);
		assertEquals(new ResponseEntity<>( HttpStatus.NOT_ACCEPTABLE), authcontroller.signUp(m2));
		
	}
	@Test
	public void signupMemberFailure2() {
		
		when(adminService.saveMember(m3)).thenReturn(m3);
		assertEquals(new ResponseEntity<>( HttpStatus.NOT_ACCEPTABLE), authcontroller.signUp(m3));
		
	}
	
	@Test 
	public void signIn() {
		User req = new User((long)11, "Abhishek123","$10$TY3CCAVvSI7OKKUO.EoAUeU.//0DtXREmICTH0rQN2dPKbxXhv6kG" ,Role.ADMIN,"","");
		User user = new User();
		user.setUserName("Abhishek123");
		user.setPassword("$10$TY3CCAVvSI7OKKUO.EoAUeU.//0DtXREmICTH0rQN2dPKbxXhv6kG");
		when(authService.signInAndReturnJWT(user)).thenReturn(req);
		assertEquals(new ResponseEntity<>(req, HttpStatus.OK), authcontroller.signIn(user));
	}
	@Test 
	public void signInFailure() {
		User req = new User((long)11, "Abhishek123","$10$TY3CCAVvSI7OKKUO.EoAUeU.//0DtXREmICTH0rQN2dPKbxXhv6kG" ,Role.ADMIN,"","");
		
		User user = new User();
		user.setUserName("Abhishek12345");
		user.setPassword("$10$TY3CCAVvSI7OKKUO.EoAUeU.//0DtXREmICTH0rQN2dPKbxXhv6kG");
		
		when(authService.signInAndReturnJWT(user)).thenReturn(req);
		assertEquals(new ResponseEntity<>(req, HttpStatus.OK), authcontroller.signIn(user));
	}
	
	@Test
	public void fetchAllPhysicians() {
		
		Physician p = new Physician(11, "Dr Amit", "UP");
		Physician p2 = new Physician(12, "Dr Abhishek", "UP");
		
		List<Physician> listp=  new ArrayList<Physician> ();
		listp.add(p);
		listp.add(p2);
		
		 when(physicianService.fetchAllPhysicians()).thenReturn(listp);
			assertEquals(2, authcontroller.fetchAllPhysicians().size());
		
	}
	
}
