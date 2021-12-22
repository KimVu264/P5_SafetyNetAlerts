package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.dao.MedicalRecordDao;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class MedicalRecordService
{
	private MedicalRecordDao medicalRecordDao;

	public MedicalRecordService(MedicalRecordDao medicalRecordDao){
		this.medicalRecordDao = medicalRecordDao;
	}

	public List<MedicalRecord> save (List<MedicalRecord> medicalRecords) {
		return medicalRecordDao.saveAll(medicalRecords);
	}

	public MedicalRecord getMedicalRecord(String firstName, String lastName){
		return medicalRecordDao.findByFirstNameAndLastName(firstName, lastName);
	}

	public MedicalRecord addMedicalRecord(MedicalRecord medicalRecord) {
		return medicalRecordDao.save(medicalRecord);
	}
}
