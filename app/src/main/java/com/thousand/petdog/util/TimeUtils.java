package com.thousand.petdog.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {

    public static final int DATE_ALL = 0;
    public static final int DATE_DAY = 1;
    public static final int DATE_YEAR = 2;
    public static final int DATE_MONTH = 3;


    private  static  SimpleDateFormat sfDateAll = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private  static  SimpleDateFormat sfDate = new SimpleDateFormat("yyyy-MM-dd");
    private  static  SimpleDateFormat sfDateYear = new SimpleDateFormat("dd");
    private  static  SimpleDateFormat sfDateMonth = new SimpleDateFormat("yyyy-MM");

    public static String getDateString(long time,int type){
        Date d = new Date(time);
        if (type==DATE_ALL){
            return sfDateAll.format(d);     //返回时间到秒
        }else if (type==DATE_DAY){
            return sfDate.format(time);
        }else if (type==DATE_YEAR){
            return sfDateYear.format(d);
        } else if (type==DATE_MONTH){
            return sfDateMonth.format(d);
        }

        return sfDate.format(time);     //默认返回时间到天
    }

    public static String getTimeInterval(Date date1,Date date2){
        int day = getDayTimeInterval(date1,date2);
        String interval = getDayTimeInterval(date1,date2)+"天";
        if (day > 31) interval = getMonthTimeInterval(date1,date2)+"月";
        return interval;
    }

    private static int getDayTimeInterval(Date date1,Date date2){
        return (int) ((date1.getTime() - date2.getTime()) / (1000*3600*24));
    }

    private static int getMonthTimeInterval(Date date1,Date date2){
        return (int) ((date1.getTime() - date2.getTime()) / (1000*3600*24*30));
    }


}
