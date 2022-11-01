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
 * 同步请求实现用户注册功能
 *
 * @Date 2022-10-31
 * @Author zqx
 */
@WebServlet("/register.do")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        // 第一：获取客户端浏览器提交的数据（合法性的验证）
        String name = req.getParameter("uname");
        String pass = req.getParameter("upass");
        int age = Integer.parseInt(req.getParameter("age"));
        String sex = req.getParameter("sex");
        double weight = Double.parseDouble(req.getParameter("weight"));

        // 第二：加工处理
        UserInfo userInfo = new UserInfo() ;
        userInfo.setUsername(name);
        userInfo.setPassword(pass);
        userInfo.setUserAge(age);
        userInfo.setUserSex(sex);
        userInfo.setWeight(weight);

        UserInfoDao userInfoDao = new UserInfoDaoImpl();
        int r = userInfoDao.insertUserInfo(userInfo) ;

        // 第三：把数据设置在作用域对象中
        req.setAttribute("user",name);

        // 第四：页面跳转
        req.getRequestDispatcher("reg_suc.jsp").forward(req,resp);
    }
}
