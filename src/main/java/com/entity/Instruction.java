package com.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Instruction {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String instruction;
	
	private String photo;
	
	@JsonIgnore
	@ManyToOne
	private Recipe recipe;

	
}
