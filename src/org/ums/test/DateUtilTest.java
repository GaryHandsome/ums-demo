package org.ums.test;

import org.ums.util.DateUtil;

import java.util.Date;

/**
 * @Date 2022-11-03
 * @Author zqx
 */
public class DateUtilTest {
    public static void main(String[] args) {
        // Date date = DateUtil.parseDate("2022-11-03");
        // Date date = DateUtil.parseDate("11/03/2022",DateUtil.PATTERN_EN);
        Date date = DateUtil.parseDate("03/2022/11","dd/yyyy/MM");
        System.out.println(date);
    }
}
