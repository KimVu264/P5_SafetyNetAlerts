package com.safetynet.safetynetalerts.dao;

import com.safetynet.safetynetalerts.model.FireStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FireStationDao extends JpaRepository<FireStation, Integer> {

	FireStation findByAddress(String address);

	@Query("Select distinct f from FireStation f where f.station=?1")
	List<FireStation> getAllByStation(int station);

	@Query("delete FROM  FireStation f WHERE f.address = ?1 ")
	@Modifying
	int deleteFireStationByAddress(String address);

	@Query("delete FROM  FireStation f WHERE f.station = ?1 ")
	@Modifying
	int deleteFireStationByStation(int station);

	@Query("SELECT DISTINCT ps.phone FROM FireStation fs INNER JOIN Person ps ON ps.address = fs.address WHERE fs.station = ?1")
	List<String> getPhonesByStation(int fireStation);

	@Query("SELECT fs FROM FireStation fs WHERE fs.station = ?1")
	List<FireStation> findByStation(int station);
}
