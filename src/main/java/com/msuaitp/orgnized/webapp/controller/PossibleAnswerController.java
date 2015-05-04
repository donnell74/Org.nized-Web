package com.msuaitp.orgnized.webapp.controller;

import com.msuaitp.orgnized.webapp.dao.QuestionDao;
import com.msuaitp.orgnized.webapp.dao.SurveyDao;
import com.msuaitp.orgnized.webapp.domain.PossibleAnswer;
import com.msuaitp.orgnized.webapp.domain.QuestionUgh;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("poss-answers")
public class PossibleAnswerController {

	QuestionDao questionDao = new QuestionDao();
	SurveyDao surveyDao = new SurveyDao();

	@RequestMapping("new")
	public String newPossAnswers (@RequestParam int questionId, @RequestParam String questionType, Model model, final
	RedirectAttributes
			redirectAttributes) {

		QuestionUgh question = questionDao.findOneUgh(String.valueOf(questionId));

		if (questionType.equalsIgnoreCase("TEXT")) {
			redirectAttributes.addFlashAttribute("message", "Cannot add answers for text questions");

			model.addAttribute("survey", surveyDao.getOneSurvey(String.valueOf(question.getSurvey_id().getId())));
			return "survey-add-questions";
		}

		model.addAttribute("question", question);
		model.addAttribute("poss", new PossibleAnswer());

		return "question-add-answers";
	}

	public String addAnswers (@RequestParam String[] text, @RequestParam int question_id) {
		return "";
	}
}
