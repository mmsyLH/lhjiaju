package xyz.lhweb.furns.factory;

import xyz.lhweb.furns.dao.*;
import xyz.lhweb.furns.dao.impl.*;


/**
 * DAO工厂
 *
 * @author 罗汉
 * @date 2023/03/27
 */
public class DaoFactory {


    /**
     * 得到产品DAO
     *
     * @return {@link FurnDao}
     */
    public static FurnDao getFurnDao() {
        return new FurnDaoImpl();
    }

    /**
     * 得到用户DAO
     *
     * @return {@link MemberDAO}
     */
    public static MemberDAO getMemberDAO() {
        return new MemberDAOImpl();
    }

    /**
     * 得到订单项DAO
     *
     * @return {@link OrderItemDao}
     */
    public static OrderItemDao getOrderItemDao() {
        return new OrderItemImpl();
    }

    /**
     * 得到订单DAO
     *
     * @return {@link OrderDAo}
     */
    public static OrderDAo getOrderDAo() {
        return new OrderDaoImpl();
    }
    /**
     * 得到UserDAO
     *
     * @return {@link OrderDAo}
     */
    public static UserDao getUserDAo() {
        return new UserDAOImpl();
    }
}
