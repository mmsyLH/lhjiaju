package xyz.lhweb.furns.dao.impl;

import xyz.lhweb.furns.bean.CartItem;
import xyz.lhweb.furns.bean.OrderItem;
import xyz.lhweb.furns.dao.BasicDAO;
import xyz.lhweb.furns.dao.OrderItemDao;
import xyz.lhweb.furns.utils.JDBCUtilsByDruid;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 订单项实现
 *
 * @author 罗汉
 * @date 2023/04/08
 */
public class OrderItemImpl extends BasicDAO<OrderItem> implements OrderItemDao {
    private Connection conn = null;
    private PreparedStatement pstat = null;
    private ResultSet res = null;
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

    /**
     * 通过id获取订单信息
     * 根据订单id查询订单详情
     *
     * @param begin    开始
     * @param pageSize 页面大小
     * @param oid      项id
     * @return {@link List}<{@link CartItem}>
     */
    @Override
    public List<CartItem> getOrderInfoById(int begin, int pageSize, String oid) {
        List<CartItem> cartItem = new ArrayList<>();
        try {
            conn = JDBCUtilsByDruid.getConnection();
            String sql = "SELECT f.id,f.img_path, f.name, f.price, oi.count, oi.total_price\n" +
                    "FROM `order` o\n" +
                    "INNER JOIN order_item oi ON o.id = oi.order_id\n" +
                    "INNER JOIN furn f ON oi.name = f.name\n" +
                    "WHERE o.id = ? LIMIT ? ,?";
            pstat = conn.prepareStatement(sql);
            pstat.setString(1, oid);
            pstat.setInt(2, begin);
            pstat.setInt(3, pageSize);
            res = pstat.executeQuery();
            while (res.next()) {
                CartItem item = new CartItem();
                item.setId(res.getInt("id"));
                item.setPimage((res.getString("img_path")));
                item.setName((res.getString("name")));
                item.setPrice(res.getBigDecimal("total_price"));
                item.setCount(res.getInt("count"));
                item.setTotalPrice(res.getBigDecimal("total_price"));
                cartItem.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cartItem;
    }

    /**
     * 删除订单id
     *
     * @param id id
     * @return int
     */
    @Override
    public int deleteOrderById(String id) {
        String sql = "delete from `order_item` where `order_id`=?";
        return update(sql, id);
    }
}
