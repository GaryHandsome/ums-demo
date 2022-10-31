package org.ums.dao.impl;

import org.ums.dao.UserInfoDao;
import org.ums.entity.UserInfo;
import org.ums.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户DAO的实现类
 *
 * @Date 2022-10-27
 * @Author zqx
 */
public class UserInfoDaoImpl implements UserInfoDao {
    @Override
    public UserInfo selectUserInfoByName(String username) {

        UserInfo user = null ;

        // 第一：定义要操作数据库的SQL语句
        String sql = "select id,username,password,user_age,user_sex,weight from userinfo where username=?" ;

        Connection conn = null ;
        PreparedStatement pstmt = null ;
        ResultSet rst = null ;

        try {
            // 第二：获取连接对象
            conn = DBUtil.getConnection();

            // 第三：预编译SQL语句，实例化语句对象
            pstmt = conn.prepareStatement(sql);

            // 第四：填充数据
            pstmt.setString(1,username);

            // 第五：执行SQL语句，并接收执行的结果
            rst = pstmt.executeQuery();

            // 第六：对象结果进行处理
            if(rst.next()) {
                // 1）如果有数据，则创建用户对象，否则返回user默认为null
                user = new UserInfo() ;

                // 2）获取数据表中各列的数据
                int id = rst.getInt(1);
                // String username = rst.getString(2);
                String password = rst.getString(3);
                int age = rst.getInt(4);
                String sex = rst.getString(5);
                double weight = rst.getDouble(6);

                // 3）把各列数据，封装到用户对象中
                user.setId(id);
                user.setUsername(username);
                user.setPassword(password);
                user.setUserAge(age);
                user.setUserSex(sex);
                user.setWeight(weight);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            // 第七：关闭相关的对象
            DBUtil.close(rst,pstmt,conn);
        }

        return user ;
    }

    @Override
    public int insertUserInfo(UserInfo userInfo) {
        int r = 0 ;

        // 第一：定义要操作数据库的SQL语句
        String sql = "insert into userinfo(username,password,user_age,user_sex,weight) values (?,?,?,?,?)" ;

        Connection conn = null ;
        PreparedStatement pstmt = null ;


        try {
            // 第二：获取连接对象
            conn = DBUtil.getConnection();

            // 第三：预编译SQL语句，实例化语句对象
            pstmt = conn.prepareStatement(sql);

            // 第四：填充数据
            pstmt.setString(1,userInfo.getUsername());
            pstmt.setString(2,userInfo.getPassword());
            pstmt.setInt(3,userInfo.getUserAge());
            pstmt.setString(4,userInfo.getUserSex());
            pstmt.setDouble(5,userInfo.getWeight());

            // 第五：执行SQL语句，并接收执行的结果
            r = pstmt.executeUpdate();

            // 第六：对象结果进行处理

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            // 第七：关闭相关的对象
            DBUtil.close(null,pstmt,conn);
        }

        return r ;
    }
}
