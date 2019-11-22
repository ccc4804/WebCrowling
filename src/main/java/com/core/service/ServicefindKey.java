package com.core.service;

import java.util.List;

import com.core.model.ModelDisease;

public interface ServicefindKey {
	public String getSearchNaver(String findString);
	public List<String> getSearchGoogle(String findString);
	public ModelDisease getDisease(String query);
}
