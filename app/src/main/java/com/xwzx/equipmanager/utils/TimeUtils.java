package com.xwzx.equipmanager.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 2018-04-27 13:32:00
 */

public class TimeUtils {


    public static String getCurrentDate(String pattern) {
        Date date = new Date();
        DateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }


    /**
     * 得到指定时间的UTC0时间
     *
     * @param beforeDate
     * @param dataFormat 格式为"yyyy-MM-dd'T'HH:mm:ss.SSS Z"
     * @return
     */
    public static String getUTCTimeStr(Date beforeDate, String dataFormat) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dataFormat);
        // 1、取得本地时间：
        Calendar cal = Calendar.getInstance();
        cal.setTime(beforeDate);
        // 2、取得时间偏移量：
        int zoneOffset = cal.get(Calendar.ZONE_OFFSET);
        // 3、取得夏令时差：
        int dstOffset = cal.get(Calendar.DST_OFFSET);
        // 4、从本地时间里扣除这些差量，即可以取得UTC时间：
        cal.add(Calendar.MILLISECOND, -(zoneOffset + dstOffset));
        //  cal.setTimeInMillis(cal.getTimeInMillis()+8 * 60 * 60 * 1000);
        cal.setTimeInMillis(cal.getTimeInMillis());
        Date afterDate = cal.getTime();
        try {
            return simpleDateFormat.format(afterDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getUTCTimeDef() {
        return getUTCTimeStr(new Date(), "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    }


    /**
     * utc 时间格式转换Date 2018-08-07T03:41:59Z
     *  
     *
     * @param utcTime 时间
     * @return
     */
    public static Date formatStrUTCToDate(String utcTime) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.CHINESE);
        TimeZone utcZone = TimeZone.getTimeZone("GMT");
        sf.setTimeZone(utcZone);
        Date date = null;
        String dateTime = "";
        try {
            date = sf.parse(utcTime);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String formatStr(String pattern, Date date) {
        SimpleDateFormat sf = new SimpleDateFormat(pattern, Locale.CHINESE);
        TimeZone utcZone = TimeZone.getTimeZone("GMT");
        sf.setTimeZone(utcZone);
        String dateTime = "";
        try {
            return sf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *     * 获取两个日期之间的间隔天数
     *     * @return
     *     
     */
    public static int getDayCount(String startDateStr, String endDateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        TimeZone utcZone = TimeZone.getTimeZone("GMT");
        sdf.setTimeZone(utcZone);
        Date startDate = null;
        Date endDate = null;
        try {
            startDate = sdf.parse(startDateStr);
            endDate = sdf.parse(endDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar fromCalendar = Calendar.getInstance();
        fromCalendar.setTime(startDate);
        fromCalendar.set(Calendar.HOUR_OF_DAY, 0);
        fromCalendar.set(Calendar.MINUTE, 0);
        fromCalendar.set(Calendar.SECOND, 0);
        fromCalendar.set(Calendar.MILLISECOND, 0);

        Calendar toCalendar = Calendar.getInstance();
        toCalendar.setTime(endDate);
        toCalendar.set(Calendar.HOUR_OF_DAY, 0);
        toCalendar.set(Calendar.MINUTE, 0);
        toCalendar.set(Calendar.SECOND, 0);
        toCalendar.set(Calendar.MILLISECOND, 0);

        return (int) ((toCalendar.getTime().getTime() - fromCalendar.getTime().getTime()) / (1000 * 60 * 60 * 24));
    }


    public static String StringToDate(String time) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Date date;
        date = format.parse(time);
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy.MM.dd");
        String s = format1.format(date);
        return s;

    }


}
