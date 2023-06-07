package xyz.lhweb.furns.dao.impl;

import xyz.lhweb.furns.bean.Admin;
import xyz.lhweb.furns.dao.AdminDAO;

import java.sql.SQLException;

/**
 * 管理daoimpl
 *
 * @author 罗汉
 * @date 2023/06/07
 */
public class AdminDAOImpl implements AdminDAO {
    /**
     * @param admin
     * @throws SQLException
     */
    @Override
    public void regist(Admin admin) throws SQLException {

    }

    /**
     * @param code
     * @return
     * @throws SQLException
     */
    @Override
    public Admin findByCode(String code) throws SQLException {
        return null;
    }

    /**
     * @param admin
     * @throws SQLException
     */
    @Override
    public void update(Admin admin) throws SQLException {

    }
}
