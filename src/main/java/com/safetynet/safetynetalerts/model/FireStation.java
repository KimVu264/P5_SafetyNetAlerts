package com.safetynet.safetynetalerts.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@EqualsAndHashCode
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "firestations")
public class FireStation {

	@Id
	@GeneratedValue
	private int id;

	private String address;

	private int station;

}
