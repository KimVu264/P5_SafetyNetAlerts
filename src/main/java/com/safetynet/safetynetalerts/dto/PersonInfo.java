package com.safetynet.safetynetalerts.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@EqualsAndHashCode
@Setter
@SuperBuilder
@NoArgsConstructor
public class PersonInfo {

	private String lastName;
	private int age;
	private List<String> medications;
	private List<String> allergies;
}
