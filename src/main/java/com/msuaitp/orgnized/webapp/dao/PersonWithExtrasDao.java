package com.msuaitp.orgnized.webapp.dao;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.msuaitp.orgnized.webapp.domain.Person;

@Component
public class PersonWithExtrasDao {
	
	RestTemplate restTemplate = new RestTemplate();
	
	public Person[] getPersonByEmail(String email) {
		String urlInit = "http://reorconsultants.com:1337/person/findWithExtras/";
		StringBuilder urlBuilder = new StringBuilder(urlInit);
		urlBuilder.append(email);
		String url = urlBuilder.toString();
		System.out.println(url);
		Person[] person = restTemplate.getForObject(url, Person[].class);
		
		return person;
	}
	
	public String getJsonByEmail(String email) {
		String urlInit = "http://reorconsultants.com:1337/person/findWithExtras/";
		StringBuilder urlBuilder = new StringBuilder(urlInit);
		urlBuilder.append(email);
		String url = urlBuilder.toString();
		System.out.println(url);
		String response = restTemplate.getForObject(url, String.class);
				//restTemplate.getForObject(url, Person.class);
		
		return response;
	}
	
}
