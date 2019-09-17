package com.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
//Generer les getters et les setters
@AllArgsConstructor
//Generer constructeur avec tous les argument
@NoArgsConstructor
//Generer un constructeur sans argement
@Entity
public class Recipe {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String title;
	
	private String description;
	
	private int feeds_this_many;
	
	private int preparation_time;
	
	@JoinColumn(name="id")
	@OneToMany
	private List<Ingredient> ingredients;
	
	@OneToMany
	@JoinColumn(name = "id")
	private List<Instruction> instructions;


	
}
