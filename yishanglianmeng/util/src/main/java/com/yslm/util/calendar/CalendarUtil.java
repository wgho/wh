package com.yslm.util.calendar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.yslm.util.cache.CacheUtil;


/**
 * 日期工具类
 *
 * @author jinkai
 */
public class CalendarUtil {

    /**
     * 日期之前
     *
     * @param date
     * @param date2
     * @return
     */
    public static boolean isBefore(Date date, Date date2) {
        return null != date && null != date2 && date.before(date2);
    }

    /**
     * 日期之后
     *
     * @param date
     * @param date2
     * @return
     */
    public static boolean isAfter(Date date, Date date2) {
        return null != date && null != date2 && date.after(date2);
    }

    /**
     * 日期之中
     *
     * @param date
     * @param date2
     * @param date3
     * @return
     */
    public static boolean isBetween(Date date, Date date2, Date date3) {
        return null != date && isAfter(date, date2) && isBefore(date, date3);
    }

    /**
     * 某一个日期n月相对应某一天 n 为负值表示向前 n 为正值表示向后
     */
    public static Date calDateForMonth(Date date, int n) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, n);
        return c.getTime();
    }

    /**
     * 某一个日期n天相对应某一天 n 为负值表示向前 n 为正值表示向后
     */
    public static Date calDateForDay(Date date, int n) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH, n);
        return c.getTime();
    }

    /**
     * 某一个日期n天相对应某一天 n 为负值表示向前 n 为正值表示向后
     */
    public static Date calDateForYear(Date date, int n) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.YEAR, n);
        return c.getTime();
    }

    /**
     * 取某一个时间相对某一时间n小时向前或向后的时间
     */
    public static Date calDateForHour(Date date, int n) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.HOUR_OF_DAY, n);
        return c.getTime();
    }

    /**
     * 取某一时间相对某一时间n分钟向前或向后的时间
     */
    public static Date calDateForMin(Date date, int n) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MINUTE, n);
        return c.getTime();
    }

    /**
     * 当前月份最大天数
     *
     * @param date
     */
    public static int currentMonthMaxDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int day = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        return day;
    }

    /**
     * 计算日期天数差值
     *
     * @param early
     * @param late
     * @return
     */
    public static final int daysBetween(Date early, Date late) {
        Calendar calst = Calendar.getInstance();
        Calendar caled = Calendar.getInstance();
        calst.setTime(early);
        caled.setTime(late);
        // 设置时间为0时
        calst.set(Calendar.HOUR_OF_DAY, 0);
        calst.set(Calendar.MINUTE, 0);
        calst.set(Calendar.SECOND, 0);
        caled.set(Calendar.HOUR_OF_DAY, 0);
        caled.set(Calendar.MINUTE, 0);
        caled.set(Calendar.SECOND, 0);
        // 得到两个日期相差的天数
        int days = ((int) (caled.getTime().getTime() / 1000) - (int) (calst
                .getTime().getTime() / 1000)) / 3600 / 24;
        return days;
    }

    /**
     * 计算日期差［年，月，日］
     *
     * @param date
     * @param now
     * @return
     */
    public static int[] betweenYearAndMonthAndDays(Date date, Date now) {
        Calendar calendarDate = Calendar.getInstance();
        calendarDate.setTime(date);
        Calendar calendarNow = Calendar.getInstance();
        calendarNow.setTime(now);
        int diffYears = 0, diffMonths, diffDays;
        int dayOfBirth = calendarDate.get(Calendar.DAY_OF_MONTH);
        int dayOfNow = calendarNow.get(Calendar.DAY_OF_MONTH);
        if (dayOfBirth <= dayOfNow) {
            diffMonths = monthsOfDate(date, now);
            diffDays = dayOfNow - dayOfBirth;
            if (diffMonths == 0)
                diffDays++;
        } else {
            if (isEndOfMonth(date)) {
                if (isEndOfMonth(now)) {
                    diffMonths = monthsOfDate(date, now);
                    diffDays = 0;
                } else {
                    calendarNow.add(Calendar.MONTH, -1);
                    diffMonths = monthsOfDate(date, now);
                    diffDays = dayOfNow + 1;
                }
            } else {
                if (isEndOfMonth(now)) {
                    diffMonths = monthsOfDate(date, now);
                    diffDays = 0;
                } else {
                    calendarNow.add(Calendar.MONTH, -1);// 上个月
                    diffMonths = monthsOfDate(date, now);
                    // 获取上个月最大的一天
                    int maxDayOfLastMonth = calendarNow.getActualMaximum(Calendar.DAY_OF_MONTH);
                    if (maxDayOfLastMonth > dayOfBirth) {
                        diffDays = maxDayOfLastMonth - dayOfBirth + dayOfNow;
                    } else {
                        diffDays = dayOfNow;
                    }
                }
            }
        }
        // 计算月份时，没有考虑年
        diffYears = diffMonths / 12;
        diffMonths = diffMonths % 12;
        return new int[]{diffYears, diffMonths, diffDays};
    }

    /**
     * 获取传入日期的开始时间 00:00:00
     */
    public static final Date calBeginTimeForThisTime(Date date) {
        Calendar calst = Calendar.getInstance();
        calst.setTime(date);
        calst.set(Calendar.HOUR_OF_DAY, 0);
        calst.set(Calendar.MINUTE, 0);
        calst.set(Calendar.SECOND, 0);
        return calst.getTime();
    }

    /**
     * 获取两个日历的月份之差
     *
     * @param date
     * @param now
     * @return
     */
    public static int monthsOfDate(Date date, Date now) {
        Calendar calendarDate = Calendar.getInstance();
        calendarDate.setTime(date);
        Calendar calendarNow = Calendar.getInstance();
        calendarNow.setTime(now);
        return (calendarNow.get(Calendar.YEAR) - calendarDate
                .get(Calendar.YEAR)) * 12 + calendarNow.get(Calendar.MONTH)
                - calendarDate.get(Calendar.MONTH);
    }

    /**
     * 取年份差
     *
     * @param date
     * @param now
     * @return
     */
    public static int yearsOfDate(Date date, Date now) {
        int months = monthsOfDate(date, now);
        return months / 12;
    }

    /**
     * 判断这一天是否是月底
     *
     * @param date
     * @return
     */
    public static boolean isEndOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        if (dayOfMonth == calendar.getActualMaximum(Calendar.DAY_OF_MONTH))
            return true;
        return false;
    }

    /**
     * 获取传入日期的结束时间 00:00:00
     */
    public static final Date calEndTimeForThisTime(Date date) {
        Calendar calst = Calendar.getInstance();
        calst.setTime(date);
        calst.set(Calendar.HOUR_OF_DAY, 23);
        calst.set(Calendar.MINUTE, 59);
        calst.set(Calendar.SECOND, 59);
        return calst.getTime();
    }

    /**
     * 获得当前当天剩余的分钟数
     * 
     * @return
     * @author gmsd
     * @Date 2015年7月16日
     */
    public static long getSurplusTodayMinute(){
        long now = System.currentTimeMillis();
        return (24*60)-(((now/1000/60) + CacheUtil.BEIJING_TIME_DIFF)%(24*60));
    }
    
    public static void main(String[] args) {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date calDate = calDateForYear(date, -7);
        System.out.println(f.format(calDate));
    }

}
