package com.msuaitp.orgnized.webapp.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CheckinService {
	public String getSemester (String dateScanned) {
		String semester;
		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate cal = LocalDate.parse(dateScanned, df);

		int month = cal.getMonthValue();
		int year = cal.getYear();
		int day = cal.getDayOfMonth();

		if (month < 6) {
			semester = "SP";
		} else {
			semester = "FA";
		}

		DateTimeFormatter sdf = DateTimeFormatter.ofPattern("yy");
		String formattedDate = cal.format(sdf); // Just the year, with 2 digits
		semester += formattedDate;

		return semester;
	}
}
