package org.ums.servlet.deferred;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Date 2022-11-07
 * @Author zqx
 */
@WebServlet("/deferred/api.do")
public class HelloApiServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();

        // System.out.println(100/0);

        out.print("api");

        out.flush();
        out.close();
    }
}
