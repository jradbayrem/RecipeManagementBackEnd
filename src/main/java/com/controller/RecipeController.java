package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Recipe;
import com.service.RecipeService;

@RestController
@RequestMapping("/recipe")
@CrossOrigin(origins = "http://localhost:4200")
public class RecipeController {

	@Autowired
	RecipeService recipeService;

	@GetMapping(value = "all")
	public List<Recipe> getAllRecipes() {
		List<Recipe> rcps = recipeService.getAllRecipe();
		return rcps;
	}

	@GetMapping(value = "findById/{id}")
	public Recipe getById(@PathVariable int id) {
		if (recipeService.getRecipeById(id) == null) {
			return null;
		} else
			return recipeService.getRecipeById(id);
	}

	@PostMapping(value = "/addRecipe", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Recipe addNewRecipe(@RequestBody Recipe recipe) {
		return this.recipeService.addRecipe(recipe);
	}

	@PutMapping("/{id}")
	public Recipe updateRecipe(@RequestBody Recipe recipe, @PathVariable int id) {
		if (recipeService.getRecipeById(id) == null) {
			return null;
		} else
			return recipeService.addRecipe(recipe);
	}

	@DeleteMapping("/{id}")
	public void deleteRecipe(@PathVariable int id) {
		if (recipeService.getRecipeById(id) != null) {
			recipeService.deleteRecipe(recipeService.getRecipeById(id));
		}
	}
}
