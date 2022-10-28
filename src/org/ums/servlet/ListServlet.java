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
 * 处理 Ajax 异步请求，向客户端响应一个List集合 - JSON字符串
 *
 * @Date 2022-10-27
 * @Author zqx
 */
@WebServlet("/list.do")
public class ListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();

        // 响应数据转换为JSON字符串非常麻烦 - GSON
        // String json = "[{'name':'zs','age':18},{'name':'ls','age':19},{'name':'wb','age':20}]" ;

        Stu stu1 = new Stu("zhangsan", 28);
        Stu stu2 = new Stu("wangwu", 38);
        Stu stu3 = new Stu("zhaoliu", 48);
        Stu stu4 = new Stu("tianqi", 58);

        List<Stu> list = new ArrayList<>() ;
        list.add(stu1) ;
        list.add(stu2) ;
        list.add(stu3) ;
        list.add(stu4) ;

        // GSON：把对象转换为 JSON 字符串
        String json = new Gson().toJson(list);

        out.print(json);

        out.flush();
        out.close();

    }
}
