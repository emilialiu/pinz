package com.dimine.sys.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author Aaron 2014-12-29
 */
public class DateUtil {
	/**
	 * 将timestamp转换成String
	 * 
	 * @param timestamp
	 * @return
	 */
	public static String timestampToStr(Timestamp timestamp) {
		return DateUtil.timestampToStr(timestamp, null);
	}

	private final static SimpleDateFormat shortSdf = new SimpleDateFormat(
			"yyyy-MM-dd");
	private final static SimpleDateFormat longSdf = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");;

	/**
	 * 将timestamp转换成String 将日期格式转换为"yyyy-MM-dd HH:mm:ss"
	 * 
	 * @param timestamp
	 * @return
	 */
	public static String timestampToStr(Timestamp timestamp, String aMask) {
		String ret = null;
		String mask = aMask;
		if (mask == null || "".equals(mask))
			mask = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat sdf = new SimpleDateFormat(mask);
		ret = sdf.format(timestamp);
		return ret;
	}

	/**
	 * 一月的最后一天
	 * 
	 * @param today
	 * @return
	 */
	public static Date lastDayInTheMonth(Date today) {
		int year = Integer.parseInt(getYear());
		int month = Integer.parseInt(getMonth()) - 1;
		int day = Integer.parseInt(getDay());
		Calendar calendar = new GregorianCalendar(year, month, day);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		Date fromDate = calendar.getTime();
		calendar.add(Calendar.MONTH, 1);
		Date toDate = calendar.getTime();
		int diffdays = DateUtil.diffDayTime(fromDate, toDate);
		calendar.add(Calendar.MONTH, -1);
		calendar.add(Calendar.DAY_OF_MONTH, diffdays - 1);
		return calendar.getTime();
	}

	/**
	 * 一月的第一天
	 * 
	 * @param today
	 * @return
	 */
	public static Date firstDayInTheMonth(Date today) {
		int year = Integer.parseInt(getYear());
		int month = Integer.parseInt(getMonth()) - 1;
		int day = Integer.parseInt(getDay());
		Calendar calendar = new GregorianCalendar(year, month, day);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}

	/**
	 * 将date转换成String 将日期格式转换为"yyyy-MM-dd"
	 * 
	 * @param timestamp
	 * @return
	 */
	public static String dateToStr(Date date) {
		return DateUtil.dateToStr(date, null);
	}

	/**
	 * 将date转换成String
	 * 
	 * @param date
	 * @param aMask
	 *            ,指定的是转换成的格式. 可以为空,为空时,将日期格式转换为"yyyy-MM-dd"
	 * @return
	 */
	public static String dateToStr(Date date, String aMask) {
		if (date == null) {
			return "";
		}
		String ret = null;
		String mask = aMask;
		if (mask == null || "".equals(mask))
			mask = "yyyy-MM-dd";
		SimpleDateFormat sdf = new SimpleDateFormat(mask);
		ret = sdf.format(date);
		return ret;
	}

	/**
	 * 将String转换成Date, 将日期格式转换为"yyyy-MM-dd"
	 * 
	 * @param timestamp
	 * @return
	 */
	public static java.util.Date strToDate(String date) {
		if ("".equals(date) || date == null)
			return null;
		return DateUtil.strToDate(date, null);
	}

	/**
	 * 将String转换成Date,如果aMask为NULL，那么其默认格式yyyy-MM-dd
	 * 
	 * @param date
	 * @param aMask
	 * @return
	 */
	public static java.util.Date strToDate(String date, String aMask) {
		java.util.Date ret = null;
		String mask = aMask;
		if (mask == null || "".equals(mask))
			mask = "yyyy-MM-dd";
		SimpleDateFormat sdf = new SimpleDateFormat(mask);
		try {
			ret = sdf.parse(date);
		} catch (ParseException e) {

		}
		return ret;
	}

	public static String lpadnum(int num, int length) {
		int len = String.valueOf(num).length();
		StringBuffer str = new StringBuffer("");
		if (len < length) {
			for (int i = 0; i < length - len; i++) {
				str.append("0");
			}
			return str.append(num).toString();
		} else {
			return String.valueOf(num);
		}
	}

