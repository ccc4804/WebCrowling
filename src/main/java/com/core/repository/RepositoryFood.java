package com.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.core.entitiy.Food;

public interface RepositoryFood extends JpaRepository<Food, String>{
	public Food findByFood(String food);
}
