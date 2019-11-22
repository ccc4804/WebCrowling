package com.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.core.entitiy.Disease;

public interface RepositoryDisease extends JpaRepository<Disease, String>{
	public Disease findByDisease(String disease);
}
