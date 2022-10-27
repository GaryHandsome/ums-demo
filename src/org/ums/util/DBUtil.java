package org.ums.util;

import java.sql.*;

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


    /**
     * 关闭相关的对象
     *
     * @param rst 结果集对象
     * @param stmt 语句对象
     * @param conn 连接对象
     */
    public static void close(ResultSet rst, Statement stmt, Connection conn) {
        if (rst != null) {
            try {
                rst.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(DBUtil.getConnection());
    }
}
