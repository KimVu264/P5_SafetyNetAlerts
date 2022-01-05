package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.dao.FireStationDao;
import com.safetynet.safetynetalerts.dao.MedicalRecordDao;
import com.safetynet.safetynetalerts.dao.PersonDao;
import com.safetynet.safetynetalerts.dto.*;
import com.safetynet.safetynetalerts.model.FireStation;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.safetynet.safetynetalerts.util.DateUtil.computeAge;

@Service
@Transactional
public class PersonService {

	@Autowired
	private PersonDao personDao;

	@Autowired
	private FireStationDao fireStationDao;

	@Autowired
	private MedicalRecordDao medicalRecordDao;

	public PersonService (PersonDao personDao) {
		this.personDao = personDao;
	}

	public Person getPersonById (int id) {
		return personDao.getById(id);
	}

	public Person getPersonByName( String firstName, String lastName) {
		return personDao.findByFirstNameAndLastName (firstName, lastName);
	}

	/*
	public Person findPerson (Person person) {
		return personDao.findPerson(person);
	} */

	public List<Person> saveAll (List<Person> persons) {
		return personDao.saveAll(persons);
	}

	public Person addPerson( Person person) {
		return personDao.save(person);
	}

	public Person updatePerson( Person person) {
		return personDao.save(person);
    }

	public void deletePerson (String firstName, String lastName) {
		personDao.deletePersonByFirstNameAndLastName(firstName,lastName);
	}

	public List<PersonFullNameWithContacts> findByAddresses(List<FireStation> fireStations) {
		List<PersonFullNameWithContacts> personsWithContacts = new ArrayList<>();

		for(FireStation fs: fireStations){
			personDao.findByAddress(fs.getAddress()).forEach(person -> {
				PersonFullNameWithContacts personWithContacts = new PersonFullNameWithContacts();
				personWithContacts.setFirstName(person.getFirstName());
				personWithContacts.setLastName(person.getLastName());
				personWithContacts.setAddress(person.getAddress());
				personWithContacts.setPhone(person.getPhone());
				personsWithContacts.add(personWithContacts);
			});
		}
		return personsWithContacts;
	}

	public ChildByAddress getChildByAddress(String address){
		ChildByAddress childByAddress = new ChildByAddress();
		List<Person> adults = personDao.getAdultByAddress(address);
		List<Person> children = personDao.getChildByAddress(address);

		if (children.isEmpty() && adults.isEmpty()) {
			return null;
		}

		List<PersonFullNameWithAge> childrenWithAge = new ArrayList<>();
		for (Person person : children)
		{
			PersonFullNameWithAge personFullNameWithAge = new PersonFullNameWithAge();
			personFullNameWithAge.setFirstName(person.getFirstName());
			personFullNameWithAge.setLastName(person.getLastName());
			MedicalRecord medicalRecord = medicalRecordDao.findByFirstNameAndLastName(person.getFirstName(), person.getLastName());
			personFullNameWithAge.setAge(computeAge(medicalRecord));
			childrenWithAge.add(personFullNameWithAge);
		}
		if (childrenWithAge != null) {
			childByAddress.setChildren(childrenWithAge);
			childByAddress.setAdults(adults);
		}
		return childByAddress;
	}

	public PersonInfoStationByAddress getFullInfosByAddress(String address) {
		PersonInfoStationByAddress infos = new PersonInfoStationByAddress();
		List<PersonInfoPhone> persons = personDao.findByAddress(address).stream()
				.map(p -> {
					MedicalRecord medicalRecords = medicalRecordDao.findByFirstNameAndLastName(p.getFirstName(), p.getLastName());
					PersonInfoPhone person = new PersonInfoPhone();
					person.setLastName(p.getLastName());
					person.setAge(computeAge(medicalRecords));
					person.setPhone(p.getPhone());
					person.setMedications(medicalRecords.getMedications());
					person.setAllergies(medicalRecords.getAllergies());
					return person;
				}).collect(Collectors.toList());

		if (persons.isEmpty()) {
			return null;
		}

		FireStation fireStation = fireStationDao.findByAddress(address);

		infos.setPersons(persons);
		infos.setStationNumber(fireStation.getStation());

		return infos;
	}

	public List<Household> getHouseholdsByStation(int station) {
			List<Household> households = new ArrayList<>();
			List<FireStation> fireStations = fireStationDao.findByStation(station);

			for (FireStation fs : fireStations) {
				List<Person> persons = personDao.findByAddress(fs.getAddress());
				Household household = new Household();
				List<PersonInfoPhone> householdPersons = new ArrayList<>();

				for (Person person : persons) {
					MedicalRecord medicalRecord = medicalRecordDao.findByFirstNameAndLastName(person.getFirstName(), person.getLastName());
					PersonInfoPhone personInfoPhone = new PersonInfoPhone();
					personInfoPhone.setLastName(person.getLastName());
					personInfoPhone.setAge(computeAge(medicalRecord));
					personInfoPhone.setPhone(person.getPhone());
					personInfoPhone.setAllergies(medicalRecord.getAllergies());
					personInfoPhone.setMedications(medicalRecord.getMedications());

					householdPersons.add(personInfoPhone);
				}

				household.setStation(fs.getStation());
				household.setAddress(fs.getAddress());
				household.setHouseholdPersons(householdPersons);

				households.add(household);
			}

			return households;
		}

	public PersonInfoAddress getFullInfo(String firstName, String lastName) {
		Person person = personDao.findByFirstNameAndLastName(firstName, lastName);

		if (person == null) {
			return null;
		}

		MedicalRecord medicalRecord = medicalRecordDao.findByFirstNameAndLastName(firstName, lastName);

		PersonInfoAddress personInfoAddresses = new PersonInfoAddress();
		personInfoAddresses.setLastName(person.getLastName());
		personInfoAddresses.setAge(computeAge(medicalRecord));
		personInfoAddresses.setAddress(person.getAddress());
		personInfoAddresses.setMail(person.getEmail());
		personInfoAddresses.setAllergies(medicalRecord.getAllergies());
		personInfoAddresses.setMedications(medicalRecord.getMedications());

		return personInfoAddresses;
	}

	public List<String> getListMailByCity(String city) {
		return personDao.getListMailByCity(city);
	}
}
