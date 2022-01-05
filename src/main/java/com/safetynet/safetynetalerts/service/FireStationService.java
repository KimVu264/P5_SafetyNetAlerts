package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.dao.FireStationDao;
import com.safetynet.safetynetalerts.dao.MedicalRecordDao;
import com.safetynet.safetynetalerts.dto.PersonFullNameWithContacts;
import com.safetynet.safetynetalerts.model.FireStation;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.safetynet.safetynetalerts.util.DateUtil.computeAge;

@Service
@Transactional
public class FireStationService
{

	private FireStationDao fireStationDao;
	private MedicalRecordDao medicalRecordDao;

	public FireStationService (FireStationDao fireStationDao, MedicalRecordDao medicalRecordDao) {
		this.fireStationDao = fireStationDao;
		this.medicalRecordDao = medicalRecordDao;
	}

	public FireStation save (FireStation fireStation) {
		return fireStationDao.save(fireStation);
	}

	public List<FireStation> saveAll (List<FireStation> fireStations) {
		return fireStationDao.saveAll(fireStations);
	}

	public FireStation getFireStationByAddress( String address) {
		return fireStationDao.findByAddress(address);
	}

	public FireStation addFireStation( FireStation fireStation) {
		return fireStationDao.save(fireStation);
	}

	public FireStation getFireStationById (int id) {
		return fireStationDao.getById(id);
	}

	public FireStation updateFireStation( FireStation fireStation) {
		return fireStationDao.save(fireStation);
	}

	public int deleteFireStationByAddress(String address) {
		return fireStationDao.deleteFireStationByAddress(address);
	}

	public int deleteFireStationByStation(int station){
		return fireStationDao.deleteFireStationByStation(station);
	}

	public List<FireStation> getListAddressByStationNumber(int station){
		List<FireStation> fireStations = fireStationDao.getAllByStation(station);

		// To remove duplicated mapping between station numbers and addresses
		Set<FireStation> set = new HashSet<>(fireStations);
		fireStations.clear();
		fireStations.addAll(set);

		return fireStations;
	}

	public int countAdults(List<PersonFullNameWithContacts> persons) {
		int adults = 0;
		for(PersonFullNameWithContacts per: persons){
			MedicalRecord medicalRecord = medicalRecordDao.findByFirstNameAndLastName(per.getFirstName(), per.getLastName());
			int age = computeAge(medicalRecord);
			if(isAdult(age)){
				adults ++;
			}
		}
		return adults;
	}

	private boolean isAdult(int age) {
		return age >= 18;
	}


	public List<String> getPhonesByStation(int firestation) {
		return fireStationDao.getPhonesByStation(firestation);
	}


}
