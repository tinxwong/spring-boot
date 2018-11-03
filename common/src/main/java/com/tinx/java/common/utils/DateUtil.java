package com.tinx.java.common.utils;


import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by baibg1 on 2016-7-20.
 */
public final class DateUtil {
    public static final String DATE_PATTERN = "yyyy-MM-dd";
    public static final String DATE_INTEGER_PATTERN = "yyyyMMdd";
    public static final String TIMESTAMP_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String TIME_PATTERN = "HH:mm:ss";
    private static Logger logger = LoggerFactory.getLogger(DateUtil.class);

    private DateUtil() {
    }


    public static Date getBeginningOfDay(Date date) {
        date = DateUtils.setHours(date, 0);
        date = DateUtils.setMinutes(date, 0);
        date = DateUtils.setSeconds(date, 0);
        date = DateUtils.setMilliseconds(date, 0);
        return date;
    }

    public static Date getEndingOfDay(Date date) {
        date = DateUtils.setHours(date, 23);
        date = DateUtils.setMinutes(date, 59);
        date = DateUtils.setSeconds(date, 59);
        date = DateUtils.setMilliseconds(date, 999);
        return date;
    }

    public static Date getBeginningOfWeek(Date date) {
        date = DateUtils.addDays(date, -1 * date.getDay());
        return getBeginningOfDay(date);
    }

    public static Date getEndingOfWeek(Date date) {
        date = DateUtils.addDays(date, -1 * date.getDay() + 6);
        return getEndingOfDay(date);
    }

    public static Date getBeginningOfMonth(Date date) {
        date = DateUtils.setDays(date, 1);
        return getBeginningOfDay(date);
    }

    public static Date getEndingOfMonth(Date date) {
        date = getBeginningOfMonth(date);
        date = DateUtils.addMonths(date, 1);
        date = DateUtils.addDays(date, -1);
        return getEndingOfDay(date);
    }

    public static Date getEndingOfYear(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int maxDay = c.getActualMaximum(Calendar.DAY_OF_YEAR);
        int today = c.get(Calendar.DAY_OF_YEAR);
        return new Date((maxDay - today) * 24 * 60 * 60 * 1000);
    }

    public static boolean isBetween(Date source, Date date1, Date date2) {
        if (source == null && date1 == null && date2 == null) {
            return true;
        }

        if (source == null || date1 == null || date2 == null) {
            return false;
        }
        return source.compareTo(date1) >= 0 && source.compareTo(date2) <= 0;
    }

    public static Date addMonth(Date date, int amount) {
        return DateUtils.addMonths(date, amount);
    }

    public static Date addDay(Date date, int amount) {
        return DateUtils.addDays(date, amount);
    }

    /**
     * 获取当前时间
     * <p>
     * createTime 2017-8-23 15:53
     *
     * @return Date
     * @author 陆国鸿
     */
    public static Date getCurrentDate() {
        return getCurrentDate(true);
    }

    /**
     * 
     * 创建时间 2018-8-9 14:09
     * @author 陆国鸿
     * @param needTime 是否需要时间部分
     * @return
     */
    public static Date getCurrentDate(boolean needTime) {
        Calendar now = Calendar.getInstance();
        if (needTime) {
            return now.getTime();
        } else {
            now.set(Calendar.HOUR_OF_DAY, 0);
            now.set(Calendar.MINUTE, 0);
            now.set(Calendar.SECOND, 0);
            now.set(Calendar.MILLISECOND, 0);
            return now.getTime();
        }
    }

    /**
     * 字符串转日期
     * 创建时间 2017-8-25 18:14
     *
     * @param dateStr
     * @param pattern
     * @return
     */
    public static Date parseDate(String dateStr, String pattern) {
        try {
            return DateUtils.parseDate(dateStr, pattern);
        } catch (ParseException e) {
            throw new IllegalArgumentException("dateStr is not fitted for pattern");
        }
    }

