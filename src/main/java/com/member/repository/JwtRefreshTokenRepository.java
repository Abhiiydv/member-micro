package com.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.member.entity.JwtRefreshToken;

public interface JwtRefreshTokenRepository extends JpaRepository<JwtRefreshToken, String>{

}
