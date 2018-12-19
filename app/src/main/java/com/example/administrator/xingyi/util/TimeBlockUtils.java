package com.example.administrator.xingyi.util;

/**
 * Project Name:  Xingyi
 * Date:  2018/12/18 0018
 * Author:  Infinity
 */
public class TimeBlockUtils {
    public static String getTimeBlock(int i){
        switch (i){
            case 0:
                return "00:00-01:00";
            case 1:
                return "01:00-02:00";
            case 2:
                return "02:00-03:00";
            case 3:
                return "03:00-04:00";
            case 4:
                return "04:00-05:00";
            case 5:
                return "05:00-06:00";
            case 6:
                return "06:00-07:00";
            case 7:
                return "07:00-08:00";
            case 8:
                return "08:00-09:00";
            case 9:
                return "09:00-10:00";
            case 10:
                return "10:00-11:00";
            case 11:
                return "11:00-12:00";
            case 12:
                return "12:00-13:00";
            case 13:
                return "13:00-14:00";
            case 14:
                return "14:00-15:00";
            case 15:
                return "15:00-16:00";
            case 16:
                return "16:00-17:00";
            case 18:
                return "18:00-19:00";
            case 19:
                return "19:00-20:00";
            case 20:
                return "20:00-21:00";
            case 21:
                return "21:00-22:00";
            case 22:
                return "22:00-23:00";
            case 23:
                return "23:00-24:00";
            default:
                return "无";
        }
    }
}
