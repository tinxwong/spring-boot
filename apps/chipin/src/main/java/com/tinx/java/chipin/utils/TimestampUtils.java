package com.tinx.java.chipin.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author tinx
 * @date 2018-10-23 21:15
 */
public class TimestampUtils {


    public static long getNextChipinTime(Map<String,String>  map){

        long chipinTime = getRandom(getTimestamp(map.get("start")),getTimestamp(map.get("end")));
        return chipinTime;
    }


    public static Map<String,String> getChipinTimeScope(){
        String currDate = currDate("yyyy-MM-dd HH:mm:ss");
        long currTimestamp = getTimestamp(currDate);
        Map<String,String> map = new HashMap<>();
        if(firstStage(currTimestamp)||thirdStage(currTimestamp)){
            map = getTimeScope(5);
        }
        if(secondStage(currTimestamp)){
            map = getTimeScope(10);
        }
        return map;
    }


    public static Map<String,String> getTimeScope(int base){
        Map<String,String> map = new HashMap<>();
        String currDate = currDate("yyyy-MM-dd HH:mm:ss");
        String[] arrDate = currDate.split(" ");
        String date = arrDate[0];
        String time = arrDate[1];
        String[] arrTime = time.split(":");
        String clock = arrTime[0];
        String minute = arrTime[1];
        String second = arrTime[2];
        int scope = Integer.parseInt(minute)/base;

        String startScope = "";
        String endScope = "";
        if(base==10){
            scope = (scope+1)*10;
            if(scope==60){
                clock = ""+(Integer.parseInt(clock)+1);
                startScope = "01";
                endScope = "09";
            }else{
                startScope = ""+(scope+1);
                endScope = ""+(scope+9);
            }

        }
        if(base==5){
            int temp = (scope+1)*base;
            System.out.println("temp"+temp);
            if(temp>=60){
                clock = ""+(Integer.parseInt(clock)+1);
                startScope = "01";
                endScope = "04";
            }else{
                if(scope%2==1){
                    startScope = ""+(temp+1);
                    endScope = ""+(temp+4);
                }
                if(scope%2==0){
                    startScope = ""+(temp+6);
                    endScope = ""+(temp+9);
                }
            }

        }

        minute = ""+startScope;
        second = "30";
        String startTime = String.format("%s %s:%s:%s",date,clock,minute,second);

        minute = ""+endScope;
        second = "00";
        String endTime = String.format("%s %s:%s:%s",date,clock,minute,second);
        map.put("start",startTime);
        map.put("end",endTime);
        return map;
    }

    public static String currDate(String format) {
        if(StringUtils.isEmpty(format)){
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat df = new SimpleDateFormat(format);//设置日期格式
        return df.format(new Date());// new Date()为获取当前系统时间
    }


    /**
     * 早上0-2
     * @return
     */
    public static boolean firstStage(long timestamp){
        String date = currDate("yyyy-MM-dd");
        String startTime = String.format("%s 00:00:00",date);
        String endTime = String.format("%s 02:00:00",date);
        long startTimestamp = getTimestamp(startTime);
        long endTimestamp = getTimestamp(endTime);
        if(timestamp>startTimestamp&&timestamp<endTimestamp){
            System.out.println("在"+startTime+"和"+endTime+"时间段之间");
            return true;
        }
        return false;
    }

    /**
     * 10:00-22:00
     * @return
     */
    public static boolean secondStage(long timestamp){

        String date = currDate("yyyy-MM-dd");
        String startTime = String.format("%s 09:50:00",date);
        String endTime = String.format("%s 21:59:59",date);

        long startTimestamp = getTimestamp(startTime);
        long endTimestamp = getTimestamp(endTime);


        if(timestamp>startTimestamp&&timestamp<endTimestamp){
            System.out.println("在"+startTime+"和"+endTime+"时间段之间");
            return true;
        }
        return false;
    }

    /**
     * 22:00-23:59
     * @return
     */
    public static boolean thirdStage(long timestamp){
        String date = currDate("yyyy-MM-dd");
        String startTime = String.format("%s 22:00:00",date);
        String endTime = String.format("%s 23:59:59",date);
        long startTimestamp = getTimestamp(startTime);
        long endTimestamp = getTimestamp(endTime);
        if(timestamp>startTimestamp&&timestamp<endTimestamp){
            System.out.println("在"+startTime+"和"+endTime+"时间段之间");
            return true;
        }
        return false;
    }

    public static long getTimestamp(String time){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = df.parse(time);
        } catch (ParseException e) {
            return 0L;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        long timestamp = cal.getTimeInMillis();
        return timestamp/1000;
    }

    public static long getRandom(long min, long max){
        Long s = (long)(min + (max-min)*Math.random());
        return s;

    }

    public static String timeStamp2Date(String seconds,String format) {
        if(StringUtils.isEmpty(seconds)) {
            return "";
        }
        if(StringUtils.isEmpty(format)){
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(seconds+"000")));
    }

    public static String transForDate(Long ms) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date temp = null;
        String str = null;
        if (ms != null) {
            str = sdf.format(ms);
        }
        return str;
    }

}
