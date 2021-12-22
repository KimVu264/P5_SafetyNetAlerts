package com.safetynet.safetynetalerts.dao;

import com.safetynet.safetynetalerts.model.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalRecordDao extends JpaRepository <MedicalRecord, Integer>
{
	MedicalRecord findByFirstNameAndLastName(String firstName, String lastName);
}
