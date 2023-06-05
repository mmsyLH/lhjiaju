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
            String sql = "SELECT p.pid,p.pimage, p.pname, p.shop_price, oi.count, oi.subtotal\n" +
                    "FROM orders o\n" +
                    "INNER JOIN orderitem oi ON o.oid = oi.oid\n" +
                    "INNER JOIN product p ON oi.pid = p.pid\n" +
                    "WHERE o.oid = ? LIMIT ? ,?";
            pstat = conn.prepareStatement(sql);
            pstat.setString(1, oid);
            pstat.setInt(2, begin);
            pstat.setInt(3, pageSize);
            res = pstat.executeQuery();
            while (res.next()) {
                CartItem item = new CartItem();
                // item.setPimage((res.getString("pimage")));
                // item.setPid(Integer.parseInt(res.getString("pid")));
                // item.setPname(res.getString("pname"));
                // item.setShopPrice(res.getDouble("shop_price"));
                // item.setCount(res.getInt("count"));
                // item.setTotalPrice(res.getDouble("subtotal"));
                cartItem.add(item);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cartItem;
    }
}
