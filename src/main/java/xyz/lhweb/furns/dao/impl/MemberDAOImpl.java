package xyz.lhweb.furns.dao.impl;


import xyz.lhweb.furns.bean.Member;
import xyz.lhweb.furns.dao.BasicDAO;
import xyz.lhweb.furns.dao.MemberDAO;

/**
 * 会员daoimpl
 *
 * @author 罗汉
 * @date 2023/04/01
 */
public class MemberDAOImpl extends BasicDAO<Member> implements MemberDAO {


    /**
     * 查询会员通过用户名
     *
     * @param username 用户名
     * @return {@link Member}
     */
    @Override
    public Member queryMemberByUsername(String username) {
        String sql = "SELECT `id`,`username`,`password`,`email` FROM `member` " +
                " WHERE `username`=?";
        return querySingle(sql, Member.class, username);
    }

    /**
     * 保存会员
     *
     * @param member 会员
     * @return int 返回-1代表失败
     */
    @Override
    public int saveMember(Member member) {
        String sql = " INSERT INTO `member`(`username`,`password`,`email`) \n" +
                " VALUES(?,MD5(?), ?);";
        return update(sql, member.getUsername(),
                member.getPassword(), member.getEmail());
    }

    /**
     * 查询会员通过用户名和密码
     *
     * @param username 用户名
     * @param password 密码
     * @return {@link Member}
     */
    @Override
    public Member queryMemberByUsernameAndPassword(String username, String password) {
        String sql = "SELECT `id`,`username`,`password`,`email` FROM `member` " +
                " WHERE `username`=? and `password`=md5(?)";
        return querySingle(sql, Member.class, username, password);
    }
}
