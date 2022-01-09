package com.safetynet.safetynetalerts.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class PersonInfoAddress extends PersonInfo{

	private String address;
	private String mail;
}
