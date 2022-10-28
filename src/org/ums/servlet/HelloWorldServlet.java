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
        // 获取客户端浏览器中的数据
        String name = req.getParameter("name");
        String age = req.getParameter("age");

        PrintWriter out = resp.getWriter();

        // 思考：响应简单字符串，没有问题，如下所示
        // 如果我要响应多个数据（对象）呢？
        // 如果我要响应多个对象（List）呢？
        // 解决：使用 JSON 格式的字符串 - 把对象或List集合 转换为 JSON 字符串
        out.print("Hello," + name.toUpperCase());
        System.out.println(age);

        out.flush();
        out.close();

    }
}
