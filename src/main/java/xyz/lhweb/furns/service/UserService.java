package xyz.lhweb.furns.service;

import xyz.lhweb.furns.bean.User;

/**
 * 用户服务
 *
 * @author 罗汉
 * @date 2023/05/30
 */
public interface UserService {

    /**
     * 注册用户
     *
     * @param user 用户
     * @return boolean
     */
    boolean registerUser(User user);

    /**
     * 判断用户名存在
     *
     * @param username 用户名
     * @return boolean
     */
    boolean isExistsUsername(String username);

    /**
     * 根据登录传入的用户信息，返回对应的在DB中的用户对象
     * @param user 是根据用户登录构建一个用户
     * @return 返回的是对应的DB中的用户对象，如果不存在, 返回 null
     */
    User login(User user);
}
