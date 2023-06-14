package xyz.lhweb.furns.service.impl;

import xyz.lhweb.furns.bean.*;
import xyz.lhweb.furns.dao.*;
import xyz.lhweb.furns.dao.impl.MemberDAOImpl;
import xyz.lhweb.furns.factory.DaoFactory;
import xyz.lhweb.furns.service.MemberService;
import xyz.lhweb.furns.service.OrderService;

import java.util.*;

/**
 * 订单服务实现
 *
 * @author 罗汉
 * @date 2023/04/08
 */
public class OrderServiceImpl extends BasicDAO<Order> implements OrderService {
    private OrderDAo orderDAo;
    private OrderItemDao orderItemDAo;
    private MemberDAO memberDAO;
    private FurnDao furnDao;

    public OrderServiceImpl() {
        orderDAo = DaoFactory.getInstance().getOrderDAo();
        orderItemDAo = DaoFactory.getInstance().getOrderItemDao();
        memberDAO = DaoFactory.getInstance().getMemberDAO();
        furnDao = DaoFactory.getInstance().getFurnDao();
    }

    /**
     * 保存订单
     * 1 生成订单
     * 2 订单是根据cart来生成的，cart在session中，通过web层，传入saveOrder
     * 3 订单是和一个会员关联的
     *
     * @param cart       车
     * @param memberName 会员名字
     * @return {@link String}
     */
    @Override
    public String saveOrder(Cart cart, String memberName) {
        // 这里的业务逻辑相对综合
        // 完成任务时将 cart购物车的数据->以order和 orderItem形式保存到db
        // 1. 通过cart对象, 构建对应的Order对象
        // 先生成一个UUID, 表示当前的订单号, 订单号要保证是唯一
        String orderId = System.currentTimeMillis() + "" + memberName;
        Member member = memberDAO.queryMemberByUsername(memberName);
        Order order = new Order(orderId, new Date(), cart.getCartTotalPrice(), 0, member.getId(),"泉州信息工程学院");
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

    /**
     * 通过oid查询订单
     *
     * @param oid oid
     * @return {@link Order}
     */
    @Override
    public Order queryOrderByOid(String oid) {
        return orderDAo.queryOrderByOid(oid);
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
        return orderItemDAo.getOrderInfoById(begin, pageSize, oid);
    }

    /**
     * @param pageNo
     * @param pageSize
     * @param memberId
     * @return
     */
    @Override
    public Page<Order> pageByUid(int pageNo, int pageSize, Integer memberId) {
        // //先创建一个page对象，然后根据实际情况填充属性
        Page<Order> page = new Page<>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        int totalRow = orderDAo.getTotalRowByUid(memberId);
        // System.out.println("OrderServiceImpl_totalRow"+totalRow);
        page.setTotalRow(totalRow);
        //比如 6 2  =》  6 / 2 = 3
        //比如 5 2  =》  5 / 2 = 2
        int pageTotalCount=totalRow/pageSize;
        if (totalRow%pageSize>0){
            pageTotalCount+=1;
        }
        page.setPageTotalCount(pageTotalCount);
        // private List<T> items;
        //验证: pageNo = 1 pageSize = 3 => begin =0
        //验证: pageNo = 3 pageSize = 2 => begin =4
        //OK => 但是注意这里隐藏一个坑, 现在你看不到, 后面会暴露
        int begin=(pageNo-1)*pageSize;
        List<Order> pageItems = orderDAo.getPageItemsByUid(begin, pageSize,memberId);
        // System.out.println("OrderServiceImpl_pageItems"+pageItems);
        page.setItems(pageItems);
        return page;

    }
}
