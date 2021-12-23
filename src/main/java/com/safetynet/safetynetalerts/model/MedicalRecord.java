package com.safetynet.safetynetalerts.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.jsoniter.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name="medicalrecords")
public class MedicalRecord {

	@Id
	@GeneratedValue
	private int id;

	private String firstName;

	private String lastName;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/mm/yyyy", timezone="CET")
	private Date birthdate;

	@ElementCollection
	//@JsonProperty( "medications" )
	private List<String> medications = new ArrayList<>();

	@ElementCollection
	//@JsonProperty( "allergies" )
	private List<String> allergies = new ArrayList<>();


}
