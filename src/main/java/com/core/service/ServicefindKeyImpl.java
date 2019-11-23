package com.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.entitiy.Disease;
import com.core.entitiy.Exercise;
import com.core.entitiy.Food;
import com.core.entitiy.Keyword;
import com.core.model.ModelQueryResult;
import com.core.repository.RepositoryDisease;
import com.core.repository.RepositoryKeyword;

@Service
public class ServicefindKeyImpl implements ServicefindKey {
	@Autowired
	RepositoryKeyword repositoryKeyword;
	@Autowired
	RepositoryDisease repositoryDisease;

	@Override
	public ModelQueryResult getQuery(String query) {
		// TODO Auto-generated method stub
		ModelQueryResult modelQueryResult;
		try {
			Disease disease = repositoryDisease.findByDisease(query);
			if (disease == null) {
				Keyword keyword = repositoryKeyword.findByKeyword(query);
				if (keyword == null) {
					repositoryKeyword.save(new Keyword(query, "Y"));
				}
				return null;
			} else {
				modelQueryResult = new ModelQueryResult(disease, new Food(), new Exercise());
				return modelQueryResult;
			}
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
			return null;
		}
	}

}
