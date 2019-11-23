package com.core.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.core.model.ModelQueryResult;
import com.core.service.ServicefindKey;
import com.core.service.crowling.ServiceCrowlingManager;

@RestController
public class ControllerSearch {
	@Autowired
	ServicefindKey servicefindKey;
	
	@Autowired
	ServiceCrowlingManager serviceCrowlingManager;
	
	@GetMapping(value = "/search")
	public ModelQueryResult searchQuery(String query) {
		return servicefindKey.getQuery(query);
	}
	
	@GetMapping(value = "/search/test")
	public List<String> getAddressList(String query){
		return serviceCrowlingManager.getSearchGoogle(query);
	}
	
	@GetMapping(value = "/test")
	public List<String> getHtml(String query) {
		return serviceCrowlingManager.getHtml(query);
	}

}
