package com.gupiao.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	
	
	public static Date getDate(String dateStr,String pattarn) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat(pattarn);
		return sdf.parse(dateStr);
	}
	
	/**
	 * 周日=1，周一=2，。。。周六=7
	 * @param dateStr
	 * @param pattarn
	 * @return
	 * @throws ParseException
	 */
	public static int getWeekDay(String dateStr,String pattarn) throws ParseException{
		Date date = getDate(dateStr, pattarn);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_WEEK);
	}
	
	
	public static void main(String[] args) throws ParseException {
		System.out.println(getWeekDay("20171216", "yyyyMMdd"));
	}
}
