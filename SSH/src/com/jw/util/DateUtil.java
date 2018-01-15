package com.jw.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	/**
	 * Date转String
	 * @param date
	 * @param format 时间格式
	 * @return
	 */
	public static String dateToString(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		
		return sdf.format(date);
	}
}
