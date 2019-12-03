package com.topshow.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;

public class DateUtils {
    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String MINUTE_PATTERN = "yyyy-MM-dd HH:mm";
    public static final String HOUR_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_PATTERN = "yyyy-MM-dd";
    public static final String MONTH_PATTERN = "yyyy-MM";
    public static final String YEAR_PATTERN = "yyyy";
    public static final String MINUTE_ONLY_PATTERN = "mm";
    public static final String HOUR_ONLY_PATTERN = "HH";

    public static Date dateAdd(Date date, int days, boolean includeTime) throws ParseException {
        if (date == null) {
            date = new Date();
        }
        if (!includeTime) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            date = sdf.parse(sdf.format(date));
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(5, days);
        return cal.getTime();
    }

    public static String dateFormat(Date date, String pattern) throws ParseException {
        if (StringUtils.isBlank(pattern)) {
            pattern = "yyyy-MM-dd";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    public static Date dateParse(String dateTimeString, String pattern) throws ParseException {
        if (StringUtils.isBlank(pattern)) {
            pattern = "yyyy-MM-dd";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.parse(dateTimeString);
    }

    public static String dateTimeToDateString(Date dateTime) throws ParseException {
        String dateTimeString = dateFormat(dateTime, "yyyy-MM-dd HH:mm:ss");
        return dateTimeString.substring(0, 10);
    }

    public static String dateTimeToDateStringIfTimeEndZero(Date dateTime) throws ParseException {
        String dateTimeString = dateFormat(dateTime, "yyyy-MM-dd HH:mm:ss");
        if (dateTimeString.endsWith("00:00:00")) {
            return dateTimeString.substring(0, 10);
        }
        return dateTimeString;
    }

    public static Date dateTimeToDate(Date dateTime) throws ParseException {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateTime);
        cal.set(11, 0);
        cal.set(12, 0);
        cal.set(13, 0);
        cal.set(14, 0);
        return cal.getTime();
    }

    public static Date dateAddHours(Date startDate, int hours) {
        if (startDate == null) {
            startDate = new Date();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(startDate);
        c.set(10, c.get(10) + hours);
        return c.getTime();
    }

    public static Date dateAddMinutes(Date startDate, int minutes) {
        if (startDate == null) {
            startDate = new Date();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(startDate);
        c.set(12, c.get(12) + minutes);
        return c.getTime();
    }

    public static Date dateAddSeconds(Date startDate, int seconds) {
        if (startDate == null) {
            startDate = new Date();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(startDate);
        c.set(13, c.get(13) + seconds);
        return c.getTime();
    }

    public static Date dateAddDays(Date startDate, int days) {
        if (startDate == null) {
            startDate = new Date();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(startDate);
        c.set(5, c.get(5) + days);
        return c.getTime();
    }

    public static Date dateAddMonths(Date startDate, int months) {
        if (startDate == null) {
            startDate = new Date();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(startDate);
        c.set(2, c.get(2) + months);
        return c.getTime();
    }

    public static Date dateAddYears(Date startDate, int years) {
        if (startDate == null) {
            startDate = new Date();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(startDate);
        c.set(1, c.get(1) + years);
        return c.getTime();
    }

    public static int dateCompare(Date myDate, Date compareDate) {
        Calendar myCal = Calendar.getInstance();
        Calendar compareCal = Calendar.getInstance();
        myCal.setTime(myDate);
        compareCal.setTime(compareDate);
        return myCal.compareTo(compareCal);
    }

    public static Date dateMin(Date date, Date compareDate) {
        if (date == null) {
            return compareDate;
        }
        if (compareDate == null) {
            return date;
        }
        if (1 == dateCompare(date, compareDate))
            return compareDate;
        if (-1 == dateCompare(date, compareDate)) {
            return date;
        }
        return date;
    }

    public static Date dateMax(Date date, Date compareDate) {
        if (date == null) {
            return compareDate;
        }
        if (compareDate == null) {
            return date;
        }
        if (1 == dateCompare(date, compareDate))
            return date;
        if (-1 == dateCompare(date, compareDate)) {
            return compareDate;
        }
        return date;
    }

    public static int dateBetween(Date startDate, Date endDate) {
        Date dateStart = null;
        Date dateEnd = null;
        try {
            dateStart = dateParse(dateFormat(startDate, "yyyy-MM-dd"), "yyyy-MM-dd");
            dateEnd = dateParse(dateFormat(endDate, "yyyy-MM-dd"), "yyyy-MM-dd");
        } catch (ParseException e) {
            
            e.printStackTrace();
        }
         
        return (int) ((dateEnd.getTime() - dateStart.getTime()) / 1000L / 60L / 60L / 24L);
    }

    public static int dateBetweenIncludeToday(Date startDate, Date endDate) {
        return dateBetween(startDate, endDate) + 1;
    }

    public static int getYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(1);
    }

    public static int getMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(2) + 1;
    }

    public static int getDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(5);
    }

    public static int getDaysOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.getActualMaximum(5);
    }

    public static int getDaysOfYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.getActualMaximum(6);
    }

    public static Date maxDateOfMonth(Date date) throws ParseException {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int value = cal.getActualMaximum(5);
        return dateParse(dateFormat(date, "yyyy-MM") + "-" + value, null);
    }

    public static Date minDateOfMonth(Date date) throws ParseException {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int value = cal.getActualMinimum(5);
        return dateParse(dateFormat(date, "yyyy-MM") + "-" + value, null);
    }

    public static Timestamp dateTotime(Date date) {
        if (null == date)
            return null;
        return new Timestamp(date.getTime());
    }

    public static Date timeTodate(Timestamp time) {
        if (null == time)
            return null;
        return new Date(time.getTime());
    }

    public static void main(String[] args) throws Exception {
        System.out.println(getDate(dateParse("2017-01-17", null)));
    }
}
