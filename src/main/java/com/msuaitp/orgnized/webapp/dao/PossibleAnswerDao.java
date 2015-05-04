package com.msuaitp.orgnized.webapp.dao;

import com.msuaitp.orgnized.webapp.domain.PossibleAnswer;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;

/**
 * Created by ryan_000 on 5/4/2015.
 */
public class PossibleAnswerDao {
	private final static Logger LOG = Logger.getLogger(PossibleAnswerDao.class.getName());

	RestTemplate restTemplate = new RestTemplate();

	public void saveAnswer (int question_id, String text) {
		String url = "http://reorconsultants.com:1337/possibleanswers/create";
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("question_id", String.valueOf(question_id));
		map.add("text", text);
		LOG.info(url + " " + question_id + " " + text);
		restTemplate.postForObject(url, map, PossibleAnswer.class);
	}
}
