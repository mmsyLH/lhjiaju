package xyz.lhweb.furns.test;

import xyz.lhweb.furns.bean.Member;
import xyz.lhweb.furns.dao.MemberDAO;
import xyz.lhweb.furns.dao.impl.MemberDAOImpl;
import xyz.lhweb.furns.service.MemberService;
import xyz.lhweb.furns.service.impl.MemberServiceImpl;

/**
 * 会员测试
 *
 * @author 罗汉
 * @date 2023/04/01
 */
public class MemberTest {
    public static void main(String[] args) {
        MemberDAO memberDAO = new MemberDAOImpl();
        MemberService memberService = new MemberServiceImpl();
        //-------------------------------------DAO---------------------------------------
        // ----------------------测试查询------------------
        // DaoQueryByName(memberDAO);
        //----------------------- Dao测试保存---------------------
        // daoSave(memberDAO);
        // ---------------------测试查询（name,pwd）--------------------
        // daoQueryBynameAndPassword(memberDAO);
        //----------------------service注册-----------------------
        // serviceRegister(memberService);
        //----------------------service登录-----------------------
        // login(memberService);
    }

    private static void login(MemberService memberService) {
        Member member = new Member(null, "admin", "admin", null);
        Member login = memberService.login(member);
        System.out.println(login);
    }

    private static void DaoQueryByName(MemberDAO memberDAO) {
        Member member = memberDAO.queryMemberByUsername("lh");
        if (member == null) {
            System.out.println("该用户名不存在");
        } else {
            System.out.println("该用户名存在" + member);
        }
    }

    private static void daoQueryBynameAndPassword(MemberDAO memberDAO) {
        Member member2 = memberDAO.queryMemberByUsernameAndPassword("lh", "123");
        if (member2 == null) {
            System.out.println("该用户名不存在");
        } else {
            System.out.println("该用户名存在" + member2);
        }
    }

    private static void daoSave(MemberDAO memberDAO) {
        Member member1 = new Member(null, "lh11", "lh11", "1072344372@qq.com");
        int i = memberDAO.saveMember(member1);
        if (i == 1) {
            System.out.println("保存成功" + member1);
        } else {
            System.out.println("保存失败");
        }
    }


    private static void serviceRegister(MemberService memberService) {
        Member member3 = new Member(null, "lh13", "lh13", "1072344372@qq.com");
        boolean b = memberService.registerMember(member3);
        if (b) {
            System.out.println("注册成功" + member3);
        } else {
            System.out.println("注册失败");
        }
    }
}
