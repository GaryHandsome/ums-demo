package org.ums.test;

import org.ums.dao.UserInfoDao;
import org.ums.dao.impl.UserInfoDaoImpl;
import org.ums.entity.UserInfo;

/**
 * @Date 2022-10-27
 * @Author zqx
 */
public class MainTest {
    public static void main(String[] args) {
        // 实例化 DAO 对象
        UserInfoDao userInfoDao = new UserInfoDaoImpl();

        // 通过 DAO 对象操作数据库，根据帐号查询用户信息
        UserInfo user = userInfoDao.selectUserInfoByName("admin");

        // 输出结果
        System.out.println(user);
    }
}
