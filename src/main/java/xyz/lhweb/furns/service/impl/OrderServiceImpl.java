package xyz.lhweb.furns.service.impl;

import xyz.lhweb.furns.bean.*;
import xyz.lhweb.furns.dao.BasicDAO;
import xyz.lhweb.furns.dao.FurnDao;
import xyz.lhweb.furns.dao.OrderDAo;
import xyz.lhweb.furns.dao.OrderItemDao;
import xyz.lhweb.furns.factory.DaoFactory;
import xyz.lhweb.furns.service.OrderService;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 订单服务实现
 *
 * @author 罗汉
 * @date 2023/04/08
 */
public class OrderServiceImpl extends BasicDAO<Order> implements OrderService {
    private OrderDAo orderDAo = DaoFactory.getOrderDAo();
    private OrderItemDao orderItemDAo = DaoFactory.getOrderItemDao();

    private FurnDao furnDao = DaoFactory.getFurnDao();

    /**
     * 保存订单
     * 1 生成订单
     * 2 订单是根据cart来生成的，cart在session中，通过web层，传入saveOrder
     * 3 订单是和一个会员关联的
     *
     * @param cart     车
     * @param memberId 会员id
     * @return {@link String} 订单编号
     */
    @Override
    public String saveOrder(Cart cart, int memberId) {
        // 这里的业务逻辑相对综合
        // 完成任务时将 cart购物车的数据->以order和 orderItem形式保存到db
        // 1. 通过cart对象, 构建对应的Order对象
        // 先生成一个UUID, 表示当前的订单号, 订单号要保证是唯一
        String orderId = System.currentTimeMillis() + "" + memberId;
        Order order = new Order(orderId, new Date(), cart.getCartTotalPrice(), 0, memberId);
        // 保存order到数据表.
        orderDAo.saveOrder(order);
        // 2.通过cart对象 ,遍历出CartItem, 构建OrderItem对象， 并保存到对应的表order_item
/*        HashMap<Integer, CartItem> items = cart.getItems();
        // HashMap<Integer, CartItem> items = cart.getItems();
        //// java基础遍历hashmap
        Set<Integer> keys = items.keySet();
        for (Integer id : keys) {
            CartItem cartItem = items.get(id);
            //    //通过cartItem对象构建了OrderItem
            OrderItem orderItem = new OrderItem(null, cartItem.getName(), cartItem.getPrice(), cartItem.getCount(), cartItem.getTotalPrice(), orderId);
            orderItemDAo.saveOrder(orderItem);
            // 更新一把furn表的sales销量, stock存量
            //(1) 获取到furn对象
            Furn furn = furnDao.queryFurnById(id);
            //(2) 更新一下这个furn的sales销量, stock存量
            furn.setSales(furn.getSales()+cartItem.getCount());
            furn.setStock(furn.getStock()-cartItem.getCount());
            //(3) 更新到数据表
            furnDao.updateFurn(furn);
        }*/
        //=======================通过entryset方式遍历取出cartItem
        for (Map.Entry<Integer,CartItem> entry: cart.getItems().entrySet()) {
            CartItem cartItem = entry.getValue();
            //    //通过cartItem对象构建了OrderItem
            OrderItem orderItem = new OrderItem(null, cartItem.getName(), cartItem.getPrice(), cartItem.getCount(), cartItem.getTotalPrice(), orderId);
            orderItemDAo.saveOrder(orderItem);
            // 更新一把furn表的sales销量, stock存量
            //(1) 获取到furn对象
            Furn furn = furnDao.queryFurnById(cartItem.getId());
            //(2) 更新一下这个furn的sales销量, stock存量
            furn.setSales(furn.getSales()+cartItem.getCount());
            furn.setStock(furn.getStock()-cartItem.getCount());
            //(3) 更新到数据表
            furnDao.updateFurn(furn);
        }
        // 清空购物车
        cart.clear();
        return orderId;
    }
}
