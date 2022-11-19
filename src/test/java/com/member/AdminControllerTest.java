package com.member;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;


import com.member.controller.AdminController;
import com.member.entity.Member;
import com.member.entity.Physician;
import com.member.service.AdminService;
import com.member.service.PhysicianService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminControllerTest {

	
	@Autowired
	AdminController adminController;
	
	@MockBean
	private AdminService adminService;
	
	@MockBean
	PhysicianService physervice;
	
	Member m = new Member((long) 13, "Raghav", "singh", "Raghav123", "123@Raghav", "123@Raghav", "Dwarka",
			"Delhi", 12, "Delhi", new Date(12 - 12 - 2001), "Raghav@gmail.com", LocalDateTime.now(),
			LocalDateTime.now());
	
	Member m2 = new Member((long) 12, "Ajeet", "yadav", "Ajeet123", "123@Ajeet", "123@Ajeet", "Dwarka",
			"Delhi", 12, "Delhi", new Date(12 - 12 - 2001), "Ajay@gmail.com", LocalDateTime.now(),
			LocalDateTime.now());
	
	Physician p = new Physician(11, "Dr Amit", "UP");
	Physician p2 = new Physician(12, "Dr Abhishek", "UP");
	
	
	@Test
	void contextLoads() {
	}
	
	
	@Test
	public void findMemberById() {
	 long id = 12;
	 Optional<Member> o = Optional.ofNullable(m2);
	 
	 when(adminService.findMemberById(id)).thenReturn(o);
		assertEquals(o, adminController.getDetailsOfMember(id));
	}
	
	@Test
	public void findMemberByIdFailure() {
	 long id = 11;
	 Optional<Member> o = Optional.ofNullable(m2);
	 
	 when(adminService.findMemberById(m2.getId())).thenReturn(o);
		assertEquals(o, adminController.getDetailsOfMember(id));
	}
	
	@Test
	public void fetchAllPhysicians() {
		List<Physician> listp=  new ArrayList<Physician> ();
		listp.add(p);
		listp.add(p2);
		
		 when(physervice.fetchAllPhysicians()).thenReturn(listp);
			assertEquals(2, adminController.fetchAllPhysicians().size());
		
	}
	@Test
	public void fetchAllPhysiciansFailure() {
		List<Physician> listp=  new ArrayList<Physician> ();
		listp.add(p);
		listp.add(p2);
		
		 when(physervice.fetchAllPhysicians()).thenReturn(listp);
			assertEquals(3, adminController.fetchAllPhysicians().size());
		
	}
	@Test 
	public void fetcAllMembers()
	{
		List<Member> listm =  new ArrayList<Member>();
		listm.add(m);
		listm.add(m2);
		 when(adminService.getAllMembers()).thenReturn(listm);
			assertEquals(2, adminController.getAllMembers().size());
	}
	
	@Test
	public void fetchAllMembersByPid() {
		Integer pid = 12;
		
		List<Member> listm =  new ArrayList<Member>();
		listm.add(m);
		listm.add(m2);
		
		 when(adminService.getAllMembersByPhysicianId(pid)).thenReturn(listm);
			assertEquals(2, adminController.getAllMembersByPhysicianId(pid).size());
		
		
	}
	@Test
	public void fetchAllMembersByPidFailure() {
		Integer pid = 11;
		
		List<Member> listm =  new ArrayList<Member>();
		listm.add(m);
		listm.add(m2);
		
		 when(adminService.getAllMembersByPhysicianId(pid)).thenReturn(listm);
			assertEquals(2, adminController.getAllMembersByPhysicianId(pid).size());
		
		
	}
}
