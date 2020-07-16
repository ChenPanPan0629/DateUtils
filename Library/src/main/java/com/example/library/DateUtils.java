package com.example.library;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtils {
    public static final String TIME_FORMATE = "yyyy年MM月dd日";

    /**
     * 判断两个日期是否为同一天
     */
    public static boolean isSameDay(long millis1, long millis2, TimeZone timeZone) {
        long interval = millis1 - millis2;
        return interval < 86400000 && interval > -86400000 && millis2Days(millis1, timeZone) == millis2Days(millis2, timeZone);
    }

    private static long millis2Days(long millis, TimeZone timeZone) {
        return (((long) timeZone.getOffset(millis)) + millis) / 86400000;
    }


    /**
     * 获得指定日期的前一天
     */
    @SuppressLint("SimpleDateFormat")
    public static String getSpecifiedDayBefore(String specifiedDay) {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat(TIME_FORMATE).parse(specifiedDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (date != null) {
            c.setTime(date);
            int day = c.get(Calendar.DATE);
            c.set(Calendar.DATE, day - 1);
            @SuppressLint("SimpleDateFormat") String dayBefore = new SimpleDateFormat(TIME_FORMATE).format(c.getTime());
            return dayBefore;
        } else return "";
    }

    /**
     * 获得指定日期的后一天
     */
    @SuppressLint("SimpleDateFormat")
    public static String getSpecifiedDayAfter(String specifiedDay) {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat(TIME_FORMATE).parse(specifiedDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (date != null) {
            c.setTime(date);
            int day = c.get(Calendar.DATE);
            c.set(Calendar.DATE, day + 1);
            return new SimpleDateFormat(TIME_FORMATE).format(c.getTime());
        } else return "";
    }

    /*
     * 将字符串转换为时间戳
     */
    public static long dateToStamp(String s) throws ParseException {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat(TIME_FORMATE);
        Date date = simpleDateFormat.parse(s);
        if (date != null) {
            return date.getTime();
        } else return 0;
    }

    /**
     * 时间戳转为具体日期
     *
     * @param time 1541569323155
     * @return 2018-11-07 13:42:03
     */
    public static String getDate2String(long time) {
        Date date = new Date(time);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return format.format(date);
    }
}
