package com.msuaitp.orgnized.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GeneralController {

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
	public String attendance() {

		return "attendance";
	}

	@RequestMapping("/login")
	public String login() {

		return "login";
	}

}
