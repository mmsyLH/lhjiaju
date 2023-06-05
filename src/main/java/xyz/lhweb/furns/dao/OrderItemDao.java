package xyz.lhweb.furns.dao;

import xyz.lhweb.furns.bean.CartItem;
import xyz.lhweb.furns.bean.Order;
import xyz.lhweb.furns.bean.OrderItem;

import java.util.List;

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

    /**
     * 通过id获取订单信息
     * 根据订单id查询订单详情
     *
     * @param begin    开始
     * @param pageSize 页面大小
     * @param oid      项id
     * @return {@link List}<{@link CartItem}>
     */
    List<CartItem> getOrderInfoById(int begin, int pageSize, String oid);
}
