package com.safetynet.safetynetalerts.dto;

import lombok.Data;

import java.util.List;

@Data
public class PersonInfoStationByAddress {

	private List<PersonInfoPhone> persons;
	private int stationNumber;

}
