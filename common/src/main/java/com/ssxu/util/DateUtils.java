package com.ssxu.util;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 类描述：  时间处理工具类
 * 创建人：ssxu
 * 创建时间：2017-4-29 下午8:54:12
 *
 * @version 1.0
 */
public class DateUtils {

    /**
     * 根据传入的类型返回SimpleDateFormat对象
     *
     * @param dateType dateType
     * @return 结果
     */
    private static SimpleDateFormat getDateFormat(String... dateType) {
        String type = dateType.length == 0 ? "yyyy-MM-dd" : dateType[0];
        return new SimpleDateFormat(type);
    }

    /**
     * 返回当前时间字符串  默认格式yyyy-MM-dd
     *
     * @param dateType 要返回的格式  <span style="color:red">yyyy-MM-dd HH:mm:ss</span>
     * @return 返回当前时间字符串
     */
    public static String getNowDate(String... dateType) {
        return getDateFormat(dateType).format(new Date());
    }

    /**
     * 时间转字符串  默认格式yyyy-MM-dd
     *
     * @param data     时间
     * @param dateType 要转的类型  <span style="color:red">yyyy-MM-dd HH:mm:ss</span>
     * @return 相对应的字符串
     */
    public static String dateToStr(Date data, String... dateType) {
        return getDateFormat(dateType).format(data);
    }

    /**
     * 字符串转日期   默认格式yyyy-MM-dd
     *
     * @param str      时间字符串
     * @param dateType 要转的类型  <span style="color:red">yyyy-MM-dd HH:mm:ss</span>
     * @return 转成的date
     */
    public static Date strToDate(String str, String... dateType) {
        Date date = null;
        try {
            date = getDateFormat(dateType).parse(str);
        } catch (Exception e) {
            System.err.println("时间转字符串出错" + e.toString());
        }
        return date;
    }

    /**
     * 计算两个日期之间相差的天数
     *
     * @param smdate 较小的时间  格式yyyy-MM-dd
     * @param bdate  较大的时间  格式yyyy-MM-dd
     * @return 相差天数
     */
    public static int daysBetween(Date smdate, Date bdate) {
        SimpleDateFormat sdf = getDateFormat("yyyy-MM-dd");
        try {
            smdate = sdf.parse(sdf.format(smdate));
            bdate = sdf.parse(sdf.format(bdate));
            Calendar cal = Calendar.getInstance();
            cal.setTime(smdate);
            long time1 = cal.getTimeInMillis();
            cal.setTime(bdate);
            long time2 = cal.getTimeInMillis();
            long between_days = (time2 - time1) / (1000 * 3600 * 24);
            return Integer.parseInt(String.valueOf(between_days));
        } catch (ParseException e) {
            System.err.println("计算日期天数出错===" + e.toString());
        }
        return 0;
    }

    /**
     * 两个日期相减得到天数小时分钟
     *
     * @param date1 时间1
     * @param date2 时间2
     * @return 结果
     */
    public static String dateXj(String date1, String date2) {
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date d1 = df.parse(date1);
            Date d2 = df.parse(date2);
            long diff = d1.getTime() - d2.getTime();// 这样得到的差值是微秒级别
            long days = diff / (1000 * 60 * 60 * 24);

            long hours = (diff - days * (1000 * 60 * 60 * 24))
                    / (1000 * 60 * 60);
            long minutes = (diff - days * (1000 * 60 * 60 * 24) - hours
                    * (1000 * 60 * 60))
                    / (1000 * 60);
            return "" + days + "天" + hours + "小时" + minutes + "分";
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "计算出错";
    }

    /***
     * 给日期增加多少天
     *
     * @param calDate 基础日期
     * @param addDate 类型必须是long
     * @return 基础日期增加addDate之后的结果
     */
    public static String addCalendarDay(Date calDate, long addDate) {
        long time = calDate.getTime();
        addDate = addDate * 24 * 60 * 60 * 1000;
        time += addDate;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(new Date(time));
    }

    /**
     * 给当前日期加几个月 然后当前日减去一
     *
     * @param month 增加多少月
     * @return 增加月后捡起一返回的结果
     */
    public static String addCalendarMonth(int month) {
        Format f = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, month);
        c.add(Calendar.DAY_OF_MONTH, -1);
        return f.format(c.getTime());
    }

    /**
     * 日期转星期
     *
     * @param datetime 日期
     * @return 星期
     */
    public static String dateToWeek(String datetime) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar cal = Calendar.getInstance(); // 获得一个日历
        Date datet = null;
        try {
            datet = f.parse(datetime);
            cal.setTime(datet);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1; // 指示一个星期中的某天。
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    /**
     * 获取某一年的今天 yyyy-MM-dd
     */
    public static String getNowOfLastYear(int s) {
        SimpleDateFormat aSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        GregorianCalendar aGregorianCalendar = new GregorianCalendar();
        aGregorianCalendar.set(Calendar.YEAR, aGregorianCalendar
                .get(Calendar.YEAR) - s);
        String currentYearAndMonth = aSimpleDateFormat
                .format(aGregorianCalendar.getTime());
        return currentYearAndMonth;
    }

    /**
     * 以 date 为基准日期，通过相差的年数得到新日期
     *
     * @param date 基础日期
     * @return 结果
     */
    public static Date addYear(Date date, int year) {
        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.setTime(date);
        canlendar.add(Calendar.YEAR, year);
        return canlendar.getTime();
    }

    /**
     * 获取当前时间的前多少日 月 年 的日期
     *
     * @param type 1 日 2 月 3年
     * @param time 多长时间 后面 7  前面  -7
     * @return 日期
     */
    public static String frontDate(String type, int time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        switch (type) {
            case "1":
                c.add(Calendar.DATE, time);
                break;
            case "2":
                c.add(Calendar.MONTH, time);
                break;
            case "3":
                c.add(Calendar.YEAR, time);
        }
        Date d = c.getTime();
        return format.format(d);
    }
}