	/**
	 * 返回当前的GregorianCalendar
	 * 
	 * @return
	 */
	public static Calendar getToday() {
		return new GregorianCalendar();
	}

	/**
	 * 返回当前日期的年
	 * 
	 * @return
	 */
	public static String getYear() {
		Calendar calendar = DateUtil.getToday();
		return String.valueOf(calendar.get(Calendar.YEAR));
	}

	/**
	 * 返回当前日期的月
	 * 
	 * @return
	 */
	public static String getMonth() {
		Calendar calendar = DateUtil.getToday();
		int month = calendar.get(Calendar.MONTH) + 1;
		return DateUtil.lpadnum(month, 2);
	}

	/**
	 * 获取季度 申生明添加
	 * 
	 * @return
	 */
	public static String getQuarter() {
		String quarter = "";
		Calendar calendar = DateUtil.getToday();
		String year = String.valueOf(calendar.get(Calendar.YEAR));
		int month = calendar.get(Calendar.MONTH) + 1;
		if (month >= 1 && month <= 3) {
			quarter = year + "-01";
		} else if (month >= 4 && month <= 6) {
			quarter = year + "-02";
		} else if (month >= 7 && month <= 9) {
			quarter = year + "-03";
		} else if (month >= 10 && month <= 12) {
			quarter = year + "-04";
		}
		return quarter;
	}

	/**
	 * 返回当前日期的天
	 * 
	 * @return
	 */
	public static String getDay() {
		Calendar calendar = DateUtil.getToday();
		return DateUtil.lpadnum(calendar.get(Calendar.DAY_OF_MONTH), 2);
	}

	/**
	 * 返回当前日期的小时数
	 * 
	 * @return
	 */
	public static String getHour() {
		Calendar calendar = DateUtil.getToday();
		return DateUtil.lpadnum(calendar.get(Calendar.HOUR_OF_DAY), 2);
	}

	/**
	 * 返回当前日期的分钟
	 * 
	 * @return
	 */
	public static String getMinute() {
		Calendar calendar = DateUtil.getToday();
		return DateUtil.lpadnum(calendar.get(Calendar.MINUTE), 2);
	}

	/**
	 * 返回当前日期的秒钟
	 * 
	 * @return
	 */
	public static String getSecond() {
		Calendar calendar = DateUtil.getToday();
		return DateUtil.lpadnum(calendar.get(Calendar.SECOND), 2);
	}

	/**
	 * 返回当前日期
	 * 
	 * @return
	 */
	public static String getDate() {
		return DateUtil.getYear() + "-" + DateUtil.getMonth() + "-"
				+ DateUtil.getDay();
	}

	/**
	 * 返回带时间的日期
	 * 
	 * @return
	 */
	public static String getDateWithTime() {
		return DateUtil.getDate() + " " + DateUtil.getHour() + ":"
				+ DateUtil.getMinute() + ":" + DateUtil.getSecond();
	}

