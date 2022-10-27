package org.ums.dao;

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
    void selectUserInfoByName(String username);
}
