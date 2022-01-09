package com.safetynet.safetynetalerts.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.safetynet.safetynetalerts.model.Person;
import lombok.*;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@EqualsAndHashCode
@Setter
@NoArgsConstructor
public class ChildByAddress {
	private List<PersonFullNameWithAge> children;
	private List<Person> adults;
}
