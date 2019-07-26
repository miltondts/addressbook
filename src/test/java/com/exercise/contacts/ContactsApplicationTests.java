package com.exercise.contacts;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.context.junit4.SpringRunner;

import addressbook.AddressBookApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AddressBookApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class ContactsApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	private HashMap<String, Object> testContact1;
	private String testUrl;

	private boolean contactsSame(HashMap<String, Object> m1, HashMap<String, Object> m2) {
		return m1.get("id").toString().equals(m2.get("id").toString()) && m1.get("city").equals(m2.get("city"))
				&& m1.get("country").equals(m2.get("country")) && m1.get("name").equals(m2.get("name"))
				&& m1.get("street").equals(m2.get("street")) && m1.get("zipCode").equals(m2.get("zipCode"))
				&& Arrays.asList(m1.get("phoneNumbers")).equals(Arrays.asList(m2.get("phoneNumbers")));
	}

	@Before
	public void init() {
	    restTemplate.getRestTemplate().setRequestFactory(new HttpComponentsClientHttpRequestFactory());
		testUrl = "http://localhost:" + port + "/api/v1/contacts/";
		testContact1 = new HashMap<String, Object>();
		testContact1.put("city", "Big Xity");
		testContact1.put("country", "The World");
		testContact1.put("name", "Cy D. Fect");
		testContact1.put("phoneNumbers", Arrays.asList("910910910"));
		testContact1.put("street", "Meaning road, 42");
		testContact1.put("zipCode", "0451-451");
	}

	@Test
	public void createContactNoInfoTest() throws Exception {
		HashMap<String, String> requestBody = new HashMap<String, String>();
		requestBody.put("", "");
		ResponseEntity<HashMap> response = restTemplate.postForEntity(testUrl, new HttpEntity<>(requestBody),
				HashMap.class);
		assertTrue(response.getStatusCode().equals(HttpStatus.BAD_REQUEST));
	}

	@Test
	public void createContactRequiredFieldsMissingTest() throws Exception {
		HashMap<String, String> requestBody = new HashMap<String, String>();
		requestBody.put("city", "Lisboa");
		ResponseEntity<HashMap> response = restTemplate.postForEntity(testUrl, new HttpEntity<>(requestBody),
				HashMap.class);
		assertTrue(response.getStatusCode().equals(HttpStatus.BAD_REQUEST));
	}

	@Test
	public void getContactFailureTest() throws Exception {
		ResponseEntity<HashMap> response = restTemplate.getForEntity(testUrl + "9000", HashMap.class);
		assertTrue(response.getStatusCode().equals(HttpStatus.NOT_FOUND));
	}

	@Test
	public void contactTest() throws Exception {
		// create contact
		ResponseEntity<HashMap> response = restTemplate.postForEntity(testUrl, new HttpEntity<>(testContact1),
				HashMap.class);
		assertTrue(response.getStatusCode().equals(HttpStatus.OK));
		
		// get contact
		response = restTemplate.getForEntity(testUrl + "1", HashMap.class);
		HashMap<String, Object> expected = new HashMap<>(testContact1);
		expected.put("id", "1");
		assertTrue(response.getStatusCode().equals(HttpStatus.OK));
		assertTrue(contactsSame(response.getBody(), expected));
		
		// update full
		HashMap<String, Object> requestBody = new HashMap<>(testContact1);
		requestBody.put("phoneNumbers", Arrays.asList("00910910910"));
		response = restTemplate.exchange(testUrl+"1", HttpMethod.PUT,
				new HttpEntity<>(requestBody), HashMap.class);
		assertTrue(response.getStatusCode().equals(HttpStatus.OK));
		requestBody.put("id", "1");
		assertTrue(contactsSame(response.getBody(), requestBody));
		
		// update address
		requestBody = new HashMap<>(testContact1);
		requestBody.put("city", "Big City");
		response = restTemplate.exchange(testUrl+"1/address", HttpMethod.PATCH,
				new HttpEntity<>(requestBody), HashMap.class);
		assertTrue(response.getStatusCode().equals(HttpStatus.OK));
		requestBody.put("id", "1");
		requestBody.put("phoneNumbers", Arrays.asList("00910910910"));
		assertTrue(contactsSame(response.getBody(), requestBody));
		
		// update phoneNumbers
		requestBody = new HashMap<>(testContact1);
		response = restTemplate.exchange(testUrl+"1/phoneNumbers", HttpMethod.PATCH,
				new HttpEntity<>(requestBody), HashMap.class);
		assertTrue(response.getStatusCode().equals(HttpStatus.OK));
		requestBody.put("id", "1");
		requestBody.put("city", "Big City");
		assertTrue(contactsSame(response.getBody(), requestBody));
	}
}
