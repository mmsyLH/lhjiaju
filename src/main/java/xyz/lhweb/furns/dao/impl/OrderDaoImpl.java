package xyz.lhweb.furns.dao.impl;

import xyz.lhweb.furns.bean.Order;
import xyz.lhweb.furns.dao.BasicDAO;
import xyz.lhweb.furns.dao.OrderDAo;
import xyz.lhweb.furns.utils.JDBCUtilsByDruid;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 订单DAO实现
 *
 * @author 罗汉
 * @date 2023/04/14
 */
public class OrderDaoImpl extends BasicDAO<Order> implements OrderDAo {
    private Connection conn = null;
    private PreparedStatement pstat = null;
    private ResultSet res = null;
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

    /**
     * 通过oid查询订单
     *
     * @param oid oid
     * @return {@link Order}
     */
    @Override
    public Order queryOrderByOid(String oid) {
        Order order = new Order();
        try {
            String sql = "SELECT * from `order` WHERE id = ?";
            conn = JDBCUtilsByDruid.getConnection();
            pstat = conn.prepareStatement(sql);
            pstat.setString(1, oid);
            res = pstat.executeQuery();
            while (res.next()) {
                toBean(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // try {
            //     JDBCUtils.close(conn, pstat, res);
            // } catch (SQLException e) {
            //     e.printStackTrace();
            // }
        }
        return order;
    }
    private void toBean(Order order) throws SQLException {
        order.setId(res.getString("id"));
        //getDate() 方法返回的是一个 java.sql.Date 类型的数据，而日期时间类型的数据应该使用 getTimestamp() 方法来获取。
        // Date date=new Date(res.getTimestamp("ordertime").getTime());
        order.setCreateTime(res.getTimestamp("create_time"));
        order.setPrice(res.getBigDecimal("price"));
        order.setStatus(res.getInt("status"));
        order.setMemberId(res.getInt("member_id"));
    }
}
