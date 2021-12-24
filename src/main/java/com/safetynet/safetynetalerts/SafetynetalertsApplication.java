package com.safetynet.safetynetalerts;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;
import com.safetynet.safetynetalerts.model.FireStation;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.service.DataService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;

@SpringBootApplication
public class SafetynetalertsApplication {

	public static void main(String[] args)
	{
		SpringApplication.run(SafetynetalertsApplication.class, args);
	}
	/*
	public int calc(JsonIterator iter) throws IOException
	{
		Any obj = JsonIterator.deserialize("[1,2,3]");
		int totalTagsCount = 0;
		for(String field = iter.readObject(); field != null; field = iter.readObject())
		{
			switch(field)
			{
				case "persons":
				case "firestations":
				case "medicalrecords":
					while(iter.readArray())
						iter.skip();
					totalTagsCount++;
					break;

				default:
					iter.skip();
			}
		}
			return totalTagsCount;
		}
*/
	@Bean
	CommandLineRunner runner(DataService dataService) {
		return args -> {
			// read json and write to db
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

				System.out.println("Users Saved!");

			} catch (IOException e){
				System.out.println("Unable to save users: " + e.getMessage());
			}
		};
	}
}
