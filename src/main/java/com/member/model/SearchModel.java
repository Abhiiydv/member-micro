package com.member.model;

import lombok.Data;

@Data
public class SearchModel {

	
	private Long memberId;
	private String first_name;
	private String last_name;
	private String physician_name;
	private Integer claimId;
	
}
