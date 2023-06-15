package xyz.lhweb.furns.dao.impl;

import xyz.lhweb.furns.bean.Order;
import xyz.lhweb.furns.dao.BasicDAO;
import xyz.lhweb.furns.dao.OrderDAo;
import xyz.lhweb.furns.utils.JDBCUtilsByDruid;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
        String sql="INSERT INTO `order`(`id`,`create_time`,`price`,`status`,`member_id`,`address`) " +
                "VALUES(?,?,?,?,?,?)";
        return update(sql,order.getId(),order.getCreateTime(),order.getPrice(),order.getStatus(),order.getMemberId(),order.getAddress());
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

    /**
     * 通过uid查询记录条数
     *
     * @param member_id uid
     * @return int
     */
    @Override
    public int getTotalRowByUid(Integer member_id) {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM `order` WHERE `member_id` =?";
        try {
            conn = JDBCUtilsByDruid.getConnection();
            pstat = conn.prepareStatement(sql);
            pstat.setInt(1, member_id);
            ResultSet rs = pstat.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
                // System.out.println("Table has " + count + " rows.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    /**
     * @param oid
     * @return
     */
    @Override
    public int getTotalRowByOid(String oid) {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM `order` WHERE `id` LIKE CONCAT('%', ?, '%')";
        try {
            conn = JDBCUtilsByDruid.getConnection();
            pstat = conn.prepareStatement(sql);
            pstat.setString(1, oid);
            ResultSet rs = pstat.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
                // System.out.println("Table has " + count + " rows.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    /**
     * 通过uid显示订单
     *
     * @param begin
     * @param pageSize
     * @param memberId      memberId
     * @return {@link List}<{@link Order}>
     */
    @Override
    public List<Order> getPageItemsByUid(int begin, int pageSize, Integer memberId) {
        List<Order> ordersList = new ArrayList<>();
        try {
            conn = JDBCUtilsByDruid.getConnection();
            String sql = "SELECT * from `order` WHERE `member_id`=?   limit ?,?";
            pstat = conn.prepareStatement(sql);
            pstat.setInt(1, memberId);
            pstat.setInt(2, begin);
            pstat.setInt(3, pageSize);
            res = pstat.executeQuery();
            while (res.next()) {
                Order orders = new Order();
                toBean(orders);
                ordersList.add(orders);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ordersList;
    }

    /**
     * 被oid页面项目
     *
     * @param begin    开始
     * @param pageSize 页面大小
     * @param oid      oid
     * @return {@link List}<{@link Order}>
     */
    @Override
    public List<Order> getPageItemsByOid(int begin, int pageSize, String oid) {
        List<Order> ordersList = new ArrayList<>();
        try {
            conn = JDBCUtilsByDruid.getConnection();
            String sql = "SELECT * FROM `order` WHERE `id` LIKE CONCAT('%', ?, '%') LIMIT ?, ?";
            pstat = conn.prepareStatement(sql);
            pstat.setString(1, oid);
            pstat.setInt(2, begin);
            pstat.setInt(3, pageSize);
            res = pstat.executeQuery();
            while (res.next()) {
                Order orders = new Order();
                toBean(orders);
                ordersList.add(orders);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ordersList;
    }

    /**
     * 删除订单id
     *
     * @param id id
     * @return int
     */
    @Override
    public int deleteOrderById(String id) {
        String sql = "delete from `order` where id=?";
        return update(sql, id);
    }

    /**
     * 更新订单
     *
     * @param order 订单
     * @return int
     */
    @Override
    public int updateOrder(Order order) {
        String sql = "update `order` set `create_time`=?, `price`=?, `status`=?, `member_id`=? where `id`=?";
        return update(sql, order.getCreateTime(), order.getPrice(), order.getStatus(),
                order.getMemberId(), order.getId());
    }

    private void toBean(Order order) throws SQLException {
        order.setId(res.getString("id"));
        //getDate() 方法返回的是一个 java.sql.Date 类型的数据，而日期时间类型的数据应该使用 getTimestamp() 方法来获取。
        // Date date=new Date(res.getTimestamp("ordertime").getTime());
        order.setCreateTime(res.getTimestamp("create_time"));
        order.setPrice(res.getBigDecimal("price"));
        order.setStatus(res.getInt("status"));
        order.setMemberId(res.getInt("member_id"));
        order.setAddress(res.getString("address"));
    }
}
