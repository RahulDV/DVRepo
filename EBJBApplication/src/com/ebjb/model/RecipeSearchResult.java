package com.ebjb.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="RecipeSearchResult")
@XmlAccessorType(XmlAccessType.FIELD)
public class RecipeSearchResult {
	
	@XmlElement(name="ResultCount")
	private String resultCount;
	
	@XmlElement(name="Results")
	private List<RecipeInfo> results;

	public String getResultCount() {
		return resultCount;
	}

	public void setResultCount(String resultCount) {
		this.resultCount = resultCount;
	}

	public List<RecipeInfo> getResults() {
		return results;
	}

	public void setResults(List<RecipeInfo> results) {
		this.results = results;
	}

	
	
}
