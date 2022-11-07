package org.ums.servlet;

import com.google.gson.Gson;
import org.ums.entity.Stu;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * $.parseJSON的使用
 *
 * @Date 2022-10-27
 * @Author zqx
 */
@WebServlet("/parseJson.do")
public class ParseJsonTestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();

        Stu stu = new Stu("zhangsan", 28);

        // GSON：把对象转换为 JSON 字符串
        String json = new Gson().toJson(stu);

        out.print(json);
        out.flush();
        out.close();

    }
}
