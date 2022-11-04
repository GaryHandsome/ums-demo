package org.ums.test;

import org.ums.dao.impl.ProductDaoImpl;

/**
 * 测试批量删除商品
 *
 * @Date 2022-11-04
 * @Author zqx
 */
public class ProductTest {
    public static void main(String[] args) {
        String[] pids = {"103","104"} ;
        int count = new ProductDaoImpl().deleteProduct(pids);
        System.out.println(count);
    }
}
