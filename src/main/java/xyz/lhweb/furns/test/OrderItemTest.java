package xyz.lhweb.furns.test;

import xyz.lhweb.furns.bean.OrderItem;
import xyz.lhweb.furns.dao.OrderItemDao;
import xyz.lhweb.furns.dao.impl.OrderItemImpl;

import java.math.BigDecimal;
public class OrderItemTest {
    public static void main(String[] args) {
        OrderItemDao dao = new OrderItemImpl();
        //-------------------------------------DAO---------------------------------------
        // ----------------------测试查询------------------
        daoSave(dao);
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
}
