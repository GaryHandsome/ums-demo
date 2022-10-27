package org.ums.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 连接数据库的工具类
 *
 * @Date 2022-10-27
 * @Author zqx
 */
public class DBUtil {
    /**
     * 连接驱动程序
     */
    private static final String DRIVER = "net.sourceforge.jtds.jdbc.Driver";
    /**
     * 连接URL
     */
    private static final String URL = "jdbc:jtds:sqlserver://localhost:1433;DatabaseName=ums";
    /**
     * 帐号
     */
    private static final String USER = "sa";
    /**
     * 密码
     */
    private static final String PASS = "123";

    /**
     * 静态代码块
     */
    static {
        try {
            // 加载驱动 - 驱动 JDBC 接口的实现
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取连接对象 - 建立与数据库联系的桥梁
     *
     * @return
     */
    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            System.out.println("获取连接对象失败...");
            e.printStackTrace();
        }
        return conn;
    }

}
