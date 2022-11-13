package com.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.member.entity.Physician;

public interface Physician_Repository extends JpaRepository<Physician, Integer>{
	
}
