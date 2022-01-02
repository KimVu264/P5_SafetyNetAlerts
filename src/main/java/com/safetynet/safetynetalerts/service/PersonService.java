package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.dao.MedicalRecordDao;
import com.safetynet.safetynetalerts.dao.PersonDao;
import com.safetynet.safetynetalerts.dto.ChildByAddress;
import com.safetynet.safetynetalerts.dto.PersonWithAge;
import com.safetynet.safetynetalerts.model.FireStation;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static com.safetynet.safetynetalerts.util.DateUtil.computeAge;

@Service
@Transactional
public class PersonService {

	@Autowired
	private PersonDao personDao;

	@Autowired
	private MedicalRecordDao medicalRecordDao;

	public PersonService (PersonDao personDao, MedicalRecordDao medicalRecordDao) {
		this.personDao = personDao;
		this.medicalRecordDao = medicalRecordDao;
	}

	public Person getPersonById (int id) {
		return personDao.getById(id);
	}

	public Person getPersonByName( String firstName, String lastName) {
		return personDao.getPersonByFirstNameAndLastName(firstName, lastName);
	}

	/*
	public Person findPerson (Person person) {
		return personDao.findPerson(person);
	} */

	public List<Person> save (List<Person> persons) {
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

	public List<Person> getPersonsByAddresses(List<FireStation> fireStations) {
		List<Person> pers = new ArrayList<>();
		for(FireStation fs: fireStations){
			List<Person> subPers = personDao.getPersonByAddress(fs.getAddress());
			pers.addAll(subPers);
		}
		return pers;
	}

	public ChildByAddress getChildByAddress(String address){
		ChildByAddress childByAddress = new ChildByAddress();
		List<Person> adults = personDao.getAdultByAddress(address);
		List<Person> children = personDao.getChildByAddress(address);

		List<PersonWithAge> childrenWithAge = new ArrayList<>();
		for (Person person : children)
		{
			PersonWithAge personWithAge = new PersonWithAge();
			personWithAge.setFirstName(person.getFirstName());
			personWithAge.setLastName(person.getLastName());
			MedicalRecord medicalRecord = medicalRecordDao.findByFirstNameAndLastName(person.getFirstName(), person.getLastName());
			personWithAge.setAge(computeAge(medicalRecord));
			childrenWithAge.add(personWithAge);
		}
		if (childrenWithAge != null) {
			childByAddress.setChildren(childrenWithAge);
			childByAddress.setAdults(adults);
		}
		return childByAddress;
	}
}
