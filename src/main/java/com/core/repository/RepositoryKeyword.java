package com.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.core.entitiy.Keyword;

public interface RepositoryKeyword extends JpaRepository<Keyword, String>{
	public Keyword findByKeyword(String keyword);

}
