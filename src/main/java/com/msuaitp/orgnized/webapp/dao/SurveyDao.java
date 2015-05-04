package com.msuaitp.orgnized.webapp.dao;

import com.msuaitp.orgnized.webapp.domain.Survey;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ryan_000 on 4/29/2015.
 */
public class SurveyDao {

	RestTemplate restTemplate = new RestTemplate();

	public Survey saveOutline (Survey survey, Principal principal) {
		String url = "http://reorconsultants.com:1337/surveys";
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("creator", principal.getName());
		map.add("name", survey.getName());
		map.add("start_date", survey.getStart_date().toString());
		map.add("end_date", survey.getEnd_date().toString());
		Survey response = restTemplate.postForObject(url, map, Survey.class);
		// restTemplate.getForObject(url, Person.class);

		return response;
	}

	public List<Survey> getAllSurveys () {
		String url = "http://reorconsultants.com:1337/surveys";
		return Arrays.asList(restTemplate.getForEntity(url, Survey[].class).getBody());
	}

	public void deleteSurvey (String id) {
		String url = "http://reorconsultants.com:1337/surveys";
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("id", id);
		restTemplate.postForObject(url, map, Void.class);
	}

	public Survey getOneSurvey (String id) {
		String url = "http://reorconsultants.com:1337/surveys/" + id;
		return restTemplate.getForObject(url, Survey.class);
	}
}
