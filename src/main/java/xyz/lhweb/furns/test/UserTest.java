package xyz.lhweb.furns.test;

import org.junit.Test;
import xyz.lhweb.furns.bean.Member;
import xyz.lhweb.furns.bean.User;
import xyz.lhweb.furns.dao.MemberDAO;
import xyz.lhweb.furns.dao.UserDao;
import xyz.lhweb.furns.dao.impl.MemberDAOImpl;
import xyz.lhweb.furns.dao.impl.UserDAOImpl;
import xyz.lhweb.furns.service.MemberService;
import xyz.lhweb.furns.service.UserService;
import xyz.lhweb.furns.service.impl.MemberServiceImpl;
import xyz.lhweb.furns.service.impl.UserServiceImpl;

/**
 * 会员测试
 *
 * @author 罗汉
 * @date 2023/04/01
 */
public class UserTest {
   static UserDao userDao = new UserDAOImpl();
   static   UserService userService = new UserServiceImpl();

    public static void main(String[] args) {
        // boolean existsUsername = userService.isExistsUsername("123");
        // User user = userDao.queryUserByUsername("123");
        // System.out.println(user);


        User user = new User(null,"123","123","123","爱好阿瓦","男");
        int i = userDao.saveUser(user);
        System.out.println(i);

    }
    private static void login(MemberService memberService) {
        Member member = new Member(null, "admin", "admin", null);
        Member login = memberService.login(member);
        System.out.println(login);
    }
    // @Test
    // private void isName( ) {
    //     boolean existsUsername = userService.isExistsUsername("123");
    //     System.out.println(existsUsername);
    // }
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
