package com.member.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.member.entity.User;
import com.member.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public User saveUser(User user) {
		// TODO Auto-generated method stub
		if (user.getUserName() != null)
			user.setPassword(passwordEncoder.encode(user.getPassword()));
		System.out.println("Creating user..." + user.getUserName());
		return userRepo.save(user);
	}

	@Override
	public Optional<User> findByUsername(String username) {
		// TODO Auto-generated method stub
		return userRepo.findByUserName(username);
	}
}
