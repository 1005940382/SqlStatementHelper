package com.orange.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 鞠鹏飞
 * @data 2017/8/23
 * @Description 时间工具类
 */
public class TimeUtil
{
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 字符串转时间戳
     * @param dateString 时间字符串
     * @return 13位时间戳
     */
    public static String getTimestampByString(String dateString)
    {
        try
        {
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_FORMAT);
            Date date = sdf.parse(dateString);
            return date.getTime() + "";
        }
        catch(ParseException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 判断字符串是否为标准的日期格式
     * @param time 待判定字符串
     * @return 是否为日期格式
     */
    public static boolean isDateTime(String time)
    {
        boolean convertSuccess = true;
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_FORMAT);
        try
        {
            sdf.setLenient(false);
            sdf.parse(time);
        }
        catch(ParseException e)
        {
            convertSuccess = false;
        }
        return convertSuccess;
    }

    /**
     * 判断字符串是否为纯数字
     * @param string 待判定字符串
     * @return 是否为纯数字格式
     */
    public static boolean isNumeric(String string)
    {
        for(int i = 0; i < string.length(); i++)
        {
            if(!Character.isDigit(string.charAt(i)))
            {
                return false;
            }
        }
        return true;
    }

    /**
     * 格式化时间字符串
     * @param time 时间字符串
     * @return 13位时间戳
     */
    public static String translateTimestamp(String time)
    {
        String oldValue = time;
        String newValue = oldValue;
        //Datetime-->13位时间戳
        if(TimeUtil.isDateTime(oldValue))
        {
            newValue = TimeUtil.getTimestampByString(oldValue);
        }
        //10位时间戳-->13位时间戳
        else if(oldValue.length() == 10 && TimeUtil.isNumeric(oldValue))
        {
            newValue = oldValue + "000";
        }
        return newValue;
    }
}
