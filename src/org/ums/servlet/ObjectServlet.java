package org.ums.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 处理 Ajax 异步请求，向客户端响应一个对象 - JSON字符串
 *
 * @Date 2022-10-27
 * @Author zqx
 */
@WebServlet("/obj.do")
public class ObjectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();

        // 响应数据转换为JSON字符串非常麻烦 - GSON
        String json = "{'name':'zs','age':18}" ;

        out.print(json);

        out.flush();
        out.close();

    }
}
