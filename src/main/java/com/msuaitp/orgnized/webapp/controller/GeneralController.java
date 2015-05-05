package com.msuaitp.orgnized.webapp.controller;

import com.msuaitp.orgnized.webapp.dao.CheckinDao;
import com.msuaitp.orgnized.webapp.dao.NotesDao;
import com.msuaitp.orgnized.webapp.dao.SurveyDao;
import com.msuaitp.orgnized.webapp.domain.Note;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class GeneralController {

	CheckinDao checkinDao = new CheckinDao();
	NotesDao notesDao = new NotesDao();
	SurveyDao surveyDao = new SurveyDao();

	@RequestMapping(value = {"/", "/home"})
	public String home() {

		return "home";
	}

	@RequestMapping("/admin")
	public String admin() {

		return "admin";
	}

	@RequestMapping("/announcements")
	public String announcements() {

		return "announcements";
	}

	@RequestMapping("/classbonus")
	public String class_bonus() {

		return "classbonus";
	}

	@RequestMapping("/notes")
	public String notes (Model model) {
		List<Note> theNotes = Arrays.asList(notesDao.getAllNotes());
		model.addAttribute("theNotes", theNotes);

		return "notes";
	}

	@RequestMapping("/people")
	public String people() {

		return "people";
	}

	@RequestMapping("/profile")
	public String profile() {

		return "profile";
	}

	@RequestMapping("/surveys")
	public String surveys (Model model) {
		model.addAttribute("surveys", surveyDao.getAllSurveys());

		return "surveys";
	}

	@RequestMapping("/attendance")
	public String attendance (Model model) {
		model.addAttribute("allDates", checkinDao.getAllDates());

		return "attendance";
	}

	@RequestMapping("/login")
	public String login() {

		return "login";
	}
}
