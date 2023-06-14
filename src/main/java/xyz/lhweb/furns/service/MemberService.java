package xyz.lhweb.furns.service;

import xyz.lhweb.furns.bean.Member;
import xyz.lhweb.furns.bean.Page;

/**
 * 会员服务
 *
 * @author 罗汉
 * @date 2023/04/01
 */
public interface MemberService {

    /**
     * 注册会员
     *
     * @param member 会员
     * @return boolean
     */
     boolean registerMember(Member member);

    /**
     * 判断用户名存在
     *
     * @param username 用户名
     * @return boolean
     */
     boolean isExistsUsername(String username);

    /**
     * 根据登录传入的member信息，返回对应的在DB中的member对象
     * @param member 是根据用户登录构建一个member
     * @return 返回的是对应的db中的member对象，如果不存在,返回null
     */
     Member login(Member member);

     Member queryMemberByUsername(String username);
    Member findByCode(String code);

    /**
     * 更新会员
     *
     * @param member 会员
     * @return {@link Boolean}
     */
    Boolean updateMember(Member member);

    Page<Member> pageByName(int pageNo, int pageSize, String name);

    Boolean delById(int id);

    Member queryMemberById(int id);
}