    public static Date parseDate(String dateStr) {
        SimpleDateFormat format = null;
        if (StringUtils.isBlank(dateStr))
            return null;
        String _dateStr = dateStr.trim();
        try {
            if (_dateStr.matches("\\d{1,2}[A-Z]{3}")) {
                _dateStr = _dateStr + (Calendar.getInstance().get(Calendar.YEAR) - 2000);
            }
            // 01OCT12
            if (_dateStr.matches("\\d{1,2}[A-Z]{3}\\d{2}")) {
                format = new SimpleDateFormat("ddMMMyy", Locale.ENGLISH);
            } else if (_dateStr.matches("\\d{1,2}[A-Z]{3}\\d{4}.*")) {// 01OCT2012
                // ,01OCT2012
                // 1224,01OCT2012
                // 12:24
                _dateStr = _dateStr.replaceAll("[^0-9A-Z]", "").concat("000000").substring(0, 15);
                format = new SimpleDateFormat("ddMMMyyyyHHmmss", Locale.ENGLISH);
            } else {
                StringBuffer sb = new StringBuffer(_dateStr);
                String[] tempArr = _dateStr.split("\\s+");
                tempArr = tempArr[0].split("-|\\/");
                if (tempArr.length == 3) {
                    if (tempArr[1].length() == 1) {
                        sb.insert(5, "0");
                    }
                    if (tempArr[2].length() == 1) {
                        sb.insert(8, "0");
                    }
                }
                _dateStr = sb.append("000000").toString().replaceAll("[^0-9]", "").substring(0, 14);
                if (_dateStr.matches("\\d{14}")) {
                    format = new SimpleDateFormat("yyyyMMddHHmmss");
                }
            }

            if (format == null) {
                return null;
            }
            Date date = format.parse(_dateStr);
            return date;
        } catch (Exception e) {
            throw new RuntimeException("无法解析日期字符[" + dateStr + "]");
        }
    }

    public static String format(Date date, String... patterns) {
        if (date == null)
            return "";
        String pattern = TIMESTAMP_PATTERN;
        if (patterns != null && patterns.length > 0 && !StringUtils.isBlank(patterns[0])) {
            pattern = patterns[0];
        }
        return DateFormatUtils.format(date, pattern);
    }

    /**
     * 把日期部分格式化成整型
     *
     * @param date 日期
     * @return Integer
     */
    public static Integer formatDate2Integer(Date date) {
        String dateString = format(date, DATE_INTEGER_PATTERN);
        Integer result = Integer.parseInt(dateString);
        return result;
    }

    public static Date getSaasCreateDate() {
        SimpleDateFormat beferDateFormat = new SimpleDateFormat(DATE_PATTERN);
        Date saasDate = null;
        try {
            saasDate = beferDateFormat.parse("2017-09-13");
        } catch (ParseException e) {
            logger.error("", e);
        }
        return saasDate;
    }

    public static Date getDate(String type, String strDate) {
        SimpleDateFormat beferDateFormat = new SimpleDateFormat(type);
        Date date = null;
        try {
            date = beferDateFormat.parse(strDate);
        } catch (ParseException e) {
            logger.error("", e);
        }
        return date;
    }

    public static Map<String, Date> convertMonthByDate(Date date) {
        Map<String, Date> map = new HashMap<String, Date>();
        // 指定日期月第一天
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.DATE, 1);
        Date theDate = calendar.getTime();
        // 指定日期月最后一天
        calendar.add(Calendar.MONTH, 1); // 加一个月
        calendar.set(Calendar.DATE, 1); // 设置为该月第一天
        calendar.add(Calendar.DATE, -1); // 再减一天即为上个月最后一天
        calendar.set(Calendar.HOUR, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        map.put("first", theDate);
        map.put("last", calendar.getTime());
        return map;
    }

    public static List<String> getMonthBetween(String minDate, String maxDate) {
        ArrayList<String> result = new ArrayList<String>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");//格式化为年月

        Calendar min = Calendar.getInstance();
        Calendar max = Calendar.getInstance();

        try {
            min.setTime(sdf.parse(minDate));
            min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);

            max.setTime(sdf.parse(maxDate));
            max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);

        } catch (ParseException e) {
            logger.error(e.getMessage(), e);
        }
        Calendar curr = min;
        while (curr.before(max)) {
            result.add(sdf.format(curr.getTime()));
            curr.add(Calendar.MONTH, 1);
        }

        return result;
    }
}
