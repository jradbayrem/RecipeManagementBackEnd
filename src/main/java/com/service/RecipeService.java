package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DAO.IngredientDao;
import com.DAO.InstructionDao;
import com.DAO.RecipeDao;
import com.entity.Ingredient;
import com.entity.Instruction;
import com.entity.Recipe;

@Service
public class RecipeService {

	@Autowired
	RecipeDao recipeDao;
	
	@Autowired
	IngredientDao ingDao;
	
	@Autowired
	InstructionDao instDao;
	
	public RecipeService(RecipeDao recipeDao) {
		this.recipeDao = recipeDao;
	}
	
	public List<Recipe> getAllRecipe() {
		List<Recipe> rcps = this.recipeDao.findAll();
		for (Recipe rcp : rcps) {
			rcp.setInstructions(this.instDao.findByRecipe(rcp));
			rcp.setIngredients(this.ingDao.findByRecipe(rcp));
		}
		return rcps;
	}
	
	public Recipe addRecipe(Recipe recipe) {
		List<Ingredient> ings = recipe.getIngredients();
		List<Instruction> ins = recipe.getInstructions();
		recipe.setIngredients(null);
		recipe.setInstructions(null);
		recipe = this.recipeDao.save(recipe);
		for (Ingredient ing : ings) {
			ing.setRecipe(recipe);
			ing = this.ingDao.save(ing);
		}
		for (Instruction inst : ins) {
			inst.setRecipe(recipe);
			inst = this.instDao.save(inst);
		}
		recipe.setIngredients(ings);
		recipe.setInstructions(ins);
		return recipe;
	}
	
	public Recipe getRecipeById(Integer recipe_id) {
		Recipe rcp = this.recipeDao.findById(recipe_id).orElse(null);
		rcp.setInstructions(this.instDao.findByRecipe(rcp));
		rcp.setIngredients(this.ingDao.findByRecipe(rcp));
		return rcp;
	}
	
	public void updateRecipe(Recipe recipe) {
		this.recipeDao.save(recipe);
	}
	
	public void deleteRecipe(Recipe recipe) {
		this.recipeDao.delete(recipe);
	}
}
