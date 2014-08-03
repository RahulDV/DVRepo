package com.ebjb.model;

import java.util.List;

public class EBJBSearchVO {
	
	private String count;
	
	private List<RecipeVO> recipe;

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public List<RecipeVO> getRecipe() {
		return recipe;
	}

	public void setRecipe(List<RecipeVO> recipe) {
		this.recipe = recipe;
	} 

}
