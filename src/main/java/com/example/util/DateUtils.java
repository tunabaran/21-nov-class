package com.example.util;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUtils {

    private DateUtils(){};

    public static Date addHours(Date date, int hours) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("UTC"));
        cal.setTime(date);
        cal.add(Calendar.HOUR_OF_DAY, hours);
        return cal.getTime();
    }
}
