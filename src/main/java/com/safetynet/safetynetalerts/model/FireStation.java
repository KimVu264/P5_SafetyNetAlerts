package com.safetynet.safetynetalerts.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name="firestations")
public class FireStation {

	@Id
	@GeneratedValue
	private int id;

	private String address;

	private int station;

}
