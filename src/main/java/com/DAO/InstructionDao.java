package com.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.Instruction;
import com.entity.Recipe;

@Repository
public interface InstructionDao extends JpaRepository<Instruction, Integer>{

	public List<Instruction> findByRecipe(Recipe recipe);
}
