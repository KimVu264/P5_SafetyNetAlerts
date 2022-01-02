package com.safetynet.safetynetalerts.controller;

import com.safetynet.safetynetalerts.dto.ChildByAddress;
import com.safetynet.safetynetalerts.dto.PersonByStation;
import com.safetynet.safetynetalerts.model.FireStation;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.service.FireStationService;
import com.safetynet.safetynetalerts.service.PersonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SafetyController {

	@Autowired
	private FireStationService fireStationService;

	@Autowired
	private PersonService personService;

	private static final Logger logger = LogManager.getLogger("SafetyController");

	// http://localhost:8080/firestation?stationNumber=<station_number>

	@GetMapping("/firestation")
	public @ResponseBody
	PersonByStation listPersonsByStationNumber(@RequestParam(name = "stationNumber") int station) {
		List<FireStation> firestations = fireStationService.getListAddressByStationNumber(station);
		List<Person> pers = personService.getPersonsByAddresses(firestations);
		int numberOfAdults = fireStationService.countAdults(pers);
		int numberOfChildren = pers.size()- numberOfAdults;

		PersonByStation personByStation = new PersonByStation();
		personByStation.setPersons(pers);
		personByStation.setNumberOfAdults(numberOfAdults);
		personByStation.setNumberOfChildren(numberOfChildren);

		return personByStation;
	}

	// http://localhost:8080/childAlert?address=<address>

	@GetMapping(value = "/childAlert")
	public @ResponseBody
	ChildByAddress getChildByAddress(@RequestParam(value = "address") String address) {
		ChildByAddress childByAddress = personService.getChildByAddress(address);
		if (childByAddress == null) {
			logger.info("null");
			return null;
		} else {
			return childByAddress;
		}
	}
}
