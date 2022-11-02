package org.ums.dao.impl;

import org.ums.dao.ProductDao;
import org.ums.entity.Product;

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
        return 0;
    }

    @Override
    public int deleteProduct(String pid) {
        return 0;
    }

    @Override
    public int updateProduct(Product product) {
        return 0;
    }

    @Override
    public Product selectProductByProductId() {
        return null;
    }

    @Override
    public List<Product> selectProduct() {
        return null;
    }
}
