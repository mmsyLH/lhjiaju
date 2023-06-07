package xyz.lhweb.furns.factory;

import xyz.lhweb.furns.dao.*;
import xyz.lhweb.furns.dao.impl.*;

/**
 * DAO工厂
 * 单例模式
 * 确保系统中只有一个DaoFactory实例
 * 确保多个线程访问DaoFactory时，不会创建多个实例
 * 线程安全
 * 饿汉式单例模式
 * 在类加载时就创建了DaoFactory实例
 * 当系统第一次调用getInstance方法时，才会返回DaoFactory实例
 * @author 罗汉
 * @date 2023/03/27
 */
public class DaoFactory {
    private static final DaoFactory INSTANCE = new DaoFactory();

    /**
     * 私有构造方法
     */
    private DaoFactory() {}

    /**
     * 得到DaoFactory实例
     * @return DaoFactory实例
     */
    public static DaoFactory getInstance() {
        return INSTANCE;
    }

    /**
     * 得到产品DAO
     * @return {@link FurnDao}
     */
    public FurnDao getFurnDao() {
        return new FurnDaoImpl();
    }

    /**
     * 得到用户DAO
     * @return {@link MemberDAO}
     */
    public MemberDAO getMemberDAO() {
        return new MemberDAOImpl();
    }

    /**
     * 得到订单项DAO
     * @return {@link OrderItemDao}
     */
    public OrderItemDao getOrderItemDao() {
        return new OrderItemImpl();
    }

    /**
     * 得到订单DAO
     * @return {@link OrderDAo}
     */
    public OrderDAo getOrderDAo() {
        return new OrderDaoImpl();
    }

    /**
     * 得到UserDAO
     * @return {@link OrderDAo}
     */
    public UserDao getUserDAo() {
        return new UserDAOImpl();
    }

    public AdminDAO getAdminDAO() {
        return new AdminDAOImpl();
    }
}
