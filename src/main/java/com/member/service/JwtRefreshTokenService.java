package com.member.service;

import com.member.entity.JwtRefreshToken;
import com.member.entity.User;

public interface JwtRefreshTokenService {
	
	    JwtRefreshToken createRefreshToken(Long userId);

	    User generateAccessTokenFromRefreshToken(String refreshTokenId);
	    
}
