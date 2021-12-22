package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.dao.PersonDao;
import com.safetynet.safetynetalerts.model.Person;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PersonService {

	private PersonDao personDao;

	public PersonService (PersonDao personDao) {
		this.personDao = personDao;
	}

	public Person getPersonById (int id) {
		return personDao.getById(id);
	}

	public Person getPersonBy( String firstName, String lastName) {
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
}
