package com.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.Recipe;

@Repository
public interface RecipeDao extends JpaRepository<Recipe, Integer>{

}
