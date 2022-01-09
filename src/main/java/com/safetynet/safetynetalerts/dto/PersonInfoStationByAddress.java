package com.safetynet.safetynetalerts.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@EqualsAndHashCode
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonInfoStationByAddress {

	private List<PersonInfoPhone> persons;
	private int stationNumber;

}
