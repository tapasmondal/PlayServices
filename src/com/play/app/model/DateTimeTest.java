package com.play.app.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

public class DateTimeTest {
	
	public static void main(String[] args) {
		/*Calendar currentdate = Calendar.getInstance();
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		TimeZone obj = TimeZone.getTimeZone("CST");
		TimeZone obj1 = TimeZone.getTimeZone("IST");
		formatter.setTimeZone(obj);
		
		//System.out.println("Local:: " +currentdate.getTime());
		//System.out.println("CST  :: "+ formatter.format(currentdate.getTime()));
		formatter.setTimeZone(obj1);
		//System.out.println("IST  :: "+ formatter.format(currentdate.getTime()));
		
		DateTimeZone dtz = DateTimeZone.forID("IST");
		Date endDate =new DateTime(dtz)
				.withYear(2017)
				.withMonthOfYear(4)
				.withDayOfMonth(9)
			    .withHourOfDay(05)
			    .withMinuteOfHour(00)
			    .withSecondOfMinute(00).toDate();
		
		System.out.println("IST  :: "+ formatter.format(endDate));*/
		DateFormat formatter1 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		
		Calendar calendar = Calendar.getInstance();;
		Long t1=calendar.getTimeInMillis();
		System.out.println("time  :: "+ formatter1.format(calendar.getTime()));
		calendar.roll(Calendar.SECOND,-324000);
		Long t2=t1-32400000;
		calendar.setTimeInMillis(t2);
		System.out.println("time  :: "+ formatter1.format(calendar.getTime()));
		
		calendar= Calendar.getInstance();;
		TimeZone obj1 = TimeZone.getTimeZone("IST");
		formatter1.setTimeZone(obj1);;
		System.out.println("time  :: "+ formatter1.format(calendar.getTime()));
		
		
	}

}
