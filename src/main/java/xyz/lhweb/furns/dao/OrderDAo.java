package xyz.lhweb.furns.dao;

import xyz.lhweb.furns.bean.Order;

import java.util.List;

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
    /**
     * 通过oid查询订单
     *
     * @param oid oid
     * @return {@link Order}
     */
    Order queryOrderByOid(String oid);
    /**
     * 通过uid查询记录条数
     *
     * @param uid uid
     * @return int
     */
    int getTotalRowByUid(Integer uid);
    int getTotalRowByOid(String oid);
    /**
     * 通过uid显示订单
     *
     * @param memberId uid
     * @return {@link List}<{@link Order}>
     */
    List<Order> getPageItemsByUid(int begin, int pageSize,Integer memberId);

    /**
     * 更新订单
     *
     * @param order 订单
     * @return int
     */
    int updateOrder(Order order) ;

    /**
     * 被oid页面项目
     *
     * @param begin    开始
     * @param pageSize 页面大小
     * @param oid      oid
     * @return {@link List}<{@link Order}>
     */
    List<Order> getPageItemsByOid(int begin, int pageSize, String oid);

    /**
     * 删除订单id
     *
     * @param id id
     * @return int
     */
    int deleteOrderById(String id);
}

