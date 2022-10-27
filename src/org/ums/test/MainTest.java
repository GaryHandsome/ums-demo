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
        UserInfoDao userInfoDao = new UserInfoDaoImpl();
        UserInfo user = userInfoDao.selectUserInfoByName("admin");
        System.out.println(user);
    }
}
