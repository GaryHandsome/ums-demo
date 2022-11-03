package org.ums.servlet.product;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.ums.dao.ProductDao;
import org.ums.dao.impl.ProductDaoImpl;
import org.ums.entity.Product;
import org.ums.servlet.BaseServlet;
import org.ums.util.DateUtil;
import org.ums.vo.ResponseData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * 添加商品
 *
 * @Date 2022-11-02
 * @Author zqx
 */
@WebServlet("/product_add.do")
public class AddProductServlet extends BaseServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 第一：获取客户端发送的数据（合法性的验证） - ?
        String productId = req.getParameter("productId");
        String productName = req.getParameter("productName");
        String productType = req.getParameter("productType");
        double productPrice = Double.parseDouble(req.getParameter("productPrice"));
        String year = req.getParameter("year");
        String month = req.getParameter("month");
        String day = req.getParameter("day");
        String strDate = year + "-" + month + "-" + day;
        Date productDate = DateUtil.parseDate(strDate);
        int productStatus = Integer.parseInt(req.getParameter("isDown"));



        Product product = new Product() ;
        product.setProductId(productId);
        product.setProductName(productName);
        product.setProductType(productType);
        product.setProductPrice(productPrice);
        product.setProductDate(productDate);
        product.setProductStatus(productStatus);

        // 第二：加工处理（业务处理） - 如果业务复杂，则抽象业务对象来处理
        ProductDao productDao = new ProductDaoImpl();
        int row = productDao.insertProduct(product);

        // 第三：把数据转为 JSON 字符串
        // 第四：打印输出 - 响应客户端
        ResponseData responseData = null ;
        if(row==1) {
            responseData = success(200,"添加成功",null);
            String json = new Gson().toJson(responseData);
            print(resp,json);
            return;
        }

        responseData = success(500, "添加失败", null);
        String json = new Gson().toJson(responseData);
        print(resp, json);
    }
}
