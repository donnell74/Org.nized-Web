package com.msuaitp.orgnized.webapp.controller;

import com.msuaitp.orgnized.webapp.dao.QuestionDao;
import com.msuaitp.orgnized.webapp.dao.SurveyDao;
import com.msuaitp.orgnized.webapp.domain.Question;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.logging.Logger;

@Controller
@RequestMapping("questions")
public class QuestionController {
	private final static Logger LOG = Logger.getLogger(CheckinsController.class.getName());

	QuestionDao questionDao = new QuestionDao();
	SurveyDao surveyDao = new SurveyDao();

	@RequestMapping("new")
	public String newQuestion (@RequestParam(value = "surveyid") String surveyid, Model model) {
		Question question = new Question();
		question.setSurvey_id(Integer.parseInt(surveyid));
		model.addAttribute("question", question);
		return "question-create";
	}

	@RequestMapping("save")
	public String saveQuestion (Model model, @Valid @ModelAttribute Question question, BindingResult result) {

		if (!result.hasErrors()) {
			LOG.info("No Errors");
			questionDao.saveQuestion(question);
			String id = String.valueOf(question.getSurvey_id());
			model.addAttribute("survey", surveyDao.getOneSurvey(id));
			return "redirect:/survey/edit?id=" + id;
		}
		LOG.info("Errors");
		return "question-create";
	}
}
