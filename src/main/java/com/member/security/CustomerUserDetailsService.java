package com.member.security;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.member.entity.User;
import com.member.service.UserService;
import com.member.utils.SecurityUtils;

@Service
public class CustomerUserDetailsService implements UserDetailsService{

	@Autowired
	private UserService userService;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userService.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("User not found with username: "+ username));
		
		 Set<GrantedAuthority> authorities = Set.of(SecurityUtils.convertToAuthority(user.getRole().name()));

	        return UserPrincipal.builder()
	                .user(user)
	                .id(user.getId())
	                .username(user.getUserName())
	                .password(user.getPassword())
	                .authorities(authorities)
	                .build();
	    }
		

}
