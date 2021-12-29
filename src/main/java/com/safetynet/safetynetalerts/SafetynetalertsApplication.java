package com.safetynet.safetynetalerts;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;
import com.safetynet.safetynetalerts.model.FireStation;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.service.DataService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

@SpringBootApplication
public class SafetynetalertsApplication {

	@Value("${path.data.json}")
	private String getPATH;

	public static void main(String[] args)
	{
		SpringApplication.run(SafetynetalertsApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(DataService dataService) {
		return args -> {
			// read json and write to db
			/*
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<List<Person>> typeReference = new TypeReference<List<Person>>(){};
			InputStream inputStream = TypeReference.class.getResourceAsStream("/json/persons.json");

			TypeReference<List<FireStation>> typeReference1 = new TypeReference<List<FireStation>>(){};
			InputStream inputStream1 = TypeReference.class.getResourceAsStream("/json/firestations.json");

			TypeReference<List<MedicalRecord>> typeReference2 = new TypeReference<List<MedicalRecord>>(){};
			InputStream inputStream2 = TypeReference.class.getResourceAsStream("/json/medicalrecords.json");

			try {
				List<Person> persons = mapper.readValue(inputStream,typeReference);
				dataService.savePerson(persons);

				List<FireStation> fireStations = mapper.readValue(inputStream1,typeReference1);
				dataService.saveFireStation(fireStations);

				List<MedicalRecord> medicalRecords = mapper.readValue(inputStream2,typeReference2);
				dataService.saveMedicalRecord(medicalRecords);

				System.out.println("Data Saved!");

			} catch (IOException e){
				System.out.println("Unable to save users: " + e.getMessage());
			}

			 */

			BufferedReader bufferedReader = new BufferedReader(new FileReader(this.getPATH));
			Gson gson = new Gson();
			Object jsonObject = gson.fromJson(bufferedReader, Object.class);
			String jsonString = gson.toJson(jsonObject);
			ObjectMapper objectMapper = new ObjectMapper();
			Any jsonAny = JsonIterator.deserialize(jsonString);

			Any jsonPerson = jsonAny.get("persons");
			List<Person> persons = objectMapper.readValue(jsonPerson.toString(), new TypeReference<List<Person>>() {
			});

			Any jsonFireStation = jsonAny.get("firestations");
			List<FireStation> fireStations = objectMapper.readValue(jsonFireStation.toString(), new TypeReference<List<FireStation>>() {
			});

			Any jsonMedicalRecord = jsonAny.get("medicalrecords");
			List<MedicalRecord> medicalRecords = objectMapper.readValue(jsonMedicalRecord.toString(), new TypeReference<List<MedicalRecord>>() {
			});

			try {
				dataService.savePerson(persons);
				dataService.saveFireStation(fireStations);
				dataService.saveMedicalRecord(medicalRecords);
				System.out.println("Data Saved!");
			} catch (Exception ex) {
				System.out.println("An error occurred while saving data");
			}
		};
	}
}
