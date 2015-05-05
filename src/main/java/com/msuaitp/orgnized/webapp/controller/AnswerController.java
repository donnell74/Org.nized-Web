package com.msuaitp.orgnized.webapp.controller;

import com.msuaitp.orgnized.webapp.dao.AnswerDao;
import com.msuaitp.orgnized.webapp.dao.PossibleAnswerDao;
import com.msuaitp.orgnized.webapp.dao.QuestionDao;
import com.msuaitp.orgnized.webapp.dao.SurveyDao;
import com.msuaitp.orgnized.webapp.domain.Answer;
import com.msuaitp.orgnized.webapp.domain.Question;
import com.msuaitp.orgnized.webapp.domain.Survey;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Controller
@RequestMapping("answers")
public class AnswerController {
	private final static Logger LOG = Logger.getLogger(SurveyController.class.getName());

	SurveyDao surveyDao = new SurveyDao();
	QuestionDao questionDao = new QuestionDao();
	PossibleAnswerDao possDao = new PossibleAnswerDao();
	AnswerDao ansDao = new AnswerDao();

	@RequestMapping("view")
	public String viewRepsonses (@RequestParam int surveyid, Model model) {
		Survey survey = surveyDao.getOneSurvey(String.valueOf(surveyid));
		List<Question> questions = survey.getQuestions();
		List<Answer> answers = new ArrayList<>();
		for (Question q : questions) {
			q.setPossible_answers(possDao.getPossAnswers(q.getId()));
			if (q.getType() != Question.typeEnum.TEXT) {
				answers.addAll(ansDao.getAnswers(q.getId()));
				Map<String, Integer> counts = new HashMap<String, Integer>();
				for (Answer eachAnswer : answers) {
					String answerText = eachAnswer.getText();
					Integer answerCount = counts.get(answerText);
					if (answerCount != null) {
						counts.put(answerText, 1);
					} else {
						counts.put(answerText, ++answerCount);
					}
				}
			}

		}
		model.addAttribute("answers", answers);
		model.addAttribute("survey", survey);
		return "survey-view-response";
	}
}
