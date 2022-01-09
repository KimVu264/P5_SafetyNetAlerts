package com.safetynet.safetynetalerts.controller;

import com.safetynet.safetynetalerts.dto.*;
import com.safetynet.safetynetalerts.model.FireStation;
import com.safetynet.safetynetalerts.service.FireStationService;
import com.safetynet.safetynetalerts.service.PersonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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

		if(firestations.isEmpty()){
			return null;
		}

		List<PersonFullNameWithContacts> persons = personService.findByAddresses(firestations);
		int numberOfAdults = fireStationService.countAdults(persons);
		int numberOfChildren = persons.size()- numberOfAdults;

		PersonByStation personByStation = new PersonByStation();
		personByStation.setPersons(persons);
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
			logger.info("Address does not exist or there is no one at this address");
			return null;
		} else {
			return childByAddress;
		}
	}

	// http://localhost:8080/phoneAlert?firestation=<firestation_number>

	@GetMapping(value = "/phoneAlert")
	public @ResponseBody
	List<String> getListPhoneByStation(@RequestParam(value = "firestation") int firestation) {
		return fireStationService.getPhonesByStation(firestation);
	}

	// http://localhost:8080/fire?address=<address>

	@GetMapping("/fire")
	public @ResponseBody
	PersonInfoStationByAddress getFullInfosByAddress(@RequestParam(value = "address") String address) {
		return personService.getFullInfosByAddress(address);
	}

	// http://localhost:8080/flood/station?stations=<a list of station_numbers>

	@GetMapping("/flood/station")
	public @ResponseBody
	List<List<Household>> getFullInfosByStations(@RequestParam(value = "stations") int[] stations) {
		List<List<Household>> list = new ArrayList<>();
		for (int station : stations) {
			List<Household> households = personService.getHouseholdsByStation(station);
			list.add(households);
		}
		return list;
	}

	// http://localhost:8080/personInfo?firstName=<firstName>&lastName=<lastName>

	@GetMapping("/personInfo")
	public PersonInfoAddress getInfoByName(@RequestParam(value = "firstName") String firstName,
	                                  @RequestParam(value = "lastName") String lastName) {
		return personService.getFullInfo(firstName, lastName);
	}

	// http://localhost:8080/communityEmail?city=<city>

	@GetMapping(value = "/communityEmail")
	public List<String> getListMailByCity(@RequestParam("city") String city) {
		return personService.getListMailByCity(city);
	}

}
