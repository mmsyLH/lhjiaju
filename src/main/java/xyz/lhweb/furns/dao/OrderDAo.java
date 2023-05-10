package xyz.lhweb.furns.dao;

import xyz.lhweb.furns.bean.Order;

/**
 * 订单DAO
 *
 * @author 罗汉
 * @date 2023/04/08
 */
public interface OrderDAo {
    /**
     * 保存到数据库
     *
     * @param order 订单
     * @return int
     */
    int saveOrder(Order order);
}
