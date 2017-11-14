package com.project.Utils;

import java.util.Calendar;
import java.util.Date;

public class Convert {
	
	public static Integer convertDateToDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_WEEK);
	}

}
