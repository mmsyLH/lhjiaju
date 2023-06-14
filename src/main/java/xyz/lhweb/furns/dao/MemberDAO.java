package xyz.lhweb.furns.dao;


import xyz.lhweb.furns.bean.Furn;
import xyz.lhweb.furns.bean.Member;

import java.util.List;

/**
 * 会员DAO
 *
 * @author 罗汉
 * @date 2023/04/01
 */
public interface MemberDAO {


    /**
     * 查询会员通过用户名
     *
     * @param username 用户名
     * @return {@link Member}
     */
     Member queryMemberByUsername(String username);

    /**
     * 保存会员
     *
     * @param member 会员
     * @return int
     */
     int saveMember(Member member);

    /**
     * 查询会员通过用户名和密码
     *
     * @param username 用户名
     * @param password 密码
     * @return 返回的对象，如果不存在，返回null
     */
     Member queryMemberByUsernameAndPassword
            (String username, String password);


    /**
     * 根据Code找到用户
     *
     * @param code 代码
     * @return {@link Member}
     */
     Member findByCode(String code);

     int updateMember(Member member);

    /**
     * 得到总一行名字
     *
     * @param name 名字
     * @return int
     */
    int getTotalRowByName(String name);

    /**
     * 获得页面项别名
     *
     * @param begin    开始
     * @param pageSize 页面大小
     * @param name     名字
     * @return {@link List}<{@link Furn}>
     */
    List<Member> getPageItemsByname(int begin, int pageSize, String name);

    int delById(int id);

    Member queryMemberById(int id);
}
