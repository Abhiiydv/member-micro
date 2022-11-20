package com.member;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import com.member.entity.Member;
import com.member.entity.Physician;
import com.member.entity.Role;
import com.member.entity.User;
import com.member.repository.AdminRepository;
import com.member.repository.MemberRepository;
import com.member.repository.Physician_Repository;
import com.member.repository.UserRepository;
import com.member.service.AdminService;
import com.member.service.MemberService;
import com.member.service.PhysicianService;
import com.member.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
class MemberServiceApplicationTests {

	@Autowired
	private UserService userService;

	@Autowired
	private PhysicianService physicianServ;

	@Autowired
	private MemberService memberService;

	@MockBean
	private MemberRepository memberRepo;

	@MockBean
	private UserRepository userRepo;

	@MockBean
	private AdminRepository adminrepo;

	@Autowired
	private AdminService adminService;

	@MockBean
	private PasswordEncoder passwordEncoder;

	@MockBean
	private Physician_Repository phyRepo;

	User user = new User((long) 7, "Abhishekydv9", "12345@Abhishek", Role.MEMBER, "", "");

	@Test
	public void saveUser() {
		String encodedPassword = "$10$YS7.P.OaNONZ1VS.XOOHHOcimDgZRm8ElyxMR0OIujtQSTSQIMGFu";
		when(passwordEncoder.encode(user.getPassword())).thenReturn(encodedPassword);
		when(userRepo.save(user)).thenReturn(user);
		assertEquals(user.getId(), userService.saveUser(user).getId());
	}

	Member m = new Member((long) 11, "Abhishek", "yadav", "Abhishek123", "123@Abhishek", "123@Abhishek", "Dwarka",
			"Delhi", 12, "Delhi", new Date(12 - 11 - 2001), "Abhsiehkydv99@gmail.com", LocalDateTime.now(),
			LocalDateTime.now());
	Member m2 = new Member((long) 12, "Ajeet", "yadav", "Ajeet123", "123@Ajeet", "123@Ajeet", "Dwarka", "Delhi", 12,
			"Delhi", new Date(12 - 12 - 2001), "Ajay@gmail.com", LocalDateTime.now(), LocalDateTime.now());

	@Test
	public void saveMember() {

		List<Physician> listP = new ArrayList<>();
		listP.add(new Physician(10, "Dr Amit", "UP"));

		User user = new User();
		user.setUserName(m.getUsername());
		user.setPassword("$10$YS7.P.OaNONZ1VS.XOOHHOcimDgZRm8ElyxMR0OIujtQSTSQIMGFu");
		user.setRole(Role.MEMBER);

		when(phyRepo.findAll()).thenReturn(listP);

		when(userService.saveUser(user)).thenReturn(user);

		when(userRepo.save(user)).thenReturn(user);

		when(adminrepo.save(m)).thenReturn(m);
		assertEquals(m.getId(), adminService.saveMember(m).getId());
	}

	@Test
	public void getUserbyUsername() {
		String username = "Abhishek123";
		Optional<User> u = Optional.of(new User((long) 12, "Abhishek123", "12345@Abhishek", Role.MEMBER, "", ""));
		when(userRepo.findByUserName(username)).thenReturn(u);
		assertEquals(u, userService.findByUsername(username));
	}

	@Test
	public void getmemberbyEmail() {
		String email = "Abhsiehkydv99@gmail.com";
		Optional<Member> op = Optional.ofNullable(m);
		when(adminrepo.findByEmail(m.getEmail())).thenReturn(m);
		assertEquals(op, adminService.findMemberByEmail(email));

	}

	@Test
	public void getmemberbyEmailFailure() {
		String email = "Abhsiehkydv999@gmail.com";
		Optional<Member> op = Optional.ofNullable(m);
		when(adminrepo.findByEmail(m.getEmail())).thenReturn(m);
		assertEquals(op, adminService.findMemberByEmail(email));

	}

	@Test
	public void checkIfExistsingMember() {
		String email = "Abhsiehkydv99@gmail.com";
		boolean result = true;
		when(adminrepo.existsByEmail(m.getEmail())).thenReturn(true);
		assertEquals(false, adminService.ifExistsingMember(email));

	}

	@Test
	public void checkIfExistsingMemberFailure() {
		String email = "Abhsiehkydv999@gmail.com";
		boolean result = true;
		when(adminrepo.existsByEmail(m.getEmail())).thenReturn(true);
		assertEquals(false, adminService.ifExistsingMember(email));

	}

	@Test
	public void getMemberByMemberId() {
		long mId = 11;
		Optional<Member> o = Optional.ofNullable(m);

		when(adminrepo.findById(mId)).thenReturn(o);
		assertEquals(o, adminService.findMemberById(mId));
	}

	@Test
	public void getMemberByMemberIdFailure() {
		long mId = 12;
		Optional<Member> o = Optional.ofNullable(m);

		when(adminrepo.findById(m.getId())).thenReturn(o);
		assertEquals(o, adminService.findMemberById(mId));
	}

	@Test
	public void findMemberbyUsername() {
		String username = "Abhishek123";
		Optional<Member> o = Optional.ofNullable(m);
		when(memberService.findByUsername(username)).thenReturn(o);
		assertEquals(o, memberService.findByUsername(username));

	}

	@Test
	public void findMemberbyUsernameFailure() {
		String username = "Abhishek12345";
		Optional<Member> o = Optional.ofNullable(m);
		when(memberService.findByUsername(m.getUsername())).thenReturn(o);
		assertEquals(o, memberService.findByUsername(username));

	}

	@Test
	public void fetchAllMembers() {

		List<Member> mlist = new ArrayList<Member>();

		mlist.add(m);

		when(adminrepo.findAll()).thenReturn(mlist);
		assertEquals(1, adminService.getAllMembers().size());

	}

	@Test
	public void findMemberByFirstLastName() {

		String firstname = "Ajeet", lastname = "Yadav";
		List<Member> mlist = new ArrayList<Member>();

		mlist.add(m2);

		when(adminrepo.findMembersbyFirstLastname(firstname, lastname)).thenReturn(mlist);
		assertEquals(1, adminService.findMembersbyFirstLastname(firstname, lastname).size());

	}

	@Test
	void contextLoads() {
	}

	@Test
	public void getUserByUsername() {
		String username = "Abhishek123";
		Optional<User> u = Optional.of(new User((long) 12, "Abhishek123", "12345@Abhishek", Role.MEMBER, "", ""));
		when(userRepo.findByUserName(username)).thenReturn(u);
		assertEquals(u, userService.findByUsername(username));
	}

	Physician p = new Physician(11, "Dr Amit", "UP");

	@Test
	public void getAllPhsicians() {
		when(phyRepo.findAll())
				.thenReturn(Stream.of(new Physician(10, "Dr Amit", "UP"), new Physician(11, "Dr Ajay", "UP"))
						.collect(Collectors.toList()));
		assertEquals(2, physicianServ.fetchAllPhysicians().size());
	}

	@Test
	public void getPhysicianByName() {
		Optional<Physician> o = Optional.of(p);
		String pname = "Dr Amit";
		when(phyRepo.findByName(pname)).thenReturn(o);
		assertEquals(o, physicianServ.findByName(pname));
	}

}