	/**
	 * 
	 * @param date
	 * @param mode为Calendar里面的参数
	 * @return
	 */
	public static String get(java.util.Date date, int mode) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setGregorianChange(date);
		return String.valueOf(DateUtil.lpadnum(calendar.get(mode), 2));
	}

	/**
	 * 将一个日期增加多少天
	 * 
	 * @param date
	 * @param day
	 * @return
	 */
	public static String addDay(java.util.Date date, int day) {
		return DateUtil.add(date, day, "day");
	}

	/**
	 * 将一个日期增加多少天
	 * 
	 * @param date
	 * @param day
	 * @return
	 */
	public static String addDay(String date, int day) {
		return DateUtil.addDay(DateUtil.strToDate(date), day);
	}

	/**
	 * @将一个日期增加多少天,并根据aMask格式返回String
	 * @param date
	 * @param day
	 * @param aMask
	 * @Return:String
	 * @Author:WenZhao.Zhang
	 */
	public static String addDay(Date date, int day, String aMask) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		ca.add(Calendar.DAY_OF_MONTH, -1);
		String result = dateToStr(ca.getTime(), aMask);
		return result;
	}

	/**
	 * 将一个日期增加多少个月
	 * 
	 * @param date
	 * @param month
	 * @return
	 */
	public static String addMonth(java.util.Date date, int month) {
		return DateUtil.add(date, month, "month");
	}

	/**
	 * 将一个日期增加多少个月
	 * 
	 * @param date
	 * @param month
	 * @return
	 */
	public static String addMonth(String date, int month) {
		return DateUtil.addMonth(DateUtil.strToDate(date), month);
	}

	/**
	 * 判断时间是否相等
	 * 
	 * @param atime
	 * @param btime
	 * @return
	 */
	public static boolean equalsDate(java.util.Date atime, java.util.Date btime) {
		GregorianCalendar c1 = new GregorianCalendar();
		GregorianCalendar c2 = new GregorianCalendar();
		c1.setTime(atime);
		c2.setTime(btime);

		if (c1.compareTo(c2) == 0) {
			return true;
		}
		return false;

		// GregorianCalendar acalc = (GregorianCalendar) getToday();
		// GregorianCalendar bcalc = (GregorianCalendar) getToday();
		// acalc.setTime(atime);
		// bcalc.setTime(btime);
		// if (acalc.after(bcalc)) {
		// return true;
		// } else {
		// if (!acalc.before(bcalc)) {
		// return true;
		// } else {
		// return false;
		// }
		// }
	}

	/**
	 * 判断时间是否相等
	 * 
	 * @param atime
	 * @param btime
	 * @return
	 */
	public static boolean equalsDate(String atime, String btime) {
		return equalsDate(strToDate(atime), strToDate(btime));
	}

	public static String add(java.util.Date date, int num, String mode) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		if (mode.equalsIgnoreCase("month")) {
			calendar.add(Calendar.MONTH, num);
		} else {
			calendar.add(Calendar.DAY_OF_MONTH, num);
		}

		return String.valueOf(calendar.get(Calendar.YEAR)) + "-"
				+ DateUtil.lpadnum(calendar.get(Calendar.MONTH) + 1, 2) + "-"
				+ DateUtil.lpadnum(calendar.get(Calendar.DAY_OF_MONTH), 2);
	}

	/**
	 * 返回当前时间 dominic
	 * 
	 * @return 返回当前时间
	 */
	public static Date getCurrentDateTime() {
		java.util.Calendar calNow = java.util.Calendar.getInstance();
		java.util.Date dtNow = calNow.getTime();

		return dtNow;
	}

	/**
	 * 时间差 dominic
	 * 
	 * @return 返回两个时间差几秒
	 */
	public static int diffTime(Date date1, Date date2) {
		GregorianCalendar c1 = new GregorianCalendar();
		c1.setTime(date1);
		GregorianCalendar c2 = new GregorianCalendar();
		c2.setTime(date2);
		double timeLong = c2.getTimeInMillis() - c1.getTimeInMillis();
		return (int) timeLong / 1000;
	}

	/**
	 * 时间差 鲁光源
	 * 
	 * @param fromDate
	 * @param toDate
	 * @return 返回两个时间差几天
	 */
	public static int diffDayTime(Date fromDate, Date toDate) {
		if (toDate == null) {
			return 0;
		}
		// 现将日期精确到天
		fromDate = DateUtil.strToDate(DateUtil.dateToStr(fromDate));
		toDate = DateUtil.strToDate(DateUtil.dateToStr(toDate));

		GregorianCalendar c1 = new GregorianCalendar();
		c1.setTime(fromDate);
		GregorianCalendar c2 = new GregorianCalendar();
		c2.setTime(toDate);
		long timeLong = c2.getTimeInMillis() - c1.getTimeInMillis();

		return (int) (timeLong / 1000 / (24 * 60 * 60));
	}

	// 以下匡剑锋添加
	/**
	 * 判断是否当前周
	 * 
	 * @param time
	 * @return
	 */
	public static boolean isThisWeek(long time) {
		Calendar calendar = Calendar.getInstance();
		int currentWeek = calendar.get(Calendar.WEEK_OF_YEAR);
		calendar.setTime(new Date(time));
		int paramWeek = calendar.get(Calendar.WEEK_OF_YEAR);
		if (paramWeek == currentWeek) {
			return true;
		}
		return false;
	}

	/**
	 * 判断选择的日期是否是今天
	 * 
	 * @param time
	 * @return
	 */
	public static boolean isToday(long time) {
		return isThisTime(time, "yyyy-MM-dd");
	}

	/**
	 * 判断是否当前月
	 * 
	 * @param time
	 * @return
	 */
	public static boolean isThisMonth(long time) {
		return isThisTime(time, "yyyy-MM");
	}

	/**
	 * 判断当前时间
	 * 
	 * @param time
	 * @param pattern
	 * @return
	 */
	private static boolean isThisTime(long time, String pattern) {
		Date date = new Date(time);
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		String param = sdf.format(date);// 参数时间
		String now = sdf.format(new Date());// 当前时间
		if (param.equals(now)) {
			return true;
		}
		return false;
	}

	/**
	 * 获取某年某月的某周的第一天（星期一）
	 * 
	 * @param year
	 * @param month
	 * @param week
	 * @return
	 */
	public static String getFirstDate(String year, String month, String week) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, Integer.parseInt(year));// 设置年份
		c.set(Calendar.MONTH, Integer.parseInt(month) - 1);// 设置月份
		c.set(Calendar.WEEK_OF_MONTH, Integer.parseInt(week));// 设置周
		c.set(Calendar.DAY_OF_WEEK, 2);// 拿到星期一
		String day2 = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
		return day2;
	}

	/**
	 * 当前季度的开始时间
	 * 
	 * @return
	 */
	public static Date getCurrentQuarterStartTime() {
		Calendar c = Calendar.getInstance();
		int currentMonth = c.get(Calendar.MONTH) + 1;
		Date now = null;
		try {
			if (currentMonth >= 1 && currentMonth <= 3)
				c.set(Calendar.MONTH, 0);
			else if (currentMonth >= 4 && currentMonth <= 6)
				c.set(Calendar.MONTH, 3);
			else if (currentMonth >= 7 && currentMonth <= 9)
				c.set(Calendar.MONTH, 4);
			else if (currentMonth >= 10 && currentMonth <= 12)
				c.set(Calendar.MONTH, 9);
			c.set(Calendar.DATE, 1);
			now = longSdf.parse(shortSdf.format(c.getTime()) + " 00:00:00");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return now;
	}

	/**
	 * 当前季度的结束时间
	 * 
	 * @return
	 */
	public static Date getCurrentQuarterEndTime() {
		Calendar c = Calendar.getInstance();
		int currentMonth = c.get(Calendar.MONTH) + 1;
		Date now = null;
		try {
			if (currentMonth >= 1 && currentMonth <= 3) {
				c.set(Calendar.MONTH, 2);
				c.set(Calendar.DATE, 31);
			} else if (currentMonth >= 4 && currentMonth <= 6) {
				c.set(Calendar.MONTH, 5);
				c.set(Calendar.DATE, 30);
			} else if (currentMonth >= 7 && currentMonth <= 9) {
				c.set(Calendar.MONTH, 8);
				c.set(Calendar.DATE, 30);
			} else if (currentMonth >= 10 && currentMonth <= 12) {
				c.set(Calendar.MONTH, 11);
				c.set(Calendar.DATE, 31);
			}
			now = longSdf.parse(shortSdf.format(c.getTime()) + " 23:59:59");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return now;
	}

	/**
	 * 根据当前日期返回当前季度
	 * @param date
	 * @return
	 */
	public static int getSeason(Date date) {
		int season = 0;
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int month = c.get(Calendar.MONTH);
		switch (month) {
		case Calendar.JANUARY:
		case Calendar.FEBRUARY:
		case Calendar.MARCH:
			season = 1;
			break;
		case Calendar.APRIL:
		case Calendar.MAY:
		case Calendar.JUNE:
			season = 2;
			break;
		case Calendar.JULY:
		case Calendar.AUGUST:
		case Calendar.SEPTEMBER:
			season = 3;
			break;
		case Calendar.OCTOBER:
		case Calendar.NOVEMBER:
		case Calendar.DECEMBER:
			season = 4;
			break;
		default:
			break;
		}
		return season;
	}

	public static void main(String[] args) {
		System.out.println(DateUtil.dateToStr(DateUtil
				.lastDayInTheMonth(new Date())));
	}

}
