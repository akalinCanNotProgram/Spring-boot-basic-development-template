package com.akalin.template.comment.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateTimeUtil {
    // 获取上月第一天
    public static String getUpFirstDay() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -1);
        c.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
        String first = format.format(c.getTime());
        return first;
    }

    // 获取上月最后一天
    public static String getUpLastDay() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.DAY_OF_MONTH,0);//设置上月最后一天
        String last = format.format(ca.getTime());
        return last;
    }
    // 获取当前月第一天
    public static String getFirstDay() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
        String first = format.format(c.getTime());
        return first;
    }

    // 获取当前月最后一天
    public static String getLastDay() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.DAY_OF_MONTH, ca
                .getActualMaximum(Calendar.DAY_OF_MONTH));
        String last = format.format(ca.getTime());
        return last;
    }
    // 获取下个月第一天
    public static String nextMonthFirstDay() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 1);
        c.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为下个月第一天
        String first = format.format(c.getTime());
        return first;
    }
    // 获取下个月最后一天
    public static String nextMonthLastDay() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 2);
        c.set(Calendar.DAY_OF_MONTH, 0);//设置下个月最后一天
        String first = format.format(c.getTime());
        return first;
    }


    public static String secToTime(Long time) {
        String timeStr = null;
        Long day =Long.valueOf(0);
        Long hour =Long.valueOf(0);
        Long minute =Long.valueOf(0);
        Long second =Long.valueOf(0);
        if (time <= 0)
            return "无超时";
        else {
            minute = time / 60;
            if (minute < 60) {
                second = time % 60;
                timeStr = unitFormat(minute) + "分";
            } else {
                hour = minute / 60;
                minute = minute % 60;
                second = time - hour * 3600 - minute * 60;
                timeStr = unitFormat(minute) + "分";
                if(hour >= 8){
                    day=hour/8;
                    hour=hour%8;
                    timeStr=unitFormat(day) + "天"+unitFormat(hour) + "小时"+unitFormat(minute) + "分";
                }else {
                    timeStr = unitFormat(hour) + "小时"+unitFormat(minute) + "分";
                }
            }
            timeStr=timeStr.toString();
        }
        return timeStr;
    }
    public static String unitFormat(Long i) {
        String retStr = null;
        if (i >= 0 && i < 10)
            retStr = "0" + Long.toString(i);
        else
            retStr = "" + i;
        return retStr;
    }

}
