package com.member;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.time.LocalDate;
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
import org.springframework.web.client.RestTemplate;

import com.member.entity.ClaimType;
import com.member.controller.AdminController;
import com.member.entity.Claim;
import com.member.entity.Member;
import com.member.entity.Physician;
import com.member.model.SearchModel;
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

	@MockBean
	private RestTemplate resttemp;

	Member m = new Member((long) 13, "Raghav", "singh", "Raghav123", "123@Raghav", "123@Raghav", "Dwarka", "Delhi", 12,
			"Delhi", new Date(12 - 12 - 2001), "Raghav@gmail.com", LocalDateTime.now(), LocalDateTime.now());

	Member m2 = new Member((long) 12, "Ajeet", "yadav", "Ajeet123", "123@Ajeet", "123@Ajeet", "Dwarka", "Delhi", 12,
			"Delhi", new Date(12 - 12 - 2001), "Ajay@gmail.com", LocalDateTime.now(), LocalDateTime.now());
	Optional<Member> m3 = Optional
			.ofNullable(new Member((long) 7, "Ajeet", "yadav", "Ajeet123", "123@Ajeet", "123@Ajeet", "Dwarka", "Delhi",
					12, "Delhi", new Date(12 - 12 - 2001), "Ajay@gmail.com", LocalDateTime.now(), LocalDateTime.now()));

	Physician p = new Physician(11, "Dr Amit", "UP");
	Physician p2 = new Physician(12, "Dr Abhishek", "UP");

	SearchModel s = new SearchModel((long) 11, "Raghav", "singh", "Dr Amit", (Integer) 12);

	Claim c = new Claim(10, (long) 7, (long) 1200, "This is for fever claims", LocalDate.of(2022, 11, 17),
			ClaimType.Medical);
	Claim c2 = new Claim(11, (long) 7, (long) 1200, "This is for fever claims", LocalDate.of(2022, 11, 17),
			ClaimType.Medical);

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
	public void getmemebrsbySearch() {
		List<Member> li2 = new ArrayList<Member>();
		li2.add(m);
		li2.add(m2);

		when(adminService.findMembersbyFirstLastname(s.getFirst_name(), s.getLast_name()))
				.thenReturn((List<Member>) li2.get(0));
		assertEquals(1, adminController.getMembers(s).size());
	}

	@Test
	public void fetchAllPhysicians() {
		List<Physician> listp = new ArrayList<Physician>();
		listp.add(p);
		listp.add(p2);

		when(physervice.fetchAllPhysicians()).thenReturn(listp);
		assertEquals(2, adminController.fetchAllPhysicians().size());

	}

	@Test
	public void fetchAllPhysiciansFailure() {
		List<Physician> listp = new ArrayList<Physician>();
		listp.add(p);
		listp.add(p2);

		when(physervice.fetchAllPhysicians()).thenReturn(listp);
		assertEquals(3, adminController.fetchAllPhysicians().size());

	}

	@Test
	public void fetcAllMembers() {
		List<Member> listm = new ArrayList<Member>();
		listm.add(m);
		listm.add(m2);
		when(adminService.getAllMembers()).thenReturn(listm);
		assertEquals(2, adminController.getAllMembers().size());
	}

	@Test
	public void fetchAllMembersByPid() {
		Integer pid = 12;

		List<Member> listm = new ArrayList<Member>();
		listm.add(m);
		listm.add(m2);

		when(adminService.getAllMembersByPhysicianId(pid)).thenReturn(listm);
		assertEquals(2, adminController.getAllMembersByPhysicianId(pid).size());

	}

	@Test
	public void fetchAllMembersByPidFailure() {
		Integer pid = 11;

		List<Member> listm = new ArrayList<Member>();
		listm.add(m);
		listm.add(m2);

		when(adminService.getAllMembersByPhysicianId(pid)).thenReturn(listm);
		assertEquals(2, adminController.getAllMembersByPhysicianId(pid).size());

	}

	@Test
	public void fetchClaimByClaimId() {

		Integer id = 10;
		Optional<Claim> op = Optional.ofNullable(c);
		when(resttemp.getForObject("http://claim-service//claim/" + id, Claim.class)).thenReturn(c);
		assertEquals(op, adminController.fetchClaimByClaimId(id));
	}

	@Test
	public void fetchClaimByClaimIdFailure() {

		Integer id = 11;
		Optional<Claim> op = Optional.ofNullable(c);
		when(resttemp.getForObject("http://claim-service//claim/" + id, Claim.class)).thenReturn(c);
		assertEquals(op, adminController.fetchClaimByClaimId(c.getClaim_id()));
	}

	@Test
	public void fetchMemberByClaimId() {
		Integer id = 10;
		Optional<Claim> op = Optional.ofNullable(c);
		when(resttemp.getForObject("http://claim-service//claim/" + id, Claim.class)).thenReturn(c);
		when(adminService.findMemberById(c.getMemberId())).thenReturn(m3);
		assertEquals(m3, adminController.fetchMemberbyClaimId(id));
	}

	@Test
	public void fetchMemberByClaimIdFailure() {
		Integer id = 15;
		Optional<Claim> op = Optional.ofNullable(c);
		when(resttemp.getForObject("http://claim-service//claim/" + id, Claim.class)).thenReturn(c);
		when(adminService.findMemberById(c.getMemberId())).thenReturn(m3);
		assertEquals(m3, adminController.fetchMemberbyClaimId(id));
	}

	@Test
	public void getAllClaims() {
		List<Claim> listc = new ArrayList<Claim>();
		listc.add(c);
		listc.add(c2);

		when(resttemp.getForObject("http://claim-service//claims", Object.class)).thenReturn(listc);
		assertEquals(listc, adminController.getAllraisedClaims());

	}

	@Test
	public void getAllClaimsFailure() {
		List<Claim> listc = new ArrayList<Claim>();

		when(resttemp.getForObject("http://claim-service//claims", Object.class)).thenReturn(listc);
		assertEquals(listc, adminController.getAllraisedClaims());

	}
}
