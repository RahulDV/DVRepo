package com.ebjb.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="RecipeInfo")
@XmlAccessorType(XmlAccessType.FIELD)
public class RecipeInfo {
	
	@XmlElement(name="RecipeID")
	private String recipeID;
	@XmlElement(name="Title")
	private String title;
	@XmlElement(name="Cuisine")
	private String cuisine;
	@XmlElement(name="Category")
	private String category;
	@XmlElement(name="Subcategory")
	private String subcategory;
	@XmlElement(name="StarRating")
	private String starRating;
	@XmlElement(name="StarRatingIMG")
	private String starRatingIMG;
	@XmlElement(name="WebURL")
	private String webURL;
	@XmlElement(name="ImageURL")
	private String imageURL;
	@XmlElement(name="ImageURL120")
	private String imageURL120;
	public String getRecipeID() {
		return recipeID;
	}
	public void setRecipeID(String recipeID) {
		this.recipeID = recipeID;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCuisine() {
		return cuisine;
	}
	public void setCuisine(String cuisine) {
		this.cuisine = cuisine;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getSubcategory() {
		return subcategory;
	}
	public void setSubcategory(String subcategory) {
		this.subcategory = subcategory;
	}
	public String getStarRating() {
		return starRating;
	}
	public void setStarRating(String starRating) {
		this.starRating = starRating;
	}
	public String getStarRatingIMG() {
		return starRatingIMG;
	}
	public void setStarRatingIMG(String starRatingIMG) {
		this.starRatingIMG = starRatingIMG;
	}
	public String getWebURL() {
		return webURL;
	}
	public void setWebURL(String webURL) {
		this.webURL = webURL;
	}
	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	public String getImageURL120() {
		return imageURL120;
	}
	public void setImageURL120(String imageURL120) {
		this.imageURL120 = imageURL120;
	}
	
	
	

}
