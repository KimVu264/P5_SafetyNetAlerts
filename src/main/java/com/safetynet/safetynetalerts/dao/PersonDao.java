package com.safetynet.safetynetalerts.dao;

import com.safetynet.safetynetalerts.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonDao extends JpaRepository<Person, Integer>
{
	@Query("delete FROM  Person p WHERE p.firstName = ?1 and p.lastName = ?2 ")

	@Modifying
	int deletePersonByFirstNameAndLastName(String firstName,String lastName);

	Person getPersonByFirstNameAndLastName(String firstName,String lastName);

	//Person findPerson(Person person);
}
