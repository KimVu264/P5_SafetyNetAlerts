package com.safetynet.safetynetalerts.dao;

import com.safetynet.safetynetalerts.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonDao extends JpaRepository<Person, Integer>
{
	@Query("delete FROM  Person p WHERE p.firstName = ?1 and p.lastName = ?2 ")
	@Modifying
	int deletePersonByFirstNameAndLastName(String firstName,String lastName);

	Person getPersonByFirstNameAndLastName(String firstName,String lastName);

	//Person findPerson(Person person);

	List<Person> getPersonByAddress (String address);

	@Query(value = "SELECT DISTINCT * FROM PERSONS  INNER JOIN MEDICALRECORDS ON PERSONS.FIRST_NAME = MEDICALRECORDS.FIRST_NAME AND PERSONS.LAST_NAME = MEDICALRECORDS.LAST_NAME WHERE PERSONS.ADDRESS = ?1 AND YEAR(CURRENT_TIMESTAMP()) - YEAR(MEDICALRECORDS.BIRTHDATE) >= 18", nativeQuery = true)
	List<Person> getAdultByAddress(String address);

	@Query(value = "SELECT DISTINCT * FROM PERSONS  INNER JOIN MEDICALRECORDS ON PERSONS.FIRST_NAME = MEDICALRECORDS.FIRST_NAME AND PERSONS.LAST_NAME = MEDICALRECORDS.LAST_NAME WHERE PERSONS.ADDRESS = ?1 AND YEAR(CURRENT_TIMESTAMP()) - YEAR(MEDICALRECORDS.BIRTHDATE) < 18", nativeQuery = true)
	List<Person> getChildByAddress(String address);

}
