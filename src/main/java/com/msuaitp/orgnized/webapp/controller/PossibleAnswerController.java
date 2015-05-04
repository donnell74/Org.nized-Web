package com.msuaitp.orgnized.webapp.controller;

import com.msuaitp.orgnized.webapp.dao.PossibleAnswerDao;
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
	PossibleAnswerDao possDao = new PossibleAnswerDao();

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

		for (int i = 0; i < 10; i++) {
			model.addAttribute("poss" + i, new PossibleAnswer());
		}
		model.addAttribute("question", question);


		return "question-add-answers";
	}

	@RequestMapping("add-answers")
	public String addAnswers (@RequestParam String[] text, @RequestParam int question_id, Model model) {
		for (int i = 0; i < text.length; i++) {
			if ((!text[i].equalsIgnoreCase("")) || (text[i] != null)) {
				possDao.saveAnswer(question_id, text[i]);
			}

		}
		QuestionUgh question = questionDao.findOneUgh(String.valueOf(question_id));

		model.addAttribute("survey", surveyDao.getOneSurveyWithExtras(String.valueOf(question.getSurvey_id().getId())));
		return "redirect:/surveys/edit?id=" + question.getSurvey_id().getId();
	}
}
