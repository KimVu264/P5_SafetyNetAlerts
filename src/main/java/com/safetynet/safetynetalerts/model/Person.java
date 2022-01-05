package com.safetynet.safetynetalerts.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "persons")
public class Person {

	@Id
	@GeneratedValue
	private int id;

	private String firstName;

	private String lastName;

	private String address;

	private String city;

	private int zip;

	private String phone;

	private String email;

}
