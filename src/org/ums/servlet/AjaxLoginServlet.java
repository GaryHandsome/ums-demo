package org.ums.servlet;

import com.google.gson.Gson;
import org.ums.dao.UserInfoDao;
import org.ums.dao.impl.UserInfoDaoImpl;
import org.ums.entity.UserInfo;
import org.ums.vo.ResponseData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 异步请求实现实现用户登录
 *
 *  我希望所有 Servlet 都可以访问封装的公共方法,怎么做？
 *
 * @Date 2022-10-27
 * @Author zqx
 */
@WebServlet("/ajax_login.do")
public class AjaxLoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 第一：获取表单中输入的数据
        String username = req.getParameter("uname");
        String password = req.getParameter("upass");

        // 第二：加工处理 - 通过 DAO 对象，查询数据
        UserInfoDao userInfoDao = new UserInfoDaoImpl();
        UserInfo user = userInfoDao.selectUserInfoByName(username);

        // 第三：定义响应对象，封装响应数据
        ResponseData responseData = null ;
        if (user == null) {
            responseData = new ResponseData(500,"查无此人，请注册",null) ;
        } else if(!user.getPassword().equals(password)) {
            responseData = new ResponseData(500,"错误帐号或密码",null) ;
        }
        responseData = new ResponseData(200,"登录成功",user) ;

        // 第四：把响应数据转化为 JSON 字符串，并打印输出
        String json = new Gson().toJson(responseData);

        PrintWriter out = resp.getWriter();
        out.print(json);
        out.flush();
        out.close();

    }
}
