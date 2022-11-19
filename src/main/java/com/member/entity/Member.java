package com.member.entity;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "members")
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size(max=20 , min=5)
	private String first_name;
	
	@NotNull
	@Size(max=20 , min=5)
	private String last_name;
	
	@NotNull
	private String username;
	
    private String password;
	
	private String confirm_password;
	
	@NotNull
	@Column(length=300)
	private String address;
	
	private String state;
	
	private Integer physician_id;
	
	private String city;
	
	private Date dob;
	
	@Column(unique=true)
	@Email
	private String email;
	
	private LocalDateTime createdTime;
	
	private LocalDateTime modification_date;


	
	
	
}
