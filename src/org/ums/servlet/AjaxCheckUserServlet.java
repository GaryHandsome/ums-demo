package org.ums.servlet;

import com.google.gson.Gson;
import org.ums.dao.UserInfoDao;
import org.ums.dao.impl.UserInfoDaoImpl;
import org.ums.entity.UserInfo;
import org.ums.vo.ResponseData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 异步请求实现检查用户是否已经被注册
 *
 * @Date 2022-10-27
 * @Author zqx
 */
@WebServlet("/check_user.do")
public class AjaxCheckUserServlet extends BaseServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 第一：获取表单中输入的数据
        String uname = req.getParameter("username");

        // 第二：加工处理 - 通过 DAO 对象，查询数据
        UserInfoDao userInfoDao = new UserInfoDaoImpl();
        UserInfo user = userInfoDao.selectUserInfoByName(uname);

        // 第三：定义响应对象，封装响应数据
        ResponseData responseData = null;

        if (user == null) {
            responseData = success(200,"此帐号未注册，可用","");
        } else {
            responseData = error(500,"此帐号已注册，不可用","");
        }

        // 第四：把响应数据转化为 JSON 字符串，并打印输出
        String json = new Gson().toJson(responseData);
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter out = resp.getWriter();
        out.print(json);

        out.flush();
        out.close();

    }
}
