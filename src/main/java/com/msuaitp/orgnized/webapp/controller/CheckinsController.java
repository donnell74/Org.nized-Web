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

/**
 * Created by ryan on 4/25/2015.
 */
@Controller
@RequestMapping(value = "checkin")
public class CheckinsController {

	CheckinDao checkinDao = new CheckinDao();

	@RequestMapping("/checkins-by-date")
	@ResponseBody
	public List<Checkins> get (
			@RequestParam(value = "date", required = true) String date, Model model) {
		List<Checkins> checkins = Arrays.asList(checkinDao.getCheckinsByDate(date));

		return checkins;
	}
}
