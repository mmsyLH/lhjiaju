package xyz.lhweb.furns.dao;

import xyz.lhweb.furns.bean.Admin;
import xyz.lhweb.furns.bean.User;

import java.sql.SQLException;

/**
 * 管理DAO
 * TODO 管理员功能尚未实现,仅实现登录功能
 * @author 罗汉
 * @date 2023/06/07
 */
public interface AdminDAO {
    void regist(Admin admin) throws SQLException;

    Admin findByCode(String code) throws SQLException;

    void update(Admin admin) throws SQLException;
}
