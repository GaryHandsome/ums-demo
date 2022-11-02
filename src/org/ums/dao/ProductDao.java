package org.ums.dao;

import org.ums.entity.Product;

import java.util.List;

/**
 * 商品 DAO 接口
 *
 * @Date 2022-11-02
 * @Author zqx
 */
public interface ProductDao {
    /**
     * 添加商品
     *
     * @param product 商品实体
     * @return 受影响的行数
     */
    int insertProduct(Product product);

    /**
     * 删除商品
     *
     * @param pid
     * @return
     */
    int deleteProduct(String pid);

    /**
     * 修改商品
     *
     * @param product
     * @return
     */
    int updateProduct(Product product);

    /**
     * 根据商品编号查询商品
     *
     * @return
     */
    Product selectProductByProductId();

    /**
     * 查询所有商品
     *
     * @return
     */
    List<Product> selectProduct();


}
