package com.member.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.member.entity.Member;
import com.member.entity.Physician;
import com.member.model.SearchModel;
import com.member.service.AdminService;
import com.member.service.PhysicianService;

@RestController
@RequestMapping("api/admin")
public class AdminController {

	@Autowired
	private AdminService adminServ;
	
	@Autowired
	private PhysicianService physicianServ;
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	//fetch member details
	@GetMapping("/member/{id}")
	public Optional<Member> getDetailsOfMember(@PathVariable Long id){
		System.out.println("Fetching Member for Id :"+id);
		return adminServ.findMemberById(id); 

	}
	
	@GetMapping("/physicians")
	public List<Physician> fetchAllPhysicians(){
		return physicianServ.fetchAllPhysicians();
	}
	
	//getAllMembers
	@GetMapping("/members")
	public List<Member> getAllMembers(){
		return adminServ.getAllMembers();
	}
	
	//fetch member by phyisician details
	@PostMapping("/search")
	public List<Member> getMembers(@RequestBody SearchModel searchobj )
	{
		List<Member> list = new ArrayList<Member>();
		
		if(searchobj.getMemberId()!=null) {
        Optional<Member> result = adminServ.findMemberById(searchobj.getMemberId());
        
        System.out.println(result);

		}
		else if(searchobj.getFirst_name()!=null && searchobj.getLast_name()!=null) {
			
		}
		else if(searchobj.getPhysician_name()!=null) {
			
		}
		else if(searchobj.getClaimId()!=null) {
			
		}
		return list;
		
	}
	//submit claim
	
	//fetch claim details
	
	//add member
	
		/*
		 * @PostMapping("/create") public ResponseEntity<Member> saveMember(@RequestBody
		 * Member member) { if
		 * (adminServ.findByUsername(member.getUsername()).isPresent()) { return new
		 * ResponseEntity<>(HttpStatus.CONFLICT); //we will use this code to show alert
		 * for already exists errorcode409 } return new
		 * ResponseEntity<>(adminServ.saveMember(member), HttpStatus.CREATED);
		 * 
		 * }
		 */
		
}
