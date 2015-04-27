package com.msuaitp.orgnized.webapp.dao;

import com.msuaitp.orgnized.webapp.domain.Checkins;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
public class CheckinDao {

	RestTemplate restTemplate = new RestTemplate();

	public Checkins[] getCheckinsByDate (String date) {
		String url = "http://reorconsultants.com:1337/checkins/getCheckinsByDate";
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("date", date);
		System.out.println(url);
		ResponseEntity<Checkins[]> checkins = restTemplate.postForEntity(url, map, Checkins[].class);

		return checkins.getBody();
	}

	public String getJsonByDate (String date) {
		String url = "http://reorconsultants.com:1337/checkins/getCheckinsByDate";
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("date", date);
		System.out.println(url);
		String response = restTemplate.postForObject(url, map, String.class);

		return response;
	}

	public String[] getAllDates () {
		String url = "http://reorconsultants.com:1337/checkins/getallCheckinDates/";
		System.out.println(url);
		ResponseEntity<String[]> response = restTemplate.getForEntity(url, String[].class);
		// restTemplate.getForObject(url, Person.class);

		return response.getBody();
	}

}