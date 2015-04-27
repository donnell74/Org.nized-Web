package com.msuaitp.orgnized.webapp.controller;

import com.msuaitp.orgnized.webapp.dao.CheckinDao;
import com.msuaitp.orgnized.webapp.domain.Checkins;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

@Controller
public class GeneralController {

	CheckinDao checkinDao = new CheckinDao();

	@RequestMapping("/home")
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
	public String notes() {

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
	public String surveys() {

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

	@RequestMapping("/checkins-by-date")
	@ResponseBody
	public List<Checkins> get (
			@RequestParam(value = "date", required = true) String date, Model model) {
		List<Checkins> checkins = Arrays.asList(checkinDao.getCheckinsByDate(date));

		return checkins;
	}
}
