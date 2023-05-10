package xyz.lhweb.furns.dao.impl;

import xyz.lhweb.furns.bean.Order;
import xyz.lhweb.furns.dao.BasicDAO;
import xyz.lhweb.furns.dao.OrderDAo;

/**
 * 订单DAO实现
 *
 * @author 罗汉
 * @date 2023/04/14
 */
public class OrderDaoImpl extends BasicDAO<Order> implements OrderDAo {
    /**
     * 保存到数据库
     *
     * @param order 订单
     * @return int
     */
    @Override
    public int saveOrder(Order order) {
        String sql="INSERT INTO `order`(`id`,`create_time`,`price`,`status`,`member_id`) " +
                "VALUES(?,?,?,?,?)";
        return update(sql,order.getId(),order.getCreateTime(),order.getPrice(),order.getStatus(),order.getMemberId());
    }
}
