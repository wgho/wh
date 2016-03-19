package com.yslm.util.calendar;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;

import com.yslm.util.log.LogUtil;


/**
 * 日期工具类
 *
 * @author tangzhi
 */
public class DateTools {

    private static final LogUtil log = LogUtil.instance(DateTools.class);

    public static final String DATE_PATTERN_DEFAULT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_PATTERN_MINUTE = "yyyy-MM-dd HH:mm";
    public static final String DATE_PATTERN_DAY = "yyyy-MM-dd";
    public static final String DATE_PATTERN_MONTH = "yyyy-MM";
    public static final String DAY_LAST_TIME = " 23:59:59";
    public static final String DATE_PATTERN_DAY_NUM = "yyyyMMdd";
    public static final String DAY_FIRST_TIME = " 00:00:00";
    public static final String SINGLE_BID_DAY_LAST_TIME = " 23:00:00";
    public static final String SINGLE_BIDDAY_FIRST_TIME = " 02:00:00";
    public static final String SINGLE_AUTO_BID_LOAN_TIME = " 22:00:00";

    /**
     * 得到当月的最大日期
     *
     * @return
     */
    public static final String DATE_PATTERN_DAY_CHINNESS = "【yyyy】年【MM】月【dd】";
    public static final String DATE_PATTERN_DAY_CHINNESS_DEFAULT = "yyyy年MM月dd日";
    public static final String DATE_PATTERN_JUEST_DAY = "dd";

    public static int getMaxDayOfLastMonth() {
        Date now = new Date();
        Date lastMonth = DateUtils.addMonths(now, -1);
        lastMonth = getMaxDateOfMonth(lastMonth);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(lastMonth);
        int maxDay = calendar.get(Calendar.DAY_OF_MONTH);
        return maxDay;
    }

