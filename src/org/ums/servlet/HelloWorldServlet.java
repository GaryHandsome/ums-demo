package org.ums.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 处理 Ajax 异步请求，向客户端响应一个字符串 - Hello,World
 *
 * @Date 2022-10-27
 * @Author zqx
 */
@WebServlet("/hello.do")
public class HelloWorldServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();

        out.print("Hello,World");

        out.flush();
        out.close();

    }
}
