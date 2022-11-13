package com.member.service;

import java.util.Optional;

import com.member.entity.User;

public interface UserService {

	User saveUser(User user);
	
	Optional<User> findByUsername(String username);
}
