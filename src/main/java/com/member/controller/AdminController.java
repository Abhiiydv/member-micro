package com.member.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

import com.member.entity.Claim;
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

	// fetch member details
	@GetMapping("/member/{id}")
	public Optional<Member> getDetailsOfMember(@PathVariable Long id) {
		System.out.println("Fetching Member for Id :" + id);
		return adminServ.findMemberById(id);

	}

	// get all physicians
	@GetMapping("/physicians")
	public List<Physician> fetchAllPhysicians() {
		return physicianServ.fetchAllPhysicians();
	}

	// get all claims
	@GetMapping("/claims")
	public Object getAllraisedClaims() {
		Object r = restTemplate.getForObject("http://claim-service//claims", Object.class);
		return r;
	}

	// getAllMembers
	@GetMapping("/members")
	public List<Member> getAllMembers() {
		return adminServ.getAllMembers();
	}

	// fetch members by physicianId
	@GetMapping("/members/physicians/{pId}")
	public List<Member> getAllMembersByPhysicianId(@PathVariable Integer pId) {
		return adminServ.getAllMembersByPhysicianId(pId);
	}

	// fetch member by phyisician details
	@PostMapping("/search")
	public List<Member> getMembers(@RequestBody SearchModel searchobj) {
		List<Member> list = new ArrayList<Member>();

		if (searchobj.getMemberId() != null) {
			Optional<Member> result = adminServ.findMemberById(searchobj.getMemberId());
			if (result.isPresent()) {
				list.add(result.get());
			}
			System.out.println(result);

		}

		else if (searchobj.getFirst_name() != null && searchobj.getLast_name() != null) {
			list = adminServ.findMembersbyFirstLastname(searchobj.getFirst_name(), searchobj.getLast_name());
		}

		else if (searchobj.getPhysician_name() != null) {

			Optional<Physician> p = physicianServ.findByName(searchobj.getPhysician_name());
			if (p != null) {
				list = adminServ.getAllMembers().stream().filter(m -> m.getPhysician_id() == p.get().getPhysician_id())
						.collect(Collectors.toList());
			}
			return list;

		}

		else if (searchobj.getClaimId() != null) {
			Claim c = restTemplate.getForObject("http://claim-service//claim/" + searchobj.getClaimId(), Claim.class);

			Optional<Member> m = adminServ.findMemberById(c.getMemberId());

			if (m.isPresent()) {
				list.add(m.get());
			}
		}

		else {
			System.out.println("Else in search");
			System.out.println("Second else");
		}
		return list;

	}
	// submit claim

	// fetch claim by claim id
	@GetMapping("/claim/{claimid}")
	public Optional<Claim> fetchClaimByClaimId(@PathVariable Integer claimid) {

		Claim c = restTemplate.getForObject("http://claim-service//claim/" + claimid, Claim.class);
		return Optional.ofNullable(c);
	}

	// fetch member by claim id
	@GetMapping("/claim/member/{claimId}")
	public Optional<Member> fetchMemberbyClaimId(@PathVariable Integer claimId) {

		Claim c = restTemplate.getForObject("http://claim-service//claim/" + claimId, Claim.class);

		Optional<Member> m = adminServ.findMemberById(c.getMemberId());

		return m;
	}
	// add member

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
