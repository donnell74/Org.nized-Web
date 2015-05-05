package com.msuaitp.orgnized.webapp.controller;

import com.msuaitp.orgnized.webapp.dao.PossibleAnswerDao;
import com.msuaitp.orgnized.webapp.dao.QuestionDao;
import com.msuaitp.orgnized.webapp.dao.SurveyDao;
import com.msuaitp.orgnized.webapp.domain.Question;
import com.msuaitp.orgnized.webapp.domain.Survey;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("surveys")
public class SurveyController {
	private final static Logger LOG = Logger.getLogger(SurveyController.class.getName());

	SurveyDao surveyDao = new SurveyDao();
	QuestionDao questionDao = new QuestionDao();
	PossibleAnswerDao possDao = new PossibleAnswerDao();

	@InitBinder
	public void initBinder (final WebDataBinder binder) {
		final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	@RequestMapping("create")
	public String createSurveyLanding (Model model) {
		Survey survey = new Survey();
		model.addAttribute("survey", survey);
		return "survey-landing";
	}

	@RequestMapping("create-outline")
	public String createSurveyOutline (Model model, @Valid @ModelAttribute Survey survey, BindingResult result, Principal
			principal) {
		if (survey.getEnd_date().before(survey.getStart_date())) {
			result.rejectValue("end_date", "before");
		}
		if ((!result.hasErrors()) && (principal != null)) {
			surveyDao.saveOutline(survey, principal);
			List<Question> questions = survey.getQuestions();
			for (Question q : questions) {
				q.setPossible_answers(possDao.getPossAnswers(q.getId()));
				LOG.info("Iterated thru PA Loop");
			}
			model.addAttribute("survey", survey);
			return "survey-add-questions";
		}
		return "survey-landing";
	}

	@RequestMapping("delete")
	public String deleteSurvey (Model model, String id) {
		surveyDao.deleteSurvey(id);
		model.addAttribute("surveys", surveyDao.getAllSurveys());
		return "redirect:/surveys";
	}

	@RequestMapping("edit")
	public String editSurvey (Model model, String id) {
		Survey survey = surveyDao.getOneSurvey(id);
		List<Question> questions = survey.getQuestions();
		for (Question q : questions) {
			q.setPossible_answers(possDao.getPossAnswers(q.getId()));
			LOG.info("Iterated thru PA Loop");
		}
		model.addAttribute("survey", survey);
		return "survey-add-questions";
	}
}
