package com.safetynet.safetynetalerts.dto;

import com.safetynet.safetynetalerts.model.Person;
import lombok.Data;

import java.util.List;

@Data
public class PersonByStation {
	private List<Person> persons;
	private int numberOfAdults;
	private int numberOfChildren;
}
