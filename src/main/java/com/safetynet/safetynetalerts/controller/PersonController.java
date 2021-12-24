package com.safetynet.safetynetalerts.controller;

import com.safetynet.safetynetalerts.dao.PersonDao;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.service.PersonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/person")
public class PersonController
{

	private static final Logger logger = LogManager.getLogger("PersonController");

	@Autowired
	private PersonService personService;

	public PersonController(PersonService personService)
	{
		this.personService = personService;
	}

	// Ajouter une nouvelle personne
	@PostMapping("/add")
	public ResponseEntity<Object> addPerson(@RequestBody Person person)
	{
		//Person temPerson = new Person();
		//temPerson.setFirstName(person.getFirstName());
		//temPerson.setLastName(person.getLastName());
		//Person per = personService.findPerson(temPerson);
		Person per = personService.getPersonBy(person.getFirstName(), person.getLastName());
		if(per == null)
		{
			logger.info("A new person is added successfully");
			return new ResponseEntity<>(personService.addPerson(person), HttpStatus.OK);
		}
		logger.error("Error: This person exist already");
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}

	// Mettre à jour une personne existante
	@PutMapping("/update/")
	public ResponseEntity<Object> updatePerson(@RequestBody Person person)
	{
		try
		{
			Person per = personService.getPersonById(person.getId());
			person.setId(per.getId());
			person.setFirstName(per.getFirstName());
			person.setLastName(per.getLastName());
			logger.info("Update person successfully");
			return new ResponseEntity<>(personService.updatePerson(person), HttpStatus.OK);
		}
		catch(EntityNotFoundException e)
		{
			logger.error("Error: Id is not valid");
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	// Delete une personne par nom et prénom
	@DeleteMapping("/delete/{firstName}/{lastName}")
	public void deletePerson(@PathVariable String firstName, @PathVariable String lastName)
	{
		//Person temPerson = new Person();
		//temPerson.setFirstName(firstName);
		//temPerson.setLastName(lastName);
		//Person per = personService.findPerson(temPerson);
		Person per = personService.getPersonBy(firstName, lastName);
		if(per == null)
		{
			logger.error("Error: firstName or lastName is not valid");
		}
		personService.deletePerson(firstName, lastName);
	}
}
