package com.member.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class Claim {
	
	private Integer claim_id;
	
	private long memberId;
	
	private long ammount;
	
	private String remarks;
	
	private LocalDate claimDate;
	
	@Enumerated(EnumType.STRING)
	private ClaimType claimType;

}
