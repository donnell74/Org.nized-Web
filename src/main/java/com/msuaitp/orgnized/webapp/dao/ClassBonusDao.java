package com.msuaitp.orgnized.webapp.dao;

import com.msuaitp.orgnized.webapp.domain.ClassBonus;
import com.msuaitp.orgnized.webapp.domain.Person;
import com.msuaitp.orgnized.webapp.domain.PersonClassBonus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

@Component
public class ClassBonusDao {
	private final static Logger LOG = Logger.getLogger(ClassBonusDao.class.getName());
	RestTemplate restTemplate = new RestTemplate();

	public List<Person> getPeopleByClassBonus (String courseCode, String semester) {
		String url = "http://reorconsultants.com:1337/classbonus/getPersonsByClassBonus";
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("course_code", courseCode);
		map.add("semester", semester);
		ResponseEntity<PersonClassBonus[]> response = restTemplate.postForEntity(url, map, PersonClassBonus[].class);
		List<Person> people = new ArrayList<Person>();
		for (PersonClassBonus pcb : response.getBody()) {
			LOG.info(pcb.getEmail().getLast_name());
			people.add(pcb.getEmail());
		}
		return people;
	}

	public List<ClassBonus> getAllClassBonus () {
		String url = "http://reorconsultants.com:1337/classbonus";
		LOG.info(url);
		ResponseEntity<ClassBonus[]> response = restTemplate.getForEntity(url, ClassBonus[].class);
		LOG.info("got bonuses - dao");

		return Arrays.asList(response.getBody());
	}
}
