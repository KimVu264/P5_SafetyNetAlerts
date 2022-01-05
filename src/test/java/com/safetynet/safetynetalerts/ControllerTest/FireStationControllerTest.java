package com.safetynet.safetynetalerts.ControllerTest;

import com.safetynet.safetynetalerts.controller.FireStationController;
import com.safetynet.safetynetalerts.service.DataService;
import com.safetynet.safetynetalerts.service.FireStationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FireStationController.class)
public class FireStationControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private FireStationService fireStationService;

	@MockBean
	private DataService dataService;

	@Test
	public void testAddFireStation() throws Exception {

		RequestBuilder request = MockMvcRequestBuilders
				.post("/firestation/add")
				.accept(MediaType.APPLICATION_JSON)
				.content("{\"address\":\"1509 Culver St\", \"station\":\"3\" } \n } ")
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(request)
				.andExpect(status().isOk()).andReturn();

	}
}
