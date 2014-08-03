package com.ebjb.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ebjb.model.EBJBSearchVO;
import com.ebjb.model.RecipeInfo;
import com.ebjb.model.RecipeSearchResult;
import com.ebjb.model.RecipeVO;
import com.ebjb.provider.RestServiceProvider;


/**
 * 
 * @author Venkat Rahul Dantuluri
 *
 */
@Service
public class RecipeService {
	
	private static final String API_KEY="dvx6sUuiVA6sl787123zwOByxH5i075k";
	private static final String SEARCH_HOST="http://api.bigoven.com/recipes?";
	private static final String SEARCH_PARAM_SEARCH_KEY="title_kw=";
	private static final String SEARCH_PARAM_PAGE_NUMBER="pg=";
	private static final String SEARCH_PARAM_RESULTS_PER_PAGE="rpp=";
	private static final String SEARCH_PARAM_API_KEY="api_key=";
	private static final String AMPERSAND ="&";
	private static final Integer RESULTS_PER_PAGE = new Integer("20") ;
	
	public EBJBSearchVO getRecipeSearchResults(String searchKey,String page){
		StringBuilder searchURL=new StringBuilder(SEARCH_HOST);
		searchURL.append(SEARCH_PARAM_SEARCH_KEY).append(searchKey).append(AMPERSAND).append(SEARCH_PARAM_PAGE_NUMBER).append(page).append(AMPERSAND).append(SEARCH_PARAM_RESULTS_PER_PAGE).append(RESULTS_PER_PAGE).append(AMPERSAND).append(SEARCH_PARAM_API_KEY).append(API_KEY);
		RestServiceProvider<RecipeSearchResult> provider = new RestServiceProvider<RecipeSearchResult>();
		RecipeSearchResult result = provider.callRemoteService(searchURL.toString(),RecipeSearchResult.class);
		EBJBSearchVO ebjbSeachVO = new EBJBSearchVO();
		ebjbSeachVO.setCount(result.getResultCount());
		List<RecipeVO> recipeVOs = new ArrayList<RecipeVO>();
		for(RecipeInfo recipeInfo:result.getResults()){
			RecipeVO recipeVO = new RecipeVO();
			recipeVO.setRecipeID(recipeInfo.getRecipeID());
			recipeVO.setImageURL(recipeInfo.getImageURL());
			recipeVOs.add(recipeVO);
		}
		ebjbSeachVO.setRecipe(recipeVOs);
		return ebjbSeachVO;
	}
	
/*	public static void main(String args[]){
		String url = "http://api.bigoven.com/recipes?title_kw=oysters&pg=1&rpp=20&api_key=dvx6sUuiVA6sl787123zwOByxH5i075k";
		RestServiceProvider rsp = new RestServiceProvider();
		RecipeSearchResult rsr = rsp.callRemoteService(url);
	}*/

}
