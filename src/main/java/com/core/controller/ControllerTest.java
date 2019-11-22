package com.core.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.core.service.ServicefindKey;

@RestController
public class ControllerTest {
	@Autowired
	ServicefindKey servicefindKey;
	
	@GetMapping(value = "/search/naver")
	public String searchNaver(String findString) {
		return servicefindKey.getSearchNaver(findString);
	}
	
	@GetMapping(value = "/search/google")
	public List<String> searchGoogle(String findString) {
		return servicefindKey.getSearchGoogle(findString);
	}

}
