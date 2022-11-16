package com.member.service;

import java.util.List;
import java.util.Optional;

import com.member.entity.Physician;

public interface PhysicianService {

	public List<Physician> fetchAllPhysicians();
	
	Optional<Physician> findByName(String name);
}
