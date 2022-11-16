package com.member.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.member.entity.Member;

public interface AdminRepository extends JpaRepository<Member, Long>{

	Optional<Member> findByUsername(String userName);
	
	
	@Query(value = "SELECT * FROM members where first_name = ?1 AND last_name = ?2", nativeQuery = true)
	List<Member> findMembersbyFirstLastname(String first_name, String last_name);
}