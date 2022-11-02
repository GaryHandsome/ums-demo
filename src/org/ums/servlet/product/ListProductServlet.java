package org.ums.servlet.product;

import com.google.gson.Gson;
import org.ums.dao.ProductDao;
import org.ums.dao.impl.ProductDaoImpl;
import org.ums.entity.Product;
import org.ums.servlet.BaseServlet;
import org.ums.vo.ResponseData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * 查询所有的商品
 *
 * @Date 2022-11-02
 * @Author zqx
 */
@WebServlet("/product_list.do")
public class ListProductServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 第一：获取客户端发送的数据（合法性的验证）

        // 第二：加工处理（业务处理） - 如果业务复杂，则抽象业务对象来处理
        ProductDao productDao = new ProductDaoImpl();
        List<Product> list = productDao.selectProduct();

        // 第三：把数据转为 JSON 字符串
        ResponseData responseData = success(list);
        String json = new Gson().toJson(responseData);

        // 第四：打印输出 - 响应客户端
        print(resp, json);
    }
}
