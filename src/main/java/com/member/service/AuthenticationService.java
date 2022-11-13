package com.member.service;

import com.member.entity.User;

public interface AuthenticationService {

	User signInAndReturnJWT(User signInRequest);
}
