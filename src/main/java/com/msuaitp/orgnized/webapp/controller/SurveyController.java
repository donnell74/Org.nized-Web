package com.msuaitp.orgnized.webapp.controller;

import com.msuaitp.orgnized.webapp.dao.SurveyDao;
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

/**
 * Created by ryan_000 on 4/28/2015.
 */

@Controller
@RequestMapping("surveys")
public class SurveyController {

	SurveyDao surveyDao = new SurveyDao();

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
		if ((!result.hasErrors()) && (principal != null)) {
			surveyDao.saveOutline(survey, principal);
			model.addAttribute("survey", survey);
			return "survey-add-questions";
		}
		return "survey-landing";
	}
}
