package org.ums.dao;

import org.ums.entity.UserInfo;

/**
 * DAO接口 - 用户
 *
 * @Date 2022-10-26
 * @Author zqx
 */
public interface UserInfoDao {
    /**
     * 根据帐号查询用户信息 - 服务用户登录业务
     *
     * @param username
     */
    UserInfo selectUserInfoByName(String username);

    /**
     * 添加用户
     *
     * @param userInfo
     * @return
     */
    int insertUserInfo(UserInfo userInfo);
}
