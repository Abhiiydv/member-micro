package com.member.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.member.entity.Member;
import com.member.entity.Physician;
import com.member.entity.User;

public interface Physician_Repository extends JpaRepository<Physician, Integer>{
	
	//Optional<Physician> findByName(String name);
	
	@Query(value = "SELECT * FROM physician_table where physician_name = ?1 ", nativeQuery = true)
	Optional<Physician> findByName(String physician_name);
	
}
