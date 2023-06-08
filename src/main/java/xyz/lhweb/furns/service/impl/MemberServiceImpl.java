package xyz.lhweb.furns.service.impl;

import xyz.lhweb.furns.bean.Member;
import xyz.lhweb.furns.dao.BasicDAO;
import xyz.lhweb.furns.dao.MemberDAO;
import xyz.lhweb.furns.dao.impl.MemberDAOImpl;
import xyz.lhweb.furns.factory.DaoFactory;
import xyz.lhweb.furns.service.MemberService;
import xyz.lhweb.furns.utils.SendEmail;

/**
 * 会员服务实现
 *
 * @author 罗汉
 * @date 2023/04/01
 */
public class MemberServiceImpl extends BasicDAO<Member> implements MemberService {
    private MemberDAO memberDAO;

    public MemberServiceImpl() {
        memberDAO=DaoFactory.getInstance().getMemberDAO();
    }

    /**
     * 注册会员
     *
     * @param member 会员
     * @return boolean
     */
    @Override
    public boolean registerMember(Member member) {
        int i = memberDAO.saveMember(member);
        try {
            SendEmail.sendMail(member.getEmail(), member.getCode());
        } catch (Exception e) {
            System.out.println("发送邮件异常:"+getClass().getName());
            throw new RuntimeException(e);
        }
        return i==1;
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

    /**
     * @param username
     * @return
     */
    @Override
    public Member queryMemberByUsername(String username) {
        return memberDAO.queryMemberByUsername(username);
    }

    /**
     * @param code
     * @return
     */
    @Override
    public Member findByCode(String code) {
        return memberDAO.findByCode(code);
    }

    /**
     * 更新会员
     *
     * @param member 会员
     * @return {@link Boolean}
     */
    @Override
    public Boolean updateMember(Member member) {
        return memberDAO.updateMember(member)==1;
    }
}
