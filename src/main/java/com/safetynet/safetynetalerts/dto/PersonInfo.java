package com.safetynet.safetynetalerts.dto;

import lombok.Data;

import java.util.List;

@Data
public class PersonInfo {

	private String lastName;
	private int age;
	private List<String> medications;
	private List<String> allergies;
}
