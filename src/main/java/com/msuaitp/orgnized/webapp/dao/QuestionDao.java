package com.msuaitp.orgnized.webapp.dao;

import com.msuaitp.orgnized.webapp.domain.Question;
import com.msuaitp.orgnized.webapp.domain.QuestionUgh;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

//import com.msuaitp.orgnized.webapp.domain.Role;

@Component
public class QuestionDao {

	RestTemplate restTemplate = new RestTemplate();

	public List<Question> getQuestionsForSurvey (String surveyid) {
		String url = "http://reorconsultants.com:1337/questions/find";
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("survey_id", surveyid);
		return Arrays.asList(restTemplate.getForEntity(url, Question[].class).getBody());
	}

	public Question findOne (String questionid) {
		String url = "http://reorconsultants.com:1337/questions/find/" + questionid;
		return restTemplate.getForObject(url, Question.class);
	}

	public void saveQuestion (Question question) {
		String url = "http://reorconsultants.com:1337/questions/create";
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("survey_id", String.valueOf(question.getSurvey_id()));
		map.add("question_text", question.getQuestion_text());
		map.add("type", String.valueOf(question.getType()));
		Question savedQuestion = restTemplate.postForObject(url, map, Question.class);

		/*url = "http://reorconsultants.com:1337/questions_roles/create";
		for(Role role : question.getRoles()){
			MultiValueMap<String, String> map2 = new LinkedMultiValueMap<String, String>();
			map2.add("question_id", String.valueOf(savedQuestion.getId()));
			map2.add("role_id", String.valueOf(role.getId()));
			restTemplate.postForObject(url, map, Void.class);
		}*/
	}

	public QuestionUgh findOneUgh (String questionid) {
		String url = "http://reorconsultants.com:1337/questions/find/" + questionid;
		return restTemplate.getForObject(url, QuestionUgh.class);
	}

	public void deleteQuestion (int question_id) {
		String url = "http://reorconsultants.com:1337/questions/destroy/" + question_id;
		restTemplate.getForObject(url, Void.class);
	}
}
