package com.msuaitp.orgnized.webapp.dao;

import com.msuaitp.orgnized.webapp.domain.Answer;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class AnswerDao {

	RestTemplate restTemplate = new RestTemplate();

	public List<Answer> getAnswers (int question_id) {
		String url = "http://reorconsultants.com:1337/questions/find";
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("question_id", String.valueOf(question_id));
		return Arrays.asList(restTemplate.getForEntity(url, Answer[].class).getBody());
	}
}
