package com.msuaitp.orgnized.webapp.dao;

import com.msuaitp.orgnized.webapp.domain.Checkins;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CheckinWithExtrasDao {

	RestTemplate restTemplate = new RestTemplate();

	public Checkins[] getPersonByEmail (String email) {
		String urlInit = "http://reorconsultants.com:1337/checkin/findWithExtras/";
		StringBuilder urlBuilder = new StringBuilder(urlInit);
		urlBuilder.append(email);
		String url = urlBuilder.toString();
		System.out.println(url);
		Checkins[] person = restTemplate.getForObject(url, Checkins[].class);

		return person;
	}

	public String getJsonByEmail (String email) {
		String urlInit = "http://reorconsultants.com:1337/checkin/findWithExtras/";
		StringBuilder urlBuilder = new StringBuilder(urlInit);
		urlBuilder.append(email);
		String url = urlBuilder.toString();
		System.out.println(url);
		String response = restTemplate.getForObject(url, String.class);
		//restTemplate.getForObject(url, Person.class);

		return response;
	}

}