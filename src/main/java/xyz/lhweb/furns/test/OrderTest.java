package xyz.lhweb.furns.test;

import xyz.lhweb.furns.bean.Cart;
import xyz.lhweb.furns.bean.CartItem;
import xyz.lhweb.furns.bean.Order;
import xyz.lhweb.furns.dao.OrderDAo;
import xyz.lhweb.furns.dao.impl.OrderDaoImpl;
import xyz.lhweb.furns.service.OrderService;
import xyz.lhweb.furns.service.impl.OrderServiceImpl;

import java.math.BigDecimal;
import java.util.Date;

public class OrderTest {
    public static void main(String[] args) {
        OrderDAo dao = new OrderDaoImpl();
        OrderService service = new OrderServiceImpl();
        //-------------------------------------DAO---------------------------------------
        // ----------------------测试查询------------------
        // daoSave(dao);

        // ---------------------测试添加-------------------
        // daoAdd(dao);
        //------------------------  ------------------------
        serviceSave(service);

    }

    private static void serviceSave(OrderService service) {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1, "北欧风格小桌子", new BigDecimal(200), 3, new BigDecimal(600)));

        // String orderId = service.saveOrder(cart, 1);
        // System.out.println("odrderId:"+orderId);
    }

    private static void daoSave(OrderDAo dao) {
        Order order = new Order("001",new Date(),new BigDecimal(99),0,2);
        int i = dao.saveOrder(order);
        if (i == 1) {
            System.out.println("添加成功："+order);
        } else {
            System.out.println("添加失败");
        }
    }
}
