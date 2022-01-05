package com.safetynet.safetynetalerts.ControllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.safetynetalerts.controller.PersonController;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.service.DataService;
import com.safetynet.safetynetalerts.service.PersonService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@RunWith(SpringRunner.class)
@WebMvcTest(controllers = PersonController.class)
public class PersonControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PersonService personService;

	@MockBean
	private DataService dataService;

	@Test
	public void testAddPerson() throws Exception {

		RequestBuilder request = MockMvcRequestBuilders
				.post("/person/add")
				.accept(MediaType.APPLICATION_JSON)
				.content("{ \"firstName\":\"Kim\", \"lastName\":\"Boyd\", \"address\":\"1509 Culver St\", \"city\":\"Culver\", \"zip\":\"97451\", \"phone\":\"841-874-6512\", \"email\":\"jaboyd@email.com\" },\n }")
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(request)
				.andExpect(status().isOk()).andReturn();

	}
//"id": 1,
	//TODO: fix this test
	@Test
	public void testUpdatePerson() throws Exception {
	Person person = new Person();
	//person.setId(1);
	person.setFirstName("John");
	person.setLastName("Boyd");
	person.setAddress("47 rue Carnot");
	person.setCity("Hanoi");
	person.setZip(567768);
	person.setPhone("9839713");
	person.setEmail("boyd@gmail.com");

	given(personService.updatePerson(person)).willReturn(person);

	ObjectMapper mapper = new ObjectMapper();
		MvcResult result = mockMvc.perform(put("/person/update/")
				.accept(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(person))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();

	}

	@Test
	public void testDeletePerson() throws Exception {

		mockMvc.perform(delete("/person/delete/John/Boyd"))
				.andExpect(status().isOk());
	}
}
