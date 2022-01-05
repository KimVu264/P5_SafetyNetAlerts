package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.model.FireStation;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class DataService
{
	private PersonService personService;

    private FireStationService fireStationService;

	private MedicalRecordService medicalRecordService;

   public  DataService(PersonService personService, FireStationService fireStationService, MedicalRecordService medicalRecordService)
   {
	   this.personService = personService;
	   this.fireStationService = fireStationService;
	   this.medicalRecordService = medicalRecordService;
   }

	public List<Person> savePerson(List<Person> persons) {
		List<Person> personList= personService.saveAll(persons);
		return personList;
	};

	public List<FireStation> saveFireStation(List<FireStation> fireStations) {
		List<FireStation> fireStationList = fireStationService.saveAll(fireStations);
		return fireStationList;
	};

	public List<MedicalRecord> saveMedicalRecord(List<MedicalRecord> medicalRecords) {
		List<MedicalRecord> medicalRecordList = medicalRecordService.saveAll(medicalRecords);
		return medicalRecordList;
	};
}
