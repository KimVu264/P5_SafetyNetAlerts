package com.safetynet.safetynetalerts.dao;

import com.safetynet.safetynetalerts.model.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalRecordDao extends JpaRepository <MedicalRecord, Integer>
{

	MedicalRecord findByFirstNameAndLastName(String firstName, String lastName);

	/*
	@Query("delete FROM MedicalRecord m WHERE m.firstName = ?1 and m.lastName = ?2")
	@Modifying
	void deleteByFirstNameAndLastName(String firstName,String lastName);
	 */

}
