package com.member.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "physician_table")
public class Physician {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer physician_id;
	
	private String physician_name;
	
	private String physician_state;
	
	
	
}
