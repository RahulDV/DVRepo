package com.ebjb.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ebjb.model.EBJBSearchVO;
import com.ebjb.service.RecipeService;


@Controller
public class ServiceController {
	
	@Autowired
	RecipeService recipeService;
	
	
	@RequestMapping(value="/search",method=RequestMethod.GET)
	@ResponseBody
	public EBJBSearchVO searchRecipes(HttpServletRequest request,HttpServletResponse response){
		response.setContentType("application/json");
		String searchKey=request.getParameter("key");
		String page=request.getParameter("page");
		return recipeService.getRecipeSearchResults(searchKey, page);
	}
	
}
