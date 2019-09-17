package com.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.Ingredient;
import com.entity.Recipe;

@Repository
public interface IngredientDao extends JpaRepository<Ingredient, Integer>{

	public List<Ingredient> findByRecipe(Recipe recipe);
}
