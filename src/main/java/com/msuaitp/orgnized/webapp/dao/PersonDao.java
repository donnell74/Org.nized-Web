package com.msuaitp.orgnized.webapp.dao;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.msuaitp.orgnized.webapp.domain.Person;

@Component
public class PersonDao {

	RestTemplate restTemplate = new RestTemplate();

	public Person getPersonByEmail(String email) {
		String urlInit = "http://reorconsultants.com:1337/person/getFirstPerson/";
		StringBuilder urlBuilder = new StringBuilder(urlInit);
		urlBuilder.append(email);
		String url = urlBuilder.toString();
		System.out.println(url);
		Person person = restTemplate.getForObject(url, Person.class);

		return person;
	}

	public String getJsonByEmail(String email) {
		String urlInit = "http://reorconsultants.com:1337/person/find/";
		StringBuilder urlBuilder = new StringBuilder(urlInit);
		urlBuilder.append(email);
		String url = urlBuilder.toString();
		System.out.println(url);
		String response = restTemplate.getForObject(url, String.class);
		// restTemplate.getForObject(url, Person.class);

		return response;
	}

	public Person findOneByUsername(String username) {
		return getPersonByEmail(username);

	}

}
