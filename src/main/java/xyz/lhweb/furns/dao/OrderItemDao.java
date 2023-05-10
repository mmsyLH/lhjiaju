package xyz.lhweb.furns.dao;

import xyz.lhweb.furns.bean.Order;
import xyz.lhweb.furns.bean.OrderItem;

/**
 * 订单项DAO
 *
 * @author 罗汉
 * @date 2023/04/08
 */
public interface OrderItemDao {
    /**
     * 保存订单
     * 保存到数据库
     *
     * @param orderItem 订单项
     * @return int
     */
    int saveOrder(OrderItem orderItem);
}
