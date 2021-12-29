package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.dao.MedicalRecordDao;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

	public MedicalRecord getMedicalRecordById(int id) {
		return medicalRecordDao.getById(id);
	}

	public MedicalRecord getMedicalRecordByName(String firstName, String lastName){
		return medicalRecordDao.findByFirstNameAndLastName(firstName, lastName);
	}

	public MedicalRecord addMedicalRecord(MedicalRecord medicalRecord) {
		return medicalRecordDao.save(medicalRecord);
	}

	public MedicalRecord updateMedicalRecord(MedicalRecord medicalRecord) {
		return medicalRecordDao.save(medicalRecord);
	}

	public void deleteMedicalRecordById(Integer id) {
		medicalRecordDao.deleteById(id);
	}
}
