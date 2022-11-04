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
    public int deleteProduct(String[] pids) {
        // 统计成功执行操作的数量
        int count = 0;

        // 第一：定义要操作数据库的SQL语句
        String sql = "delete from product where product_id=?";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // 第二：获取连接对象
            conn = DBUtil.getConnection();

            // 第四：设置事务操作为手动提交，默认是自动提交
            conn.setAutoCommit(false);

            // 第五：预编译SQL语句，实例化语句对象
            pstmt = conn.prepareStatement(sql);

            // 第六：填充数据 -- 循环填充并执行
            for (String pid : pids) {
                // 1.填充数据
                pstmt.setString(1, pid);

                // 2.行SQL语句，并接收执行的结果
                int r = pstmt.executeUpdate();

                // 3.失败执行则回滚事务
                if(r==0) {
                   conn.rollback();
                   return 0;
                }
                // 4.统计成功操作的数量
                count ++ ;
            }

            // 第七：判断全部操作是否全都成功，成功才提交事务
            if(count == pids.length) {
                // 提交事务 - 数据在数据库中真正的更新
                conn.commit();
            }

            // 第八：对象结果进行处理

        } catch (Exception e) {
            try {
                // 如果程序在执行过程中发生异常，则事务回滚
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException("事务回滚失败",ex);
            }
            throw new RuntimeException("批量删除失败",e);
        } finally {
            // 第九：关闭相关的对象
            DBUtil.close(null, pstmt, conn);
        }

        return count;
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
        String sql = "select product_id,product_name,product_type,product_price,product_count,product_image,product_date,product_desc,product_sale,product_status from product";

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
