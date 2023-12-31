package org.ums.dao;

import org.ums.entity.Product;

import java.util.List;

/**
 * 商品 DAO 接口（标准、规范） - 服务业务（Servlet、业务对象）
 * <p>
 * 仓库管理员
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
     * 删除商品 - 单条数据删除
     *
     * @param pid
     * @return
     */
    int deleteProduct(String pid);

    /**
     * 删除商品 - 多条数据删除 - 支持事务操作
     * @param pids
     * @return
     */
    int deleteProduct(String[] pids);

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
    Product selectProductByProductId(String pid);

    /**
     * 查询所有商品
     *
     * @return
     */
    List<Product> selectProduct();


}
