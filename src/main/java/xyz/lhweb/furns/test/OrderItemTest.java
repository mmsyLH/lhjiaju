package xyz.lhweb.furns.test;

import org.junit.Test;
import xyz.lhweb.furns.bean.CartItem;
import xyz.lhweb.furns.bean.OrderItem;
import xyz.lhweb.furns.dao.OrderItemDao;
import xyz.lhweb.furns.dao.impl.OrderItemImpl;

import java.math.BigDecimal;
import java.util.List;

public class OrderItemTest {
   private  static OrderItemDao dao = new OrderItemImpl();
    public static void main(String[] args) {
        dao = new OrderItemImpl();
        //-------------------------------------DAO---------------------------------------
        // ----------------------测试查询------------------
        // daoSave(dao);
        // daoQueryAll(dao);
        // daogetRow(dao);
        // daogetPageItems(dao);
        // daogetTotalRowByName(dao);
        // daogetPageItemsByname(dao);
        // ---------------------测试添加-------------------
        // daoAdd(dao);
        //------------------------  ------------------------
        // serviceQueryAll(service);
        // serviceAdd(service);
        // deleteByid(service);
        // queryFurnById(service);
        // updateFurn(service);
        // serviceGetPageItems(service);
        // serviceGetPageItemsByname(service);
        // ---------------------测试getOrderInfoById-------------------
        getOrderInfoById(dao);

    }

    private static void daoSave(OrderItemDao dao) {
        // Order order = new Order("001",new Date(),new BigDecimal(99),0,2);
        OrderItem orderItem = new OrderItem(null,"北欧小沙发",new BigDecimal(200),2,new BigDecimal(800),"sn00002");
        int i = dao.saveOrder(orderItem);
        if (i == 1) {
            System.out.println("添加成功："+orderItem);
        } else {
            System.out.println("添加失败");
        }
    }


    public static void getOrderInfoById(OrderItemDao dao) {
        System.out.println("hello world");
        List<CartItem> orderInfoById = dao.getOrderInfoById(1, 8, "16859240271071");
        for (CartItem cartItem : orderInfoById) {
            System.out.println(cartItem);
        }
        System.out.println(orderInfoById);
    }
}
