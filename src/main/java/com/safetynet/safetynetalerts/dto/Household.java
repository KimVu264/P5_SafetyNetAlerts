package com.safetynet.safetynetalerts.dto;

import lombok.Data;

import java.util.List;

@Data
public class Household {

	private int station;
	private String address;
	private List<PersonInfoPhone> householdPersons;
}
