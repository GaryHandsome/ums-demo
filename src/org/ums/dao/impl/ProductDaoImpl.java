package org.ums.dao.impl;

import org.ums.dao.ProductDao;
import org.ums.entity.Product;
import org.ums.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 商品 DAO 接口的实现
 *
 * @Date 2022-11-02
 * @Author zqx
 */
public class ProductDaoImpl implements ProductDao {
    @Override
    public int insertProduct(Product product) {
        int r = 0;

        // 第一：定义要操作数据库的SQL语句
        String sql = "insert into product(product_id,product_name,product_type,product_price,product_date,product_status) values (?,?,?,?,?,?)";

        Connection conn = null;
        PreparedStatement pstmt = null;


        try {
            // 第二：获取连接对象
            conn = DBUtil.getConnection();

            // 第三：预编译SQL语句，实例化语句对象
            pstmt = conn.prepareStatement(sql);

            // 第四：填充数据
            pstmt.setString(1, product.getProductId());
            pstmt.setString(2, product.getProductName());
            pstmt.setString(3, product.getProductType());
            pstmt.setDouble(4, product.getProductPrice());
            pstmt.setTimestamp(5, new Timestamp(product.getProductDate().getTime()));
            pstmt.setInt(6, product.getProductStatus());

            // 第五：执行SQL语句，并接收执行的结果
            r = pstmt.executeUpdate();

            // 第六：对象结果进行处理

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            // 第七：关闭相关的对象
            DBUtil.close(null, pstmt, conn);
        }

        return r;
    }

    @Override
    public int deleteProduct(String pid) {
        int r = 0;

        // 第一：定义要操作数据库的SQL语句
        String sql = "delete from product where product_id=?";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // 第二：获取连接对象
            conn = DBUtil.getConnection();

            // 第三：预编译SQL语句，实例化语句对象
            pstmt = conn.prepareStatement(sql);

            // 第四：填充数据
            pstmt.setString(1, pid);

            // 第五：执行SQL语句，并接收执行的结果
            r = pstmt.executeUpdate();

            // 第六：对象结果进行处理

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            // 第七：关闭相关的对象
            DBUtil.close(null, pstmt, conn);
        }

        return r;
    }

    @Override
    public int updateProduct(Product product) {
        int r = 0;

        // 第一：定义要操作数据库的SQL语句
        String sql = "update product set product_name=?,product_type=?,product_price=?,product_date=?,product_status=? where product_id=?";

        Connection conn = null;
        PreparedStatement pstmt = null;


        try {
            // 第二：获取连接对象
            conn = DBUtil.getConnection();

            // 第三：预编译SQL语句，实例化语句对象
            pstmt = conn.prepareStatement(sql);

            // 第四：填充数据
            pstmt.setString(1, product.getProductName());
            pstmt.setString(2, product.getProductType());
            pstmt.setDouble(3, product.getProductPrice());
            pstmt.setTimestamp(4, new Timestamp(product.getProductDate().getTime()));
            pstmt.setInt(5, product.getProductStatus());
            pstmt.setString(6, product.getProductId());

            // 第五：执行SQL语句，并接收执行的结果
            r = pstmt.executeUpdate();

            // 第六：对象结果进行处理

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            // 第七：关闭相关的对象
            DBUtil.close(null, pstmt, conn);
        }

        return r;
    }

    @Override
    public Product selectProductByProductId(String pid) {
        Product p = null;

        // 第一：定义要操作数据库的SQL语句
        String sql = "select product_id,product_name,product_type,product_price,product_count,product_image,product_date,product_desc,product_sale,product_status from product where product_id=?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rst = null;

        try {
            // 第二：获取连接对象
            conn = DBUtil.getConnection();

            // 第三：预编译SQL语句，实例化语句对象
            pstmt = conn.prepareStatement(sql);

            // 第四：填充数据
            pstmt.setString(1, pid);

            // 第五：执行SQL语句，并接收执行的结果
            rst = pstmt.executeQuery();

            // 第六：对象结果进行处理
            if (rst.next()) {
                // 1）如果有数据，则创建用户对象，否则返回user默认为null
                p = new Product();

                // 2）获取数据表中各列的数据
                // String productId = rst.getString(1);
                String productName = rst.getString(2);
                String productType = rst.getString(3);
                Double productPrice = rst.getDouble(4);
                int productCount = rst.getInt(5);
                String productImage = rst.getString(6);
                Timestamp productDate = rst.getTimestamp(7);
                String productDesc = rst.getString(8);
                int productSale = rst.getInt(9);
                int productStatus = rst.getInt(10);

                // 3）把各列数据，封装到用户对象中
                p.setProductId(pid);
                p.setProductName(productName);
                p.setProductType(productType);
                p.setProductPrice(productPrice);
                p.setProductCount(productCount);
                p.setProductImage(productImage);
                p.setProductDate(productDate);
                p.setProductDesc(productDesc);
                p.setProductSale(productSale);
                p.setProductStatus(productStatus);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            // 第七：关闭相关的对象
            DBUtil.close(rst, pstmt, conn);
        }

        return p;
    }

    @Override
    public List<Product> selectProduct() {
        List<Product> list = new ArrayList<>();

        // 第一：定义要操作数据库的SQL语句
        String sql = "select product_id,product_name,product_type,product_price,product_count,product_image,product_date,product_desc,product_sale,product_status from product where product_id=?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rst = null;

        try {
            // 第二：获取连接对象
            conn = DBUtil.getConnection();

            // 第三：预编译SQL语句，实例化语句对象
            pstmt = conn.prepareStatement(sql);

            // 第四：填充数据

            // 第五：执行SQL语句，并接收执行的结果
            rst = pstmt.executeQuery();

            // 第六：对象结果进行处理
            while (rst.next()) {
                // 1）如果有数据，则创建用户对象，否则返回user默认为null
                Product p = new Product();

                // 2）获取数据表中各列的数据
                String productId = rst.getString(1);
                String productName = rst.getString(2);
                String productType = rst.getString(3);
                Double productPrice = rst.getDouble(4);
                int productCount = rst.getInt(5);
                String productImage = rst.getString(6);
                Timestamp productDate = rst.getTimestamp(7);
                String productDesc = rst.getString(8);
                int productSale = rst.getInt(9);
                int productStatus = rst.getInt(10);

                // 3）把各列数据，封装到用户对象中
                p.setProductId(productId);
                p.setProductName(productName);
                p.setProductType(productType);
                p.setProductPrice(productPrice);
                p.setProductCount(productCount);
                p.setProductImage(productImage);
                p.setProductDate(productDate);
                p.setProductDesc(productDesc);
                p.setProductSale(productSale);
                p.setProductStatus(productStatus);

                list.add(p);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            // 第七：关闭相关的对象
            DBUtil.close(rst, pstmt, conn);
        }
        return list;
    }
}
