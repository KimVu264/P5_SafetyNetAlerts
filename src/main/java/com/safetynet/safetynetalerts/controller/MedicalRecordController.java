package com.safetynet.safetynetalerts.controller;

import com.safetynet.safetynetalerts.model.FireStation;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.service.MedicalRecordService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medicalRecord")
public class MedicalRecordController
{
	private static final Logger logger = LogManager.getLogger("MedicalRecordController");
	private MedicalRecordService medicalRecordService;

	public MedicalRecordController (MedicalRecordService medicalRecordService){
		this.medicalRecordService = medicalRecordService;
	}

	// Ajouter un dossier médical
	@PostMapping( "/addMedicalRecord")
	public ResponseEntity<Object> addMedicalRecord (@RequestBody MedicalRecord medicalRecord) {
		MedicalRecord mediRecord = medicalRecordService.getMedicalRecord(medicalRecord.getFirstName(), medicalRecord.getLastName());
		if (mediRecord == null) {
			return new ResponseEntity<>(medicalRecordService.addMedicalRecord(medicalRecord), HttpStatus.OK);
		}
		logger.error("Error: Dossier exist already");
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}


}
