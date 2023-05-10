package xyz.lhweb.furns.dao.impl;

import xyz.lhweb.furns.bean.OrderItem;
import xyz.lhweb.furns.dao.BasicDAO;
import xyz.lhweb.furns.dao.OrderItemDao;

/**
 * 订单项实现
 *
 * @author 罗汉
 * @date 2023/04/08
 */
public class OrderItemImpl extends BasicDAO<OrderItem> implements OrderItemDao {
    /**
     * 保存订单
     * 保存到数据库
     *
     * @param orderItem 订单项
     * @return int
     */
    @Override
    public int saveOrder(OrderItem orderItem) {
        String sql="INSERT INTO `order_item`(`id`,`name`,`price`,`count`,`total_price`,`order_id`) " +
                "VALUES(?,?,?,?,?,?) ";
        return update(sql,orderItem.getId(),orderItem.getName(),orderItem.getPrice(),
                orderItem.getCount(),orderItem.getTotalPrice(),orderItem.getOrderId());
    }
}
