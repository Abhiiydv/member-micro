package com.member.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.member.entity.Member;
import com.member.entity.Physician;
import com.member.entity.Role;
import com.member.entity.User;
import com.member.repository.AdminRepository;
import com.member.repository.Physician_Repository;

@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AdminRepository adminRepo;

	@Autowired
	private Physician_Repository physicianRepo;

	@Autowired
	private UserService userserv;

	@Override
	public Member saveMember(Member member) {
		// TODO Auto-generated method stub
		// member.setPassword(passwordEncoder.encode(member.getPassword()));
		System.out.println("adminservice"+member);
		List<Physician> list = physicianRepo.findAll();

		User user = new User();

		user.setUserName(member.getUsername());
		user.setPassword(member.getPassword());
		user.setRole(Role.MEMBER);

		userserv.saveUser(user);

		member.setPhysician_id(1 + (int) (Math.random() * ((list.size() - 1) + 1)));
		member.setCreatedTime(LocalDateTime.now());
		member.setModification_date(LocalDateTime.now());
		member.setPassword(user.getPassword());
		member.setConfirm_password(user.getPassword());
		return adminRepo.save(member);
	}

	@Override
	public Optional<Member> findByUsername(String userName) {
		// TODO Auto-generated method stub
		return adminRepo.findByUsername(userName);
	}

	@Override
	public Optional<Member> findMemberById(Long id) {
		// TODO Auto-generated method stub
		return adminRepo.findById(id);
	}

	@Override
	public List<Member> getAllMembers() {
		// TODO Auto-generated method stub
		return adminRepo.findAll();
	}

	@Override
	public List<Member> getAllMembersByPhysicianId(Integer pId) {
		// TODO Auto-generated method stub

		List<Member> list = adminRepo.findAll().stream().filter(m -> m.getPhysician_id() == pId)
				.collect(Collectors.toList());

		return list;
	}

	@Override
	public List<Member> findMembersbyFirstLastname(String first_name, String last_name) {
		// TODO Auto-generated method stub
		return adminRepo.findMembersbyFirstLastname(first_name, last_name);
	}
	/*
	 * @Override public List<Member> findMembersByPhysicianName(String pName) { //
	 * TODO Auto-generated method stub Optional<Physician> p =
	 * physicianRepo.findByName(pName);
	 * 
	 * List<Member> list = adminRepo.findAll().stream().filter(m->
	 * m.getPhysician_id()==p.get().getPhysician_id()).collect(Collectors.toList());
	 * 
	 * return list; }
	 */

}