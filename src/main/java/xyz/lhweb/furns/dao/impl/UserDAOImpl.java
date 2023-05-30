package xyz.lhweb.furns.dao.impl;

import xyz.lhweb.furns.bean.User;
import xyz.lhweb.furns.dao.BasicDAO;
import xyz.lhweb.furns.dao.UserDao;

/**
 * 用户daoimpl
 *
 * @author 罗汉
 * @date 2023/05/30
 */
public class UserDAOImpl extends BasicDAO<User> implements UserDao {

    /**
     * 查询用户通过用户名
     *
     * @param username 用户名
     * @return {@link User}
     */
    @Override
    public User queryUserByUsername(String username) {
        String sql = "SELECT `id`,`username`,`password`,`email`, `hobbys ` FROM `user` " +
                " WHERE `username`=?";
        return querySingle(sql, User.class, username);
    }

    /**
     * 保存用户
     *
     * @param user 用户
     * @return int 返回-1代表失败
     */
    @Override
    public int saveUser(User user) {
        String sql = " INSERT INTO `user`(`username`,`password`,`email`,`hobbys`) \n"
                + " VALUES(?,MD5(?),?);";
        return update(sql, user.getUsername(), user.getPassword(),
                user.getEmail(),user.getHobbys());
    }

    /**
     * 查询用户/管理员通过用户名和密码
     *
     * @param username 用户名
     * @param password 密码
     * @return 返回的对象，如果不存在，返回null
     */
    @Override
    public User queryUserByUsernameAndPassword(String username, String password) {
        String sql = "SELECT `id`,`username`,`password`,`email`,`hobbys` FROM `user` " +
                " WHERE `username`=? and `password`=md5(?)";
        return querySingle(sql, User.class, username, password);
    }
}