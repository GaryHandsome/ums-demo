package org.ums.servlet;

import org.ums.vo.ResponseData;

import javax.servlet.http.HttpServlet;

/**
 * 所有 Servlet 的父类 - 封装公共方法
 *
 * @Date 2022-10-31
 * @Author zqx
 */
public class BaseServlet extends HttpServlet {
    /**
     * 成功响应的封装 - 默认
     *
     * @return
     */
    public ResponseData success(Object data) {
        return new ResponseData(200, "", data);
    }

    /**
     * 重载方法 - 封装所有的成功响应信息
     *
     * @param code
     * @param msg
     * @param data
     * @return
     */
    public ResponseData success(Integer code, String msg, Object data) {
        return new ResponseData(code, msg, data);
    }

    /**
     * 错误响应的封装 - 默认
     *
     * @param msg
     * @return
     */
    public ResponseData error(String msg) {
        return new ResponseData(500, msg, null);
    }

    /**
     * 重载方法 - 封装所有的错误响应信息
     *
     * @param code
     * @param msg
     * @param data
     * @return
     */
    public ResponseData error(Integer code, String msg, Object data) {
        return new ResponseData(code, msg, data);
    }
}
