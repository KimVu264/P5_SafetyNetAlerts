package com.safetynet.safetynetalerts.dto;

import lombok.Data;

@Data
public class PersonFullNameWithContacts extends PersonFullName{

	private String address;
	private String phone;
}
