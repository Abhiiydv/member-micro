package com.member.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.member.entity.Claim;
import com.member.entity.Member;
import com.member.entity.Physician;
import com.member.entity.User;
import com.member.service.AdminService;
import com.member.service.AuthenticationService;
import com.member.service.JwtRefreshTokenService;
import com.member.service.MemberService;
import com.member.service.PhysicianService;
import com.member.service.UserService;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/api/authentication")
public class AuthenticationController {

	@Autowired
	private AuthenticationService authenticationService;
	
	@Autowired
	private JwtRefreshTokenService jwtRefreshTokenService;
	
	@Autowired
	private AdminService adminServ;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PhysicianService physicianServ;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@PostMapping("/sign-up-member")
	public ResponseEntity<?>signUp(@RequestBody Member member)
	{
		String Username = member.getUsername();
		if (adminServ.findByUsername(member.getUsername()).isPresent())
        {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
            //we will use this code to show alert for already exists errorcode409
        }
		else if(Username=="") {
			//406 if username is blank
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
        return new ResponseEntity<>(adminServ.saveMember(member), HttpStatus.CREATED);
		
	}
	
	@PostMapping("sign-in")
	public ResponseEntity<?> signIn(@RequestBody User user){
		System.out.println("Inside signIn controller");
		return new ResponseEntity<>(authenticationService.signInAndReturnJWT(user),HttpStatus.OK);
	}
	
	@GetMapping("/physicians")
	public List<Physician> fetchAllPhysicians(){
		return physicianServ.fetchAllPhysicians();
	}
	
	/*
	 * @PostMapping("/submit") public Integer submitClaim(@RequestBody Claim claim)
	 * {
	 * 
	 * Integer id = restTemplate.postForEntity("http://claim-service/submit/",
	 * claim, null, null); return id;
	 * 
	 * }
	 */
	
	/*
	 * @PostMapping("/sign-up") public ResponseEntity<?>signUp(@RequestBody User
	 * user) { if(userService.findByUsername(user.getUserName()).isPresent()) {
	 * return new ResponseEntity<>(HttpStatus.CONFLICT); } return new
	 * ResponseEntity<>(userService.saveUser(user),HttpStatus.CREATED); }
	 */
	
}
