package com.msuaitp.orgnized.webapp.controller;

import com.msuaitp.orgnized.webapp.dao.CheckinDao;
import com.msuaitp.orgnized.webapp.dao.ClassBonusDao;
import com.msuaitp.orgnized.webapp.domain.Checkins;
import com.msuaitp.orgnized.webapp.domain.ClassBonus;
import com.msuaitp.orgnized.webapp.domain.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;
import java.util.logging.Logger;

@Controller
@RequestMapping(value = "checkin")
public class CheckinsController {
	private final static Logger LOG = Logger.getLogger(CheckinsController.class.getName());

	CheckinDao checkinDao = new CheckinDao();
	ClassBonusDao classBonusDao = new ClassBonusDao();
	CheckinService checkinSvc = new CheckinService();

	@RequestMapping("/checkins-by-date")
	@ResponseBody
	public List<Checkins> getCheckinsByDate (
			@RequestParam(value = "date", required = true) String date, Model model) {
		List<Checkins> checkins = Arrays.asList(checkinDao.getCheckinsByDate(date));

		return checkins;
	}

	@RequestMapping("/attend-by-date")
	@ResponseBody
	public Map<String, Integer> getAttendByDate (
			@RequestParam(value = "date", required = true) String date, Model model) {
		List<Checkins> checkins = Arrays.asList(checkinDao.getCheckinsByDate(date));

		int total = 0;
		int members = 0;
		int guests = 0;

		for (Checkins chk : checkins) {
			total++;
			if (chk.getEmail().isIs_member()) {
				members++;
			} else {
				guests++;
			}
		}

		Map<String, Integer> numbers = new HashMap<>();
		numbers.put("total", total);
		numbers.put("member", members);
		numbers.put("general", guests);

		return numbers;
	}

	@ResponseBody
	@RequestMapping("/person-by-classBonus")
	public Map<String, List<Person>> getPeopleByClassBonus (@RequestParam(value = "date") String date, Model model) {
		Map<String, List<Person>> allTheBonuses = new HashMap<>();
		List<ClassBonus> allClasses = classBonusDao.getAllClassBonus();
		LOG.info("Got all class bonus");
		List<Checkins> checkins = Arrays.asList(checkinDao.getCheckinsByDate(date));
		List<Person> candidates = new ArrayList<>();
		for (Checkins chk : checkins) {
			candidates.add(chk.getEmail());
		}

		for (ClassBonus clazz : allClasses) {
			List<Person> listOfPeople = classBonusDao.getPeopleByClassBonus(clazz.getCourseCode(), checkinSvc
					.getSemester(date));

			listOfPeople.retainAll(candidates);

			if (!listOfPeople.isEmpty()) {
				LOG.info("Class Added to list: " + clazz.getCourse_code());
				allTheBonuses.put(clazz.getCourse_code(), listOfPeople);
			}
		}
		return allTheBonuses;
	}
}