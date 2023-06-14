package xyz.lhweb.furns.dao.impl;


import xyz.lhweb.furns.bean.Furn;
import xyz.lhweb.furns.bean.Member;
import xyz.lhweb.furns.dao.BasicDAO;
import xyz.lhweb.furns.dao.MemberDAO;

import java.util.List;

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
        String sql = "SELECT `id`,`username`,`password`,`email`,`state`,`code` FROM `member` " +
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
        String sql = " INSERT INTO `member`(`username`,`password`,`email`,`state`,`code`) \n" +
                " VALUES(?,MD5(?), ?,?,?);";
        return update(sql, member.getUsername(),
                member.getPassword(), member.getEmail(),member.getState(),member.getCode());
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
        String sql = "SELECT `id`,`username`,`password`,`email`,`state`,`code` FROM `member` " +
                " WHERE `username`=? and `password`=md5(?)";
        return querySingle(sql, Member.class, username, password);
    }

    /**
     * 根据Code找到用户
     *
     * @param code 代码
     * @return {@link Member}
     */
    @Override
    public Member findByCode(String code) {
        String sql = "select * from member where code = ?";
        return querySingle(sql, Member.class, code);
    }

    /**
     * 更新会员
     *
     * @param member 会员
     * @return int
     */
    @Override
    public int updateMember(Member member) {
        String sql = "UPDATE `member` SET `username` = ? , `password` = MD5(?), `email` = ? , "
                + "`code` = ? , `state` = ? where `id`=?";
        return update(sql,member.getUsername(),member.getPassword(),member.getEmail(),member.getCode(),member.getState(),member.getId());
    }

    /**
     * 得到总一行名字
     *
     * @param name 名字
     * @return int
     */
    @Override
    public int getTotalRowByName(String name) {
        String sql = "select count(*) from `member`";
        /**
         * (Integer) queryScalar(sql);=》cast异常
         */
        return ((Number) queryScalar(sql)).intValue();
    }

    /**
     * 获得页面项别名
     *
     * @param begin    开始
     * @param pageSize 页面大小
     * @param name     名字
     * @return {@link List}<{@link Furn}>
     */
    @Override
    public List<Member> getPageItemsByname(int begin, int pageSize, String name) {

        String sql = "SELECT * FROM `member` WHERE `username` like ? LIMIT ?,?";

        return queryMulti(sql, Member.class,"%" + name + "%", begin, pageSize);
    }

    /**
     * @return
     */
    @Override
    public int delById(int id) {
        String sql = "delete from `member` where id=?";
        return update(sql, id);
    }

    /**
     * 查询会员通过id
     *
     * @param id id
     * @return {@link Member}
     */
    @Override
    public Member queryMemberById(int id) {
        String sql = "SELECT * from member where id=?;";
        return querySingle(sql, Member.class, id);
    }

}
