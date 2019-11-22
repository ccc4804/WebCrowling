package com.core.entitiy;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Disease {
	@Id
	private String disease;
	private String goodFood;
	private String badFood;
	private String goodExercise;
	private String badExercise;
}
