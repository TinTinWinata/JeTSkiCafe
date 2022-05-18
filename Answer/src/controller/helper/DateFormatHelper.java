package controller.helper;

import java.sql.Date;

public class DateFormatHelper {
	private static final String[] MONTHS = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
	private static final String[] DAYS = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
	
	public static String getLongDate(Date date) {
		@SuppressWarnings("deprecation")
		String year = (date.getYear() + 1900)+"";
		@SuppressWarnings("deprecation")
		String month = MONTHS[date.getMonth()];
		@SuppressWarnings("deprecation")
		String dateInt = date.getDate()+"";
		@SuppressWarnings("deprecation")
		String day = DAYS[date.getDay()];
		
		String longDate = String.format("%s, %s %s %s", day, dateInt, month, year);
		return longDate;
	}
}
