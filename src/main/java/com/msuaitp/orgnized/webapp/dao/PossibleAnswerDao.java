package com.msuaitp.orgnized.webapp.dao;

import com.msuaitp.orgnized.webapp.domain.PossibleAnswer;
import com.msuaitp.orgnized.webapp.domain.PossibleAnswerUgh;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

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

	public void saveAnswer (int question_id, int answer_id, String text) {
		String url = "http://reorconsultants.com:1337/possibleanswers/update/" + answer_id;
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("question_id", String.valueOf(question_id));
		map.add("text", text);
		LOG.info(url + " " + question_id + " " + text);
		restTemplate.postForObject(url, map, PossibleAnswer.class);
	}

	public List<PossibleAnswer> getPossAnswers (int question_id) {
		String url = "http://reorconsultants.com:1337/possibleanswers/find";
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("question_id", String.valueOf(question_id));
		LOG.info(url + " " + question_id);
		return Arrays.asList(restTemplate.postForEntity(url, map, PossibleAnswer[].class).getBody());
	}

	public List<PossibleAnswerUgh> getPossAnswersWithExtras (int question_id) {
		String url = "http://reorconsultants.com:1337/possibleanswers/find";
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("question_id", String.valueOf(question_id));
		LOG.info(url + " " + question_id);
		return Arrays.asList(restTemplate.postForEntity(url, map, PossibleAnswerUgh[].class).getBody());
	}

	public PossibleAnswer getOne (int answer_id) {
		String url = "http://reorconsultants.com:1337/possibleanswers/find";
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("id", String.valueOf(answer_id));
		LOG.info(url + " " + answer_id);
		return restTemplate.postForEntity(url, map, PossibleAnswer.class).getBody();
	}

	public void delete (int answer_id) {
		String url = "http://reorconsultants.com:1337/possibleanswers/destroy/" + answer_id;
		restTemplate.getForObject(url, Void.class);
	}
}
