package com.play.app.facade;

import java.util.Date;

import org.joda.time.DateTime;

public class DateUtils {

	public static Date getCurrentMonthStart() {
		DateTime now = new DateTime();
		DateTime currentMonth = now.withDayOfMonth(1);
		return currentMonth.toDate();
	}

	public static Date getCurrentMonthEnd() {
		DateTime now = new DateTime();
		return now.dayOfMonth().withMaximumValue().toDate();
	}

	public static Date getMonthStart(int month) {
		DateTime now = new DateTime().withMonthOfYear(month);
		DateTime currentMonth = now.withDayOfMonth(1);
		return currentMonth.toDate();
	}

	public static Date getMonthEnd(int month) {
		DateTime now = new DateTime().withMonthOfYear(month);
		return now.dayOfMonth().withMaximumValue().toDate();
	}

	public static Date getDayStartDate(Date date) {
		return new DateTime(date).millisOfDay().withMinimumValue().toDate();
	}

	public static Date getDayEndDate(Date date) {
		return new DateTime(date).millisOfDay().withMaximumValue().toDate();
	}
	
	public static Date getCurrentLocalTime() {
		DateTime now = new DateTime();
		System.out.println(now.toString());
		if(!now.toString().contains("GST")) {
			now.withHourOfDay(-10);
		}		
		return now.toDate();
	}
	
	public static Date getLocalTimeOfDate(Date date) {
		DateTime now = new DateTime(date);
		System.out.println(now.toString());
		if(!now.toString().contains("GST")) {
			now.minusDays(1).plusHours(14);
		}		
		return now.toDate();
	}
}
