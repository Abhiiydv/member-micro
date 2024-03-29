package com.member.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.member.entity.Physician;
import com.member.repository.Physician_Repository;

@Service
public class PhysicianServiceImpl implements PhysicianService {

	@Autowired
	private Physician_Repository physicianRepo;

	@Override
	public List<Physician> fetchAllPhysicians() {
		// TODO Auto-generated method stub
		return physicianRepo.findAll();
	}

	@Override
	public Optional<Physician> findByName(String name) { // TODO
		return physicianRepo.findByName(name);
	}

}