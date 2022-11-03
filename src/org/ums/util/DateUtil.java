package org.ums.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * 日期相关操作的工具类
 *
 * @Date 2022-11-03
 * @Author zqx
 */
public class DateUtil {

    /**
     * 默认日期格式
     */
    public static final String PATTERN_DEFAULT = "yyyy-MM-dd";
    /**
     * 中国日期格式
     */
    public static final String PATTERN_CN = "yyyy年MM月dd日";
    /**
     * 欧美日期格式
     */
    public static final String PATTERN_EN = "MM/dd/yyyy";

    /**
     * 把字符串类型的日期 转换为 java.util.Date 类型的日期
     *
     * @param date    字符串类型的日期
     * @param pattern 指定字符串类型日期的格式
     * @return
     */
    public static java.util.Date parseDate(String date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException("日期转换失败", e);
        }
    }

    /**
     * 使用默认日期格式转换日期对象
     *
     * @param date
     * @return
     */
    public static java.util.Date parseDate(String date) {
        return parseDate(date, DateUtil.PATTERN_DEFAULT);
    }
}
