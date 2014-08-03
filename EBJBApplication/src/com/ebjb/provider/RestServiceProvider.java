package com.ebjb.provider;

import org.springframework.web.client.RestTemplate;

/**
 * 
 * @author Venkat Rahul Dantuluri
 *
 * @param <T>
 */
public class RestServiceProvider<T> {
	
	public T callRemoteService(String url, Class<T> classref){
		RestTemplate restTemplate = new RestTemplate();
		T result = restTemplate.getForObject(url,classref);
		return result;
	}


}
