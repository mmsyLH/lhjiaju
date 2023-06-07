package xyz.lhweb.furns.service.impl;

import xyz.lhweb.furns.bean.User;
import xyz.lhweb.furns.dao.BasicDAO;
import xyz.lhweb.furns.dao.UserDao;
import xyz.lhweb.furns.dao.impl.UserDAOImpl;
import xyz.lhweb.furns.factory.DaoFactory;
import xyz.lhweb.furns.service.UserService;

/**
 * 用户服务实现
 *
 * @author 罗汉
 * @date 2023/05/30
 */
public class UserServiceImpl extends BasicDAO<User> implements UserService {
    private UserDao userDAO;

    public UserServiceImpl() {
        userDAO=DaoFactory.getInstance().getUserDAo();
    }

    /**
     * 注册用户
     *
     * @param user 用户
     * @return boolean
     */
    @Override
    public boolean registerUser(User user) {
        return userDAO.saveUser(user)==1;
    }

    /**
     * 判断用户名存在
     *
     * @param username 用户名
     * @return boolean
     */
    @Override
    public boolean isExistsUsername(String username) {
        return userDAO.queryUserByUsername(username) != null;
    }

    /**
     * 登录
     *
     * @param user 用户
     * @return {@link User}
     */
    @Override
    public User login(User user) {
        return userDAO.queryUserByUsernameAndPassword(user.getUsername(), user.getPassword());
    }
}
