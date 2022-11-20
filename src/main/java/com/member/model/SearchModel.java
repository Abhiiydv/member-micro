package com.member.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchModel {

	
	private Long memberId;
	private String first_name;
	private String last_name;
	private String physician_name;
	private Integer claimId;
	
}
