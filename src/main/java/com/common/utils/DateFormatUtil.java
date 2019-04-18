package com.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtil {
    public static final String SIMPLE_FORMAT="yyyy-MM-dd";
    public static final String FORMAT="yyyy-MM-dd HH:mm:ss";
    public static final String POINT_FORMAT="yyyy.MM.dd";
    public static final String MONTH_FORMAT="yyyy-MM";
    public static final String MONTH="MM";
    public static final String TIME="HH:mm:ss";
    public static final String DAY="dd";
    public static final String CHINESE="MM月dd日";

    public static String formatDate(Date date,String patter){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat(patter);
        return simpleDateFormat.format(date);
    }

    public static Date parseDate(String date,String patter){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat(patter);
        Date parse =null;
        try {
             parse = simpleDateFormat.parse(date);
        } catch (ParseException e) {

        }
        return parse;
    }
}
