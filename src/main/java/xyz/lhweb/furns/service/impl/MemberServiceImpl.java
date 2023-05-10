package xyz.lhweb.furns.service.impl;

import xyz.lhweb.furns.bean.Member;
import xyz.lhweb.furns.dao.BasicDAO;
import xyz.lhweb.furns.dao.MemberDAO;
import xyz.lhweb.furns.dao.impl.MemberDAOImpl;
import xyz.lhweb.furns.factory.DaoFactory;
import xyz.lhweb.furns.service.MemberService;

/**
 * 会员服务实现
 *
 * @author 罗汉
 * @date 2023/04/01
 */
public class MemberServiceImpl extends BasicDAO<Member> implements MemberService {
    private MemberDAO memberDAO= DaoFactory.getMemberDAO();
    /**
     * 注册会员
     *
     * @param member 会员
     * @return boolean
     */
    @Override
    public boolean registerMember(Member member) {
        return memberDAO.saveMember(member)==1;
    }

    /**
     * 判断用户名存在
     *
     * @param username 用户名
     * @return boolean
     */
    @Override
    public boolean isExistsUsername(String username) {
        //ctrl + b   =》编译类的方法
        //ctrl + alt +b =》 的方法
        return memberDAO.queryMemberByUsername(username) != null;
    }

    /**
     * 登录
     *
     * @param member 会员
     * @return {@link Member}
     */
    @Override
    public Member login(Member member) {
        return memberDAO.queryMemberByUsernameAndPassword(member.getUsername(), member.getPassword());
    }
}
