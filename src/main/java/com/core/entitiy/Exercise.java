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
public class Exercise {
	@Id
	private String exercise;
	private String good;
	private String bad;
	private String info;
	private String url;
}