    /**
     * 得到当年的最大月份
     *
     * @return
     */
    public static int getYearOfLastMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        int yearOfLastMonth = calendar.get(Calendar.YEAR);
        return yearOfLastMonth;
    }

    /**
     * 得到下一个月的月份
     *
     * @return
     */
    public static int getMonthOfLastMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        int lastMonth = calendar.get(Calendar.MONTH) + 1;
        return lastMonth;
    }

    /**
     * 得到当前的年份
     *
     * @return
     */
    public static int getCurrentStatYear() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        return year;
    }

    // 获取当前月份，cal.get(Calendar.MONTH)是从零开始。
    public static int getCurrentStatMonth() {
        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH) + 1;
        return month;
    }

    /**
     * 得到当前的日
     *
     * @return
     */
    public static int getCurrentStatDay() {
        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return day;
    }

    /**
     * 时间校验: 开始时间不能大于当前时间.
     */
    public static Date validateStartDate(Date startDate) {
        Date today = new Date();
        // 开始时间不能大于当前时间.
        if (startDate.compareTo(today) == 1) {
            log.warnLog("startDate.compareTo(today)==1, set startDate = today:" + today);
            startDate = today;
        }
        return startDate;
    }

    /**
     * 时间校验: 不能晚于当前时间(如果晚于当前时间，则替换为当前时间)
     */
    public static Date notAfterNow(Date myDate) {
        Date today = new Date();
        if (myDate.after(today)) {
            log.warnLog("myDate.after(today), set myDate = today:" + today);
            myDate = today;
        }
        return myDate;
    }

    /**
     * 时间校验: 不能晚于昨天(如果晚于昨天，则替换为昨天)
     */
    public static Date notAfterYesterday(Date myDate) {
        Date today = new Date();
        Date yesterday = DateUtils.addDays(today, -1);
        ;
        // 3. 结束时间不能大于昨天.
        if (myDate.after(yesterday)) {
            log.warnLog("myDate.after(yesterday), set myDate = yesterday:" + yesterday);
            myDate = yesterday;
        }
        return myDate;
    }

    /**
     * 时间校验: 不能晚于上一个月(如果晚于上一个月，则替换为上一个月)
     */
    public static Date notAfterLastMonth(Date myDate) {
        Date today = new Date();
        Date lastMonth = DateUtils.addMonths(today, -1);
        lastMonth = DateTools.getMaxDateOfMonth(lastMonth);
        // 3. 结束时间不能大于上一个月.
        if (myDate.after(lastMonth)) {
            log.warnLog("myDate.after(lastMonth), set myDate = lastMonth:" + lastMonth);
            myDate = lastMonth;
        }
        return myDate;
    }

    /**
     * 时间校验: 不能晚于上一年(如果晚于上一年，则替换为上一年)
     */
    public static Date notAfterLastYear(Date myDate) {
        Date today = new Date();
        Date lastYear = DateUtils.addYears(today, -1);
        lastYear = DateTools.getMaxDateOfYear(lastYear);
        // 3. 结束时间不能大于上一年.
        if (myDate.after(lastYear)) {
            log.warnLog("myDate.after(lastYear), set myDate = lastYear:" + lastYear);
            myDate = lastYear;
        }
        return myDate;
    }

    /**
     * 时间校验: myDate不能早于basicDate(如果早于basicDate，则替换为basicDate)
     *
     * @throws Exception
     */
    public static Date notBefore(Date myDate, String basicStr) throws Exception {
        Date basicDate = DateTools.stringToDateTime(basicStr);
        // Date today = new Date();
        // Date yesterday = DateUtils.addDays(today, -1);;
        // 3. 结束时间不能大于昨天.
        if (myDate.before(basicDate)) {
            log.warnLog("myDate.before(basicDate), set myDate = basicDate:" + basicDate);
            myDate = basicDate;
        }
        return myDate;
    }

    /**
     * 将日期转化为字符串。 字符串格式("yyyy-MM-dd HH:mm:ss")。
     */
    public static String dateTime2String(Date date) {
        return dateToString(date, DATE_PATTERN_DEFAULT);
    }

    /**
     * 将日期转化为字符串。 字符串格式("yyyy-MM-dd")，小时、分、秒被忽略。
     */
    public static String dateToString(Date date) {
        return dateToString(date, DATE_PATTERN_DAY);
    }

    /**
     * 将日期转化为字符串
     */
    public static String dateToString(Date date, String pattern) {
        String str = "";
        try {
            SimpleDateFormat formater = new SimpleDateFormat(pattern);
            str = formater.format(date);
        } catch (Throwable e) {
            log.errorLog(e.getMessage(), e);
        }
        return str;
    }

    /**
     * 将传入的年月日转化为Date类型
     */
    public static Date YmdToDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        return calendar.getTime();
    }

    /**
     * 将字符串转化为日期
     */
    public static Date stringToDateTime(String str) throws ParseException {
        return getDateFormatOfDefault().parse(str);
    }

    /**
     * 将字符串转化为日期
     */
    public static Date stringToMediumDateTime(String str) throws ParseException {
        DateFormat format = DateFormat.getDateInstance(DateFormat.MEDIUM);
        return format.parse(str);
    }

    /**
     * 获取默认的DateFormat
     */
    public static DateFormat getDateFormatOfDefault() {
        return new SimpleDateFormat(DATE_PATTERN_DEFAULT);
    }

    /**
     * 将字符串转化为日期。 字符串格式("YYYY-MM-DD")。
     * 例如："2012-07-01"或者"2012-7-1"或者"2012-7-01"或者"2012-07-1"是等价的。
     */
    public static Date stringToDate(String str, String pattern) {
        Date dateTime = null;
        try {
            SimpleDateFormat formater = new SimpleDateFormat(pattern);
            dateTime = formater.parse(str);
        } catch (Exception e) {
            log.errorLog(e.getMessage(), e);
        }
        return dateTime;
    }

    /**
     * 将字符串转化为日期(从一种格式到另一种格式)。
     */
    public static String StringPatternToPattern(String str, String pattern1, String pattern2) {
        Date dateTime = null;
        String productStr = "";
        try {
            if (!(str == null || str.equals(""))) {
                SimpleDateFormat formater = new SimpleDateFormat(pattern1);
                dateTime = formater.parse(str);

                SimpleDateFormat formater1 = new SimpleDateFormat(pattern2);
                productStr = formater1.format(dateTime);
            }
        } catch (Exception ex) {
            log.errorLog(ex.getMessage(), ex);
        }
        return productStr;
    }

    /**
     * 日期时间带时分秒的Timestamp表示
     */
    public static Timestamp stringToDateHMS(String str) {
        Timestamp time = null;
        try {
            time = Timestamp.valueOf(str);
        } catch (Exception ex) {
            log.errorLog(ex.getMessage(), ex);
        }
        return time;

    }

    /**
     * 取得一个date对象对应的日期的0分0秒时刻的Date对象。
     */
    public static Date getMinDateOfHour(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MINUTE, calendar.getActualMinimum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getActualMinimum(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND, calendar.getActualMinimum(Calendar.MILLISECOND));
        return calendar.getTime();
    }

    /**
     * 取得一个date对象对应的日期的0点0分0秒时刻的Date对象。
     */
    public static Date getMinDateOfDay(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getMinDateOfHour(date));
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMinimum(Calendar.HOUR_OF_DAY));
        return calendar.getTime();
    }

    /**
     * 取得一年中的最早一天。
     */
    public static Date getMinDateOfYear(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_YEAR, calendar.getActualMinimum(Calendar.DAY_OF_YEAR));
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMinimum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getActualMinimum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getActualMinimum(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND, calendar.getActualMinimum(Calendar.MILLISECOND));

        return calendar.getTime();
    }

    /**
     * 取得一年中的最后一天
     */
    public static Date getMaxDateOfYear(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_YEAR, calendar.getActualMaximum(Calendar.DAY_OF_YEAR));
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMaximum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getActualMaximum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getActualMaximum(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND, calendar.getActualMaximum(Calendar.MILLISECOND));

        return calendar.getTime();
    }

    /**
     * 取得一周中的最早一天。
     */
    public static Date getMinDateOfWeek(Date date, Locale locale) {
        if (date == null)
            return null;

        Calendar calendar = Calendar.getInstance();
        int day_of_week = calendar.get(Calendar.DAY_OF_WEEK);

        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getActualMinimum(Calendar.DAY_OF_WEEK));
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMinimum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getActualMinimum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getActualMinimum(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND, calendar.getActualMinimum(Calendar.MILLISECOND));

        if (locale == null)
            locale = Locale.CHINESE;
        Date tmpDate = calendar.getTime();
        if (Locale.CHINESE.getLanguage().equals(locale.getLanguage())) {
            if (day_of_week == 1) {// 星期天
                tmpDate = DateUtils.addDays(tmpDate, -6);
            } else {
                tmpDate = DateUtils.addDays(tmpDate, 1);
            }
        }

        return tmpDate;
    }

    /**
     * 取得一周中的最后一天
     */
    public static Date getMaxDateOfWeek(Date date, Locale locale) {
        if (date == null)
            return null;

        Calendar calendar = Calendar.getInstance();
        int day_of_week = calendar.get(Calendar.DAY_OF_WEEK);

        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getActualMaximum(Calendar.DAY_OF_WEEK));
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMaximum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getActualMaximum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getActualMaximum(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND, calendar.getActualMaximum(Calendar.MILLISECOND));

        if (locale == null)
            locale = Locale.CHINESE;
        Date tmpDate = calendar.getTime();
        if (Locale.CHINESE.getLanguage().equals(locale.getLanguage())) {
            if (day_of_week == 1) {// 星期天
                tmpDate = DateUtils.addDays(tmpDate, -6);
            } else {
                tmpDate = DateUtils.addDays(tmpDate, 1);
            }
        }

        return tmpDate;
    }

    /**
     * 取得一月中的最早一天。
     */
    public static Date getMinDateOfMonth(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMinimum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getActualMinimum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getActualMinimum(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND, calendar.getActualMinimum(Calendar.MILLISECOND));

        return calendar.getTime();
    }

    /**
     * 取得一月中的最后一天
     */
    public static Date getMaxDateOfMonth(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMaximum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getActualMaximum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getActualMaximum(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND, calendar.getActualMaximum(Calendar.MILLISECOND));

        return calendar.getTime();
    }

    /**
     * 取得一个date对象对应的日期的23点59分59秒时刻的Date对象。
     */
    public static Date getMaxDateOfDay(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMaximum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getActualMaximum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getActualMaximum(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND, calendar.getActualMaximum(Calendar.MILLISECOND));

        return calendar.getTime();
    }

    /**
     * 取得一个date对象对应的小时的59分59秒时刻的Date对象。
     */
    public static Date getMaxDateOfHour(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MINUTE, calendar.getActualMaximum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getActualMaximum(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND, calendar.getActualMaximum(Calendar.MILLISECOND));

        return calendar.getTime();
    }

    /**
     * 获取2个时间相隔几秒
     */
    public static int getBetweenSecondNumber(Date startDate, Date endDate) {
        if (startDate == null || endDate == null)
            return -1;

        if (startDate.after(endDate)) {
            Date tmp = endDate;
            endDate = startDate;
            startDate = tmp;
        }

        long timeNumber = -1L;
        long TIME = 1000L;
        try {
            timeNumber = (endDate.getTime() - startDate.getTime()) / TIME;

        } catch (Exception e) {
            log.errorLog(e.getMessage(), e);
        }
        return (int) timeNumber;
    }

    /**
     * 获取2个时间相隔几分钟
     */
    public static int getBetweenMinuteNumber(Date startDate, Date endDate) {
        if (startDate == null || endDate == null)
            return -1;

        if (startDate.after(endDate)) {
            Date tmp = endDate;
            endDate = startDate;
            startDate = tmp;
        }

        long timeNumber = -1l;
        long TIME = 60L * 1000L;
        try {
            timeNumber = (endDate.getTime() - startDate.getTime()) / TIME;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return (int) timeNumber;
    }

    /**
     * 获取2个时间相隔几小时
     */
    public static int getBetweenHourNumber(Date startDate, Date endDate) {
        if (startDate == null || endDate == null)
            return -1;

        if (startDate.after(endDate)) {
            Date tmp = endDate;
            endDate = startDate;
            startDate = tmp;
        }

        long timeNumber = -1l;
        long TIME = 60L * 60L * 1000L;
        try {
            timeNumber = (endDate.getTime() - startDate.getTime()) / TIME;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return (int) timeNumber;
    }

    /**
     * 获取2个时间相隔几天
     */
    public static int getBetweenDayNumber(Date startDate, Date endDate) {
        if (startDate == null || endDate == null)
            return -1;

        if (startDate.after(endDate)) {
            Date tmp = endDate;
            endDate = startDate;
            startDate = tmp;
        }

        long dayNumber = -1L;
        long DAY = 24L * 60L * 60L * 1000L;
        try {
            // "2010-08-01 00:00:00 --- 2010-08-03 23:59:59"算三天
            dayNumber = (endDate.getTime() + 1000 - startDate.getTime()) / DAY;
//            System.out.println(endDate.getTime()+" "+startDate.getTime());
        } catch (Exception e) {
            log.errorLog(e.getMessage(), e);
        }
        return (int) dayNumber;
    }


    /**
     * 获取2个时间相隔几天
     */
    public static int getBetweenDayNumberIgnoreTime(Date startDate, Date endDate) {
        if (startDate == null || endDate == null)
            return -1;

        final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            startDate = dateFormat.parse(dateFormat.format(startDate));
            endDate = dateFormat.parse(dateFormat.format(endDate));
        } catch (ParseException e1) {
            e1.printStackTrace();
        }

        long dayNumber = -1L;
        long DAY = 24L * 60L * 60L * 1000L;

        try {
            // "2010-08-01 00:00:00 --- 2010-08-03 23:59:59"算两天
            dayNumber = (endDate.getTime() + 1000 - startDate.getTime()) / DAY;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (int) dayNumber;
    }

    /**
     * 获取2个时间相隔几月
     */
    public static int getBetweenMonthNumber(Date startDate, Date endDate) {
        int result = 0;
        try {
            if (startDate == null || endDate == null)
                return -1;

            // swap start and end date
            if (startDate.after(endDate)) {
                Date tmp = endDate;
                endDate = startDate;
                startDate = tmp;
            }

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startDate);

            int monthS = calendar.get(Calendar.MONTH);
            int yearS = calendar.get(Calendar.YEAR);

            calendar.setTime(endDate);
            int monthE = calendar.get(Calendar.MONTH);
            int yearE = calendar.get(Calendar.YEAR);

            if (yearE - yearS == 0) {
                result = monthE - monthS;
            } else {
                result = (yearE - yearS - 1) * 12 + (12 - monthS) + monthE;
            }

        } catch (Exception e) {
            log.errorLog(e.getMessage(), e);
        }
        return result;
    }

    /**
     * 获取2个时间相隔几年
     */
    public static int getBetweenYearNumber(Date startDate, Date endDate) {
        int result = 0;
        try {
            if (startDate == null || endDate == null)
                return -1;

            // swap start and end date
            if (startDate.after(endDate)) {
                Date tmp = endDate;
                endDate = startDate;
                startDate = tmp;
            }

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startDate);
            int yearS = calendar.get(Calendar.YEAR);

            calendar.setTime(endDate);
            int yearE = calendar.get(Calendar.YEAR);

            result = yearE - yearS;

        } catch (Exception e) {
            log.errorLog(e.getMessage(), e);
        }
        return result;
    }

    /**
     * 按天拆分时间
     */
    public static List<Date> splitDateByDay(Date startDate, Date endDate) {
        if (startDate == null || endDate == null)
            return null;

        List<Date> dateList = new ArrayList<Date>();
        dateList.add(startDate);

        int num = getBetweenDayNumber(startDate, endDate);
        for (int i = 1; i <= num; i++) {
            dateList.add(DateUtils.addDays(startDate, i));
        }

        return dateList;
    }

    /**
     * 按月拆分时间
     */
    public static List<Date> splitDateByMonth(Date startDate, Date endDate) {
        List<Date> dateList = new ArrayList<Date>();

        if (startDate == null || endDate == null) {
            return dateList;
        }

        dateList.add(startDate);
        int num = getBetweenMonthNumber(startDate, endDate);
        for (int i = 1; i <= num; i++) {
            dateList.add(DateUtils.addMonths(startDate, i));
        }

        return dateList;
    }

    /**
     * 按年拆分时间
     */
    public static List<Date> splitDateByYear(Date startDate, Date endDate) {
        List<Date> dateList = new ArrayList<Date>();

        if (startDate == null || endDate == null) {
            return dateList;
        }

        dateList.add(startDate);
        int num = getBetweenYearNumber(startDate, endDate);
        for (int i = 1; i <= num; i++) {
            dateList.add(DateUtils.addYears(startDate, i));
        }

        return dateList;
    }

    /**
     * 本季度
     */
    public static List<Date> getCurrentQuarter() {
        List<Date> dateList = new ArrayList<Date>();
        Date date = new Date(System.currentTimeMillis());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH);// 一月为0

        dateList.add(1, calendar.getTime());// 结束时间设置为当前时间

        if (month >= 0 && month <= 2) {// 第一季度
            calendar.set(Calendar.MONTH, 0);
        } else if (month >= 3 && month <= 5) {// 第二季度
            calendar.set(Calendar.MONTH, 3);
        } else if (month >= 6 && month <= 8) {// 第三季度
            calendar.set(Calendar.MONTH, 6);
        } else {// 第四季度
            calendar.set(Calendar.MONTH, 9);
        }

        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        dateList.add(0, calendar.getTime());

        return dateList;
    }

    /**
     * 上季度
     */
    public static List<Date> getLastQuarter() {
        List<Date> dateList = new ArrayList<Date>();
        Date date = new Date(System.currentTimeMillis());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH);// 一月为0

        // 如果是第一季度则返回去年的第四季度
        if (month >= 0 && month <= 2) {// 当前第一季度
            calendar.add(Calendar.YEAR, -1);// 退到去年
            calendar.set(Calendar.MONTH, 9);// 去年十月
        } else if (month >= 3 && month <= 5) {// 当前第二季度
            calendar.set(Calendar.MONTH, 0);
        } else if (month >= 6 && month <= 8) {// 当前第三季度
            calendar.set(Calendar.MONTH, 3);
        } else {// 当前第四季度
            calendar.set(Calendar.MONTH, 6);
        }
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        dateList.add(0, calendar.getTime());

        calendar.add(Calendar.MONTH, 3);// 加3个月，到下个季度的第一天
        calendar.add(Calendar.DAY_OF_MONTH, -1);// 退一天，得到上季度的最后一天
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);

        dateList.add(1, calendar.getTime());

        return dateList;
    }

    /**
     * 返回2个日期中的大者
     */
    public static Date max(Date date1, Date date2) {
        if (date1 == null && date2 == null) {
            return null;
        }
        if (date1 == null) {
            return date2;
        }
        if (date2 == null) {
            return date1;
        }
        if (date1.after(date2)) {
            return date1;
        } else {
            return date2;
        }
    }

    /**
     * 返回不大于date2的日期 如果 date1 >= date2 返回date2 如果 date1 < date2 返回date1
     */
    public static Date ceil(Date date1, Date date2) {
        if (date1 == null && date2 == null) {
            return null;
        }
        if (date1 == null) {
            return date2;
        }
        if (date2 == null) {
            return date1;
        }
        if (date1.after(date2)) {
            return date2;
        } else {
            return date1;
        }
    }

    /**
     * 返回不小于date2的日期 如果 date1 >= date2 返回date1 如果 date1 < date2 返回date2
     */
    public static Date floor(Date date1, Date date2) {
        if (date1 == null && date2 == null) {
            return null;
        }
        if (date1 == null) {
            return date2;
        }
        if (date2 == null) {
            return date1;
        }
        if (date1.after(date2)) {
            return date1;
        } else {
            return date2;
        }
    }

    /**
     * 返回2个日期中的小者
     */
    public static Date min(Date date1, Date date2) {
        if (date1 == null && date2 == null) {
            return null;
        }
        if (date1 == null) {
            return date2;
        }
        if (date2 == null) {
            return date1;
        }
        if (date1.after(date2)) {
            return date2;
        } else {
            return date1;
        }
    }

    /**
     * 判断输入日期是否是一天中的最大时刻
     */
    public static boolean isMaxDayOfDay(Date date1, String precision) {
        if (date1 == null)
            return false;
        Date date2 = getMaxDateOfDay(date1);
        int diffNum = 0;
        if ("HH".equals(precision)) {
            diffNum = getBetweenHourNumber(date1, date2);
        } else if ("mm".equals(precision)) {
            diffNum = getBetweenMinuteNumber(date1, date2);
        } else {
            diffNum = getBetweenSecondNumber(date1, date2);
        }
        return diffNum == 0;
    }

    /**
     * 判断输入日期是否是一天中的最小时刻
     */
    public static boolean isMinDayOfDay(Date date1, String precision) {
        if (date1 == null)
            return false;
        Date date2 = getMinDateOfDay(date1);
        int diffNum = 0;
        if ("HH".equals(precision)) {
            diffNum = getBetweenHourNumber(date1, date2);
        } else if ("mm".equals(precision)) {
            diffNum = getBetweenMinuteNumber(date1, date2);
        } else {
            diffNum = getBetweenSecondNumber(date1, date2);
        }
        return diffNum == 0;
    }

    /**
     * 判断输入日期是否是一天中的最大时刻
     */
    public static boolean isMaxDayOfDay(Date date1) {
        if (date1 == null)
            return false;
        Date date2 = getMaxDateOfDay(date1);
        int secondNum = getBetweenSecondNumber(date1, date2);
        return secondNum == 0;
    }

    /**
     * 判断输入日期是否是一天中的最小时刻
     */
    public static boolean isMinDayOfDay(Date date1) {
        if (date1 == null)
            return false;
        Date date2 = getMinDateOfDay(date1);
        int secondNum = getBetweenSecondNumber(date1, date2);
        return secondNum == 0;
    }

    /**
     * 判断输入日期是否是一月中的最大时刻
     */
    public static boolean isMaxDayOfMonth(Date date1) {
        if (date1 == null)
            return false;
        Date date2 = getMaxDateOfMonth(date1);
        int secondNum = getBetweenSecondNumber(date1, date2);
        return secondNum == 0;
    }

    /**
     * 判断输入日期是否是一月中的最小时刻
     */
    public static boolean isMinDayOfMonth(Date date1) {
        if (date1 == null)
            return false;
        Date date2 = getMinDateOfMonth(date1);
        int secondNum = getBetweenSecondNumber(date1, date2);
        return secondNum == 0;
    }

    /**
     * 输入日期是否为同一天.
     */
    public static boolean isTheSameDay(Date startDate, Date endDate) {
        String startDateStr = dateToString(startDate);
        String endDateStr = dateToString(endDate);
        return startDateStr.equals(endDateStr);
    }

    /**
     * 功能：获取昨天最大时间。 输入: 2010-01-31 00:00:00 返回：2010-01-30 23:59:59
     */
    public static Date getLastMaxDay(Date startDate) {
        if (startDate == null) {
            return null;
        }
        startDate = DateUtils.addDays(startDate, -1);
        return DateTools.getMaxDateOfDay(startDate);
    }

    /**
     * 根据字符串时间,返回Calendar
     */
    public static Calendar getCalendar(String datetimeStr) {
        Calendar cal = Calendar.getInstance();
        if (StringUtils.isNotBlank(datetimeStr)) {
            Date date = DateTools.stringToDate(datetimeStr, DATE_PATTERN_DEFAULT);
            cal.setTime(date);
        }
        return cal;
    }

    /**
     * startStr 或者 startStr-endStr
     */
    public static String getDifferentStr(String startStr, String endStr) {
        String dateRangeStr = "";
        if (startStr.equals(endStr)) {
            dateRangeStr = startStr;
        } else {
            dateRangeStr = startStr + "-" + endStr;
        }
        return dateRangeStr;
    }

    /**
     * 给定一个日期和天数，得到这个日期+天数的日期
     *
     * @param date 指定日期
     * @param day  天数
     * @return
     * @author tangzhi, 2012-11-28
     */
    public static String getNextDay(String date, int num, String format) {
        Date d = stringToDate(date, format);
        Calendar ca = Calendar.getInstance();
        ca.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        ca.setTime(d);

        int day = ca.get(Calendar.DATE);
        day = day + num;
        ca.set(Calendar.DATE, day);
        return getFormatDateTime(ca.getTime(), format);

    }

    /**
     * 给定一个日期和天数，得到这个日期+天数的日期
     *
     * @param date 指定日期
     * @param day  天数
     * @return
     * @author liwenliang, 2014-09-05
     */
    public static Date getNextDay(Date date, int num) {
        Calendar ca = Calendar.getInstance();
        ca.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        ca.setTime(date);

        int day = ca.get(Calendar.DATE);
        day = day + num;
        ca.set(Calendar.DATE, day);
        return ca.getTime();
    }

    /**
     * 根据指定格式获取日期数据
     *
     * @param date    ：指定日期
     * @param pattern ：日期格式
     * @return
     * @author tangzhi, 2012-11-28
     */
    private static String getFormatDateTime(Date date, String pattern) {
        if (null == date) {
            return "";
        }
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        format.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        return format.format(date);
    }

    /**
     * 获取给定日期的下一个月的日期的最晚时间
     *
     * @param startDate
     * @return
     */
    public static Date getNextMonthDay(Date startDate) {
        // 是不是
        // int month = startDate.getMonth();
        Date monthEndDate = getMaxDateOfMonth(startDate);
        Date nextMonth = DateUtils.addMonths(startDate, 1);
        nextMonth = stringToDate(dateToString(nextMonth, DATE_PATTERN_DAY) + DAY_LAST_TIME, DATE_PATTERN_DEFAULT);
        if (isTheSameDay(startDate, monthEndDate)) {
            nextMonth = getMaxDateOfMonth(nextMonth);
        }
        return nextMonth;
    }

    /**
     * 获取给定日期的上一个月的最晚时间
     *
     * @param startDate
     * @return
     */
    public static Date getLastMonthDay(Date startDate) {
        // 是不是
        // int month = startDate.getMonth();
        Date monthEndDate = getMaxDateOfMonth(startDate);
        Date nextMonth = DateUtils.addMonths(startDate, -1);
        nextMonth = stringToDate(dateToString(nextMonth, DATE_PATTERN_DAY) + DAY_LAST_TIME, DATE_PATTERN_DEFAULT);
        if (isTheSameDay(startDate, monthEndDate)) {
            nextMonth = getMaxDateOfMonth(nextMonth);
        }
        return nextMonth;
    }

    /**
     * 获取给定日期的上个月的日期的最后一天
     *
     * @param startDate
     * @return
     */
    public static Date getLastMonthLastDay(Date startDate) {


        return getMaxDateOfMonth(getLastMonthDay(startDate));
    }

    /**
     * 获取给定日期的上一个月的日期的最早时间
     *
     * @param startDate
     * @return
     */
    public static Date getFristMonthDay(Date startDate) {
        // 是不是
        // int month = startDate.getMonth();
        Date monthEndDate = getMaxDateOfMonth(startDate);
        Date nextMonth = DateUtils.addMonths(startDate, -1);
        nextMonth = stringToDate(dateToString(nextMonth, DATE_PATTERN_DAY) + DAY_FIRST_TIME, DATE_PATTERN_DEFAULT);
        if (isTheSameDay(startDate, monthEndDate)) {
            nextMonth = getMaxDateOfMonth(nextMonth);
        }
        return nextMonth;
    }

    /**
     * 获取给定日期的上个月的最早
     *
     * @param startDate
     * @return
     */
    public static Date getFirstMonthFirstDay(Date startDate) {

        return getMinDateOfMonth(getFristMonthDay(startDate));
    }

    /**
     * 获取2个时间相隔几秒,非绝对值
     */
    public static int getBetweenSecondNumberNotAbsolute(Date startDate, Date endDate) {
        if (startDate == null || endDate == null)
            return -1;
//
//        if (startDate.after(endDate)) {
//            Date tmp = endDate;
//            endDate = startDate;
//            startDate = tmp;
//        }

        long timeNumber = -1L;
        long TIME = 1000L;
        try {
            timeNumber = (endDate.getTime() - startDate.getTime()) / TIME;

        } catch (Exception e) {
            log.errorLog(e.getMessage(), e);
        }
        return (int) timeNumber;
    }

    /**
     * 获取2个时间相隔几分钟,非绝对值
     */
    public static int getBetweenMinuteNumberNotAbsolute(Date startDate, Date endDate) {
        if (startDate == null || endDate == null)
            return -1;

//        if (startDate.after(endDate)) {
//            Date tmp = endDate;
//            endDate = startDate;
//            startDate = tmp;
//        }

        long timeNumber = -1l;
        long TIME = 60L * 1000L;
        try {
            timeNumber = (endDate.getTime() - startDate.getTime()) / TIME;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return (int) timeNumber;
    }

    /**
     * 获取2个时间相隔几小时，非绝对值
     */
    public static int getBetweenHourNumberNotAbsolute(Date startDate, Date endDate) {
        if (startDate == null || endDate == null)
            return -1;

//        if (startDate.after(endDate)) {
//            Date tmp = endDate;
//            endDate = startDate;
//            startDate = tmp;
//        }

        long timeNumber = -1l;
        long TIME = 60L * 60L * 1000L;
        try {
            timeNumber = (endDate.getTime() - startDate.getTime()) / TIME;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return (int) timeNumber;
    }

    /**
     * 获取2个时间相隔几天，非绝对值
     */
    public static int getBetweenDayNumberNotAbsolute(Date startDate, Date endDate) {
        if (startDate == null || endDate == null)
            return -1;
//
//        if (startDate.after(endDate)) {
//            Date tmp = endDate;
//            endDate = startDate;
//            startDate = tmp;
//        }

        long dayNumber = -1L;
        long DAY = 24L * 60L * 60L * 1000L;
        try {
            //System.out.println((endDate.getTime() + 1000 - startDate.getTime()));
            dayNumber = (endDate.getTime() - startDate.getTime()) / DAY;
//            System.out.println(endDate.getTime()+" "+startDate.getTime());
        } catch (Exception e) {
            log.errorLog(e.getMessage(), e);
        }
        return (int) dayNumber;
    }

    /**
     * 获取2个时间相隔几月，非绝对值
     */
    public static int getBetweenMonthNumberNotAbsolute(Date startDate, Date endDate) {
        int result = 0;
        try {
            if (startDate == null || endDate == null)
                return -1;

            // swap start and end date
//            if (startDate.after(endDate)) {
//                Date tmp = endDate;
//                endDate = startDate;
//                startDate = tmp;
//            }

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startDate);

            int monthS = calendar.get(Calendar.MONTH);
            int yearS = calendar.get(Calendar.YEAR);

            calendar.setTime(endDate);
            int monthE = calendar.get(Calendar.MONTH);
            int yearE = calendar.get(Calendar.YEAR);

            if (yearE - yearS == 0) {
                result = monthE - monthS;
            } else {
                result = (yearE - yearS - 1) * 12 + (12 - monthS) + monthE;
            }

        } catch (Exception e) {
            log.errorLog(e.getMessage(), e);
        }
        return result;
    }

    /**
     * 获取2个时间相隔几年，非绝对值
     */
    public static int getBetweenYearNumberNotAbsolute(Date startDate, Date endDate) {
        int result = 0;
        try {
            if (startDate == null || endDate == null)
                return -1;

            // swap start and end date
//            if (startDate.after(endDate)) {
//                Date tmp = endDate;
//                endDate = startDate;
//                startDate = tmp;
//            }

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startDate);
            int yearS = calendar.get(Calendar.YEAR);

            calendar.setTime(endDate);
            int yearE = calendar.get(Calendar.YEAR);

            result = yearE - yearS;

        } catch (Exception e) {
            log.errorLog(e.getMessage(), e);
        }
        return result;
    }

    /**
     * 得到给定时间的年份
     *
     * @return
     */
    public static int getYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        return year;
    }

    // 获取给定时间月份，cal.get(Calendar.MONTH)是从零开始。
    public static int getMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH) + 1;
        return month;
    }

    /**
     * 得到给定时间的日
     *
     * @return
     */
    public static int getDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return day;
    }

    /**
     * @param String time 小时分秒
     * @param args
     * @Description 获取date的时间点
     */
    public static Date getNewDateTime(String date, String time) {
        String newDate = date + time;
        Date timeDate = null;
        try {
            timeDate = DateTools.stringToDateTime(newDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return timeDate;
    }

    /**
     * @param startDate
     * @param numDate
     * @return
     * @Description 获取当前时间的numDate后的日期
     */
    public static String getSomeDate(Date startDate, int numDate) {
        String open_time = DateTools.dateTime2String(startDate);//开始招标时间
        return DateTools.getNextDay(open_time, numDate, DATE_PATTERN_DEFAULT);
    }

    public static Date getSomeTime(Date startDate, int numDate) {
        String newDate = DateTools.getSomeDate(startDate, numDate);
        return DateTools.getNewDateTime(newDate, DateTools.SINGLE_AUTO_BID_LOAN_TIME);
    }

    /**
     * 如果date是工作日，返回date， 否则返回下一个最近的工作日
     *
     * @param date
     * @return
     */
    public static Date getNextWorkDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar = getNextWorkDay(calendar);
        return calendar.getTime();
    }

    /**
     * 得到时间段中每一天(包含起止日期)
     *
     * @param dateFirst 起始日期
     * @param dateLast  终止日期
     * @return
     */
    public static LinkedList<String> displayIntervalDate(String dateFirst, String dateLast) {
        LinkedList<String> dateList = new LinkedList<String>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dateOne = dateFormat.parse(dateFirst);
            Date dateTwo = dateFormat.parse(dateLast);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dateOne);
            calendar.add(Calendar.DAY_OF_MONTH, -1);
            while (calendar.getTime().before(dateTwo)) {
                calendar.add(Calendar.DAY_OF_MONTH, 1);
                dateList.add(dateFormat.format(calendar.getTime()));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return dateList;
        }
        return dateList;
    }

    /**
     * 如果date是工作日，返回date， 否则返回下一个最近的工作日
     *
     * @param calendar
     * @return
     */
    public static Calendar getNextWorkDay(Calendar calendar) {
        if (HolidayUtils.isHoliday(calendar)) {
            calendar.add(Calendar.DAY_OF_YEAR, 1);
            getNextWorkDay(calendar);
        }
        return calendar;
    }

    /**
     * 判断date是否在两个日期之间
     *
     * @param date
     * @param startDate
     * @param endDate
     * @return
     */
    public static boolean dateIsBetween(Date date, Date startDate, Date endDate) {
        if (date == null && startDate == null && endDate == null) {
            return false;
        }
        if (date.after(startDate) && date.before(endDate)) {
            return true;
        }
        return false;
    }

    /**
     * 上月字符串
     *
     * @return
     */
    public static String getLastMonthStr() {
        Calendar cal = Calendar.getInstance();
        Date date = new Date();
        cal.setTime(date);
        int delYear = 0;
        int delMonth = cal.get(Calendar.MONTH) - 1;
        String delYearMonth = "";

        if (delMonth == 0) {
            delYear = cal.get(Calendar.YEAR) - 1;
            delMonth = 12;
        } else if (delMonth == -1) {
            delYear = cal.get(Calendar.YEAR) - 1;
            delMonth = 11;
        } else if (delMonth == -2) {
            delYear = cal.get(Calendar.YEAR) - 1;
            delMonth = 10;
        } else {
            delYear = cal.get(Calendar.YEAR);
        }

        if (delMonth < 10) {
            delYearMonth = delYear + "-0" + delMonth + "";
        } else {
            delYearMonth = delYear + "-" + delMonth + "";
        }

        return delYearMonth;
    }

    /**
     * 上个月字符串
     *
     * @return
     */
    public static String getThisMonthStr() {
        Calendar cal = Calendar.getInstance();
        Date date = new Date();
        cal.setTime(date);
        int year = 0;
        int month = cal.get(Calendar.MONTH); // 上个月月份
        String yearMonthly = "";

        if (month == 0) {
            year = cal.get(Calendar.YEAR) - 1;
            month = 12;
        } else {
            year = cal.get(Calendar.YEAR);
        }

        if (month < 10) {
            yearMonthly = year + "-0" + month + "";
        } else {
            yearMonthly = year + "-" + month + "";
        }

        return yearMonthly;
    }

    public static Date getNextTime(Date date, int num) {
        Calendar ca = Calendar.getInstance();
        ca.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        ca.setTime(date);

        int minute = ca.get(Calendar.MINUTE);
        minute = minute + num;
        ca.set(Calendar.MINUTE, minute);
        return ca.getTime();
    }

    public static void main(String[] args) {
        try {
            System.out.println(DateTools.getSomeDate(new Date(), -2));
//            System.out.println(dateToString(getFirstMonthFirstDay(new Date()), DATE_PATTERN_DEFAULT));
//            Date date1 = DateTools.stringToDate( DateTools.getCurrentStatYear()+"-"+13+"-32", DateTools.DATE_PATTERN_DAY);
//            System.out.println(dateTime2String(date1));
//            
//            Date date2 = new Date();
//            
//            int diffDay = DateTools.getBetweenDayNumberNotAbsolute(date1, date2);
//            System.out.println(diffDay);
//            
//            int diffDay1 = DateTools.getBetweenDayNumberNotAbsolute(date2, date1);
//            System.out.println(diffDay1);
//        	System.out.println(DateTools.dateTime2String(new Date()));
//        	
//        	
//            int days = DateTools.getBetweenDayNumberNotAbsolute(DateTools.stringToDate("2013-11-11 18:49:00",DateTools.DATE_PATTERN_DEFAULT), 
//    				DateTools.stringToDate("2013-11-13 18:49:00",DateTools.DATE_PATTERN_DEFAULT));
//    		System.out.println(days);
//        	Date now = DateTools.stringToDateTime("2013-10-16 05:01:00");
//        	Date nextRepayDate = DateTools.stringToDateTime("2013-10-15 05:01:00");
//        	System.out.println(dateTime2String(nextRepayDate));
//        	System.out.println(dateTime2String(now));
//        	
//        	System.out.println(DateTools.getBetweenDayNumberNotAbsolute(nextRepayDate, now));
//        	long s = 1381785000233 - ;
//        	long e = 1381871400253L - 1381785000233L;
//        	System.out.println(e/24/);
//        	String newDate = dateToString(new Date());
//        	String bidLoanLastTime = newDate + DateTools.SINGLE_BID_DAY_LAST_TIME;
//        	Date bidLoanDate = stringToDateTime(bidLoanLastTime);
//        	System.out.println(bidLoanDate);
            System.out.println(getSomeDate(new Date(), -3));
            System.out.println(getBetweenDayNumberIgnoreTime(
                    stringToDateTime("2014-04-30 19:00:36"), stringToDateTime("2015-04-30 03:10:00")));
            System.out.println(getNextDay(new Date(), -11));
        } catch (Throwable e) {
            System.out.println(e);
        }
        System.out.println(getNextTime(new Date(), 60));
    }
}