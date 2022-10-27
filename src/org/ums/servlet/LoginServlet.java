package org.ums.servlet;

import org.ums.dao.UserInfoDao;
import org.ums.dao.impl.UserInfoDaoImpl;
import org.ums.entity.UserInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Date 2022-10-27
 * @Author zqx
 */
@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 第一：获取表单中输入的数据
        String username = req.getParameter("uname");
        String password = req.getParameter("upass");

        // 第二：加工处理 - 通过 DAO 对象，查询数据
        UserInfoDao userInfoDao = new UserInfoDaoImpl();
        UserInfo user = userInfoDao.selectUserInfoByName(username);

        if(user==null) {
            // 第三：把数据设置在作用域中
            req.setAttribute("error","查无此人，请注册");
            req.getRequestDispatcher("error.jsp").forward(req,resp);
            return ;
        }

        if(!user.getPassword().equals(password)) {
            req.setAttribute("error","错误的帐号或密码");
            req.getRequestDispatcher("error.jsp").forward(req,resp);
            return ;
        }


        // 第四：页面跳转
        req.getSession().setAttribute("user",user);
        resp.sendRedirect("welcome.jsp");

    }
}
