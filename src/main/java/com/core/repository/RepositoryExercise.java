package com.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.core.entitiy.Exercise;

public interface RepositoryExercise extends JpaRepository<Exercise, String>{
	public Exercise findByExercise(String exercise);
}
