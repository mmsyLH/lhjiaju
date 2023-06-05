package xyz.lhweb.furns.dao;

import xyz.lhweb.furns.bean.Member;
import xyz.lhweb.furns.bean.User;

/**
 * 用户DAO
 *
 * @author 罗汉
 * @date 2023/05/30
 */
public interface UserDao {

    /**
     * 查询会员通过用户名
     *
     * @param username 用户名
     * @return {@link User}
     */
    public User queryUserByUsername(String username);

    /**
     * 保存会员
     *
     * @param user 用户
     * @return int
     */
    public int saveUser(User user);

    /**
     * 查询用户/管理员通过用户名和密码
     *
     * @param username 用户名
     * @param password 密码
     * @return 返回的对象，如果不存在，返回null
     */
    public User queryUserByUsernameAndPassword
    (String username, String password);
}
