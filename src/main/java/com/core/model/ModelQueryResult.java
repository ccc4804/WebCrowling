package com.core.model;

import com.core.entitiy.Disease;
import com.core.entitiy.Exercise;
import com.core.entitiy.Food;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ModelQueryResult {
	private Disease disease;
	private Food food;
	private Exercise exercise;
}
