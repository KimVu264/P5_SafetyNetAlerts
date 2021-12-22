package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.dao.FireStationDao;
import com.safetynet.safetynetalerts.model.FireStation;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class FireStationService
{

	private FireStationDao fireStationDao;

	public FireStationService (FireStationDao fireStationDao) {
		this.fireStationDao = fireStationDao;
	}

	public FireStation save (FireStation fireStation) {
		return fireStationDao.save(fireStation);
	}

	public List<FireStation> save (List<FireStation> fireStations) {
		return fireStationDao.saveAll(fireStations);
	}

	public FireStation getFireStationByAddress( String address) {
		return fireStationDao.findByAddress(address);
	}

	public List<FireStation> getListStation(int station){
		return fireStationDao.findAllByStation(station);
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

}
