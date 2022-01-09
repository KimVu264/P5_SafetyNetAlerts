package com.safetynet.safetynetalerts.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.SuperBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@EqualsAndHashCode
@Setter
@SuperBuilder
@NoArgsConstructor
public class PersonFullNameWithAge extends PersonFullName {

	private int age;
}
