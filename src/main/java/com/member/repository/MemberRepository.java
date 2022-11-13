package com.member.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
	Optional<Member> findByUsername(String userName);
}
