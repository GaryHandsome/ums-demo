package org.ums.servlet.product;

import com.google.gson.Gson;
import org.ums.dao.ProductDao;
import org.ums.dao.impl.ProductDaoImpl;
import org.ums.servlet.BaseServlet;
import org.ums.vo.ResponseData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 批量删除商品 - 事务 - JDBC事务操作
 *  事务，要做的某件事情（业务），这个业务背后有多个操作，如银行转帐(转入、转出)
 *
 *
 *
 * @Date 2022-11-02
 * @Author zqx
 */
@WebServlet("/product_deletes.do")
public class DeletesProductServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 第一：获取客户端发送的数据（合法性的验证） - pids = [102,103]
        String[] pids = req.getParameterValues("productIds");

        // 第二：加工处理（业务处理） - 如果业务复杂，则抽象业务对象来处理
        int count =  new ProductDaoImpl().deleteProduct(pids);

        // 第三：把数据转为 JSON 字符串
        // 第四：打印输出 - 响应客户端
        ResponseData responseData = null;
        if (count == pids.length) {
            responseData = success(200, "删除成功", null);
            String json = new Gson().toJson(responseData);
            print(resp, json);
            return;
        }

        responseData = success(500, "删除失败", null);
        String json = new Gson().toJson(responseData);
        print(resp, json);
    }
}
