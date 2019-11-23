package com.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.core.model.ModelQueryResult;
import com.core.service.ServicefindKey;

@RestController
public class ControllerTest {
	@Autowired
	ServicefindKey servicefindKey;
	
	@GetMapping(value = "/search")
	public ModelQueryResult searchQuery(String query) {
		return servicefindKey.getQuery(query);
	}

}
