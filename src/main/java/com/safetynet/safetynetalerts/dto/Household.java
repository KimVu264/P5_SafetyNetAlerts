package com.safetynet.safetynetalerts.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@EqualsAndHashCode
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Household {

	private int station;
	private String address;
	private List<PersonInfoPhone> householdPersons;
}
