package com.hrms.common.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 时间处理工具类
 * Created by QSJ on 2018/4/25.
 */
public class DateUtil {

    /**
     * 时间戳转换成日期格式字符串
     * @param seconds 精确到秒的字符串
     * @param format
     * @return
     */
    public static String timeStamp2Date(String seconds,String format) {
        if(seconds == null || seconds.isEmpty()){
            return "";
        }
        if(format == null || format.isEmpty()){
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Long resultDate = Long.valueOf(seconds)*1000L;
        Date date = new Date(resultDate);
        return sdf.format(date);
    }
    /**
     * 日期格式字符串转换成时间戳
     * @param date_str 字符串日期
     * @param format 如：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static Timestamp date2TimeStamp(String date_str, String format){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            Date date = sdf.parse(date_str);
            long rs = date.getTime();
            String result = sdf.format(rs);
            return Timestamp.valueOf(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 取得当前时间戳（精确到秒）
     * @return
     */
    public static String timeStamp(){
        long time = System.currentTimeMillis();
        String t = String.valueOf(time/1000);
        return t;
    }

    /**
     * 获取某年第一天日期
     * @param year 年份
     * @return Date
     */
    public static java.sql.Date getYearFirst(String year) throws Exception{
        String years = "";
        if(year.length()==4){
            years = year + "-01" + "-01";
        } else{
            years = year;
        }
        return java.sql.Date.valueOf(years);
    }

    /**
     * 返回当前系统时间
     * @param format 时间格式
     * @return
     * @throws Exception
     */
    public static Date getInDate(String format) throws Exception{
        SimpleDateFormat simple = new SimpleDateFormat(format);
        Date date = new Date();
        String res = simple.format(date);
        return simple.parse(res);
    }

    /**
     * 返回当前系统的时间戳
     * @param format
     * @return
     * @throws Exception
     */
    public static Timestamp getInTimestam(String format) throws Exception{
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            Date date = new Date();
            long rs = date.getTime();
            String result = sdf.format(rs);
            return Timestamp.valueOf(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将字符串日期转换为Date
     * @param date 字符串日期
     * @param format 格式
     * @return
     */
    public static Date getStrtoDate(String date,String format) throws Exception{
        SimpleDateFormat simple = new SimpleDateFormat(format);
        if(date==null || "".equals(date)){
            Date nowdate = new Date();
            String res = simple.format(nowdate);
            return simple.parse(res);
        }else{
            return simple.parse(date);
        }
    }

    /**
     * 通过开始时间和结束时间来获取中间的日期
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param simple 时间格式
     * @return
     */
    public static List<String> getTimeSlot(String startTime,String endTime,String simple){
        try{
            List<String> list = new ArrayList<>();
            SimpleDateFormat sdf = new SimpleDateFormat(simple);
            Calendar cal = Calendar.getInstance();
            cal.setTime(sdf.parse(startTime));

            for (long d = cal.getTimeInMillis(); d <= sdf.parse(endTime).getTime(); d = getDayPlaus(cal)) {
                list.add(sdf.format(d));
            }
            return list;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static long getDayPlaus(Calendar c) {
        c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) + 1);
        return c.getTimeInMillis();
    }

    /**
     * 字符串形式返回系统当前时间
     * @param date 时间
     * @param format 时间格式
     * @return
     */
    public static String getNowDate(Date date,String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        if(date==null){
            date = new Date();
        }
        return sdf.format(date);
    }

    /**
     * 返回以前的日期
     * @param s 需要返回的时间数 s=7 返回一周以前的日期
     * @param format 日期格式
     * @return
     */
    public static String getPastSevenTime(int s,String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Calendar c = Calendar.getInstance();
        //过去七天
        c.setTime(new Date());
        c.add(Calendar.DATE, s);
        Date d = c.getTime();
        return sdf.format(d);
    }

}
