package com.msuaitp.orgnized.webapp.controller;

import com.msuaitp.orgnized.webapp.dao.PersonDao;
import com.msuaitp.orgnized.webapp.domain.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "person")
public class PersonController {

	PersonDao personDao = new PersonDao();

	@RequestMapping("/greeting")
	public String greeting(
			@RequestParam(value = "name", required = false, defaultValue = "World") String name, Model model) {
		model.addAttribute("name", name);
		return "greeting";
	}

	@RequestMapping("/name")
	public String get(
			@RequestParam(value = "email", required = false) String email, Model model) {
		Person person = personDao.getPersonByEmail(email);

		model.addAttribute("name", person.getFirst_name());
		return "greeting";
	}

	@RequestMapping("/name-string")
	@ResponseBody
	public String getString(
			@RequestParam(value = "email", required = false) String email, Model model) {
		String person = personDao.getJsonByEmail(email);
		return person;
	}
}
