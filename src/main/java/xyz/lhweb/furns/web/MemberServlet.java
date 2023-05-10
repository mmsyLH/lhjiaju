package xyz.lhweb.furns.web;

import com.google.gson.Gson;
import xyz.lhweb.furns.bean.Member;
import xyz.lhweb.furns.service.MemberService;
import xyz.lhweb.furns.service.impl.MemberServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * 会员servlet
 *
 * @author 罗汉
 * @date 2023/04/01
 */
@WebServlet("/memberServlet")
public class MemberServlet extends BasicServlet {
    private MemberService memberService = new MemberServiceImpl();

    // @Override
    // protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //     String action = request.getParameter("action");
    //     System.out.println(action);
    //     if ("login".equals(action)){
    //         login(request, response);
    //     } else if ("register".equals(action)) {
    //         register(request, response);
    //     }else {
    //         System.out.println("action参数错误");
    //     }
    // }

    /**
     * 登录
     *
     * @param request  请求
     * @param response 响应
     * @throws ServletException servlet异常
     * @throws IOException      ioexception
     */
    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // System.out.println("loginServlet被调用");
        String username = request.getParameter("username");
        String userpassword = request.getParameter("userpassword");
        // System.out.println("用户名："+username+"密码："+userpassword);
        Member member = memberService.login(new Member(null, username, userpassword, null));
        if (member == null) {
            // System.out.println("登录失败");
            request.setAttribute("username", username);
            request.setAttribute("url", request.getContextPath() + "/views/member/login.jsp");
            request.setAttribute("second", 3);
            // System.out.println("登录失败");
            request.setAttribute("infomation", "登录失败,用户名或者密码错误！即将返回登录页面");
            request.getRequestDispatcher("/views/member/tip.jsp").forward(request, response);
        } else {
            // System.out.println("登录成功"+member);
            // 将得到的member对象放入session
            request.getSession().setAttribute("member", member);
            if ("admin".equals(member.getUsername())) {
                request.setAttribute("username", member.getUsername());
                request.setAttribute("url", request.getContextPath() + "/views/manage/manage_menu.jsp");
                request.setAttribute("second", 10);
                // System.out.println("管理员登录成功");
                request.setAttribute("infomation", "登录成功,即将进入管理员后台");
                request.getRequestDispatcher("/views/member/tip.jsp").forward(request, response);
                return;
            }
            request.setAttribute("username", username);
            request.setAttribute("url", request.getContextPath() + "/index.jsp");
            request.setAttribute("second", 10);
            // System.out.println("普通会员登录成");
            request.setAttribute("infomation", "登录成功,即将进入主页");
            request.getRequestDispatcher("/views/member/tip.jsp").forward(request, response);
        }

    }

    /**
     * 注册
     *
     * @param request  请求
     * @param response 响应
     * @throws ServletException servlet异常
     * @throws IOException      ioexception
     */
    protected void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // System.out.println("registerServlet被调用");
        String username = request.getParameter("username");
        String userpassword = request.getParameter("userpassword");
        String email = request.getParameter("email");

        // 获取用户提交的验证码
        String code = request.getParameter("code");

        // 从session从获取生成的验证码
        String token = (String) request.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        System.out.println(token + "code:" + code);
        // 立即删除session中的验证码,防止重复提交，重复使用
        request.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
        // 如果token不为空，且和用户提交的保持一致就继续
        if (token != null && token.equalsIgnoreCase(code)) {// 忽略大小写
            // System.out.println(username+userpassword+email);
            // 判断用户名是否存在
            if (!memberService.isExistsUsername(username)) {
                Member member = new Member(null, username, userpassword, email);
                if (memberService.registerMember(member)) {
                    request.setAttribute("username", username);
                    request.setAttribute("url", request.getContextPath() + "/index.jsp");
                    request.setAttribute("second", 15);
                    request.setAttribute("infomation", "注册成功,即将返回首页");
                    request.getRequestDispatcher("/views/member/tip.jsp").forward(request, response);
                } else {
                    // System.out.println(" 注册失败..." + member);
                    // 把登录错误信息,放入到request域
                    request.setAttribute("infomation", "未知错误,注册失败");
                    request.setAttribute("username", username);
                    request.setAttribute("url", request.getContextPath() + "/views/member/login.jsp?active=register");
                    request.setAttribute("second", 3);
                    // 页面转发
                    // 带回一个信息 要显示到注册选项页
                    request.setAttribute("active", "register");
                    request.getRequestDispatcher("/views/member/tip.jsp").forward(request, response);
                }
            } else {
                // 把登录错误信息,放入到request域
                request.setAttribute("infomation", username + ":用户名已存在");
                request.setAttribute("username", username);
                request.setAttribute("url", request.getContextPath() + "/views/member/login.jsp?active=register");
                request.setAttribute("second", 3);
                // 页面转发
                // 带回一个信息 要显示到注册选项页
                request.setAttribute("active", "register");
                request.getRequestDispatcher("/views/member/tip.jsp").forward(request, response);
            }
        } else {
            // System.out.println("验证码不正确");
            request.setAttribute("msg", "验证码不正确");
            request.setAttribute("infomation", "验证码错误,注册失败");
            request.setAttribute("username", username);
            // 设置回显
            request.setAttribute("email", email);
            request.setAttribute("url", request.getContextPath() + "/views/member/login.jsp?active=register");
            request.setAttribute("second", 3);
            // 页面转发
            // 带回一个信息 要显示到注册选项页
            // request.setAttribute("active", "register");
            request.getRequestDispatcher("/views/member/tip.jsp").forward(request, response);
        }
    }

    /**
     * 注销
     *
     * @param request  请求
     * @param response 响应
     * @throws ServletException servlet异常
     * @throws IOException      ioexception
     */
    protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // System.out.println("logout");
        // 销毁当前用户的session
        // 重定向到网站首页-》 刷新首页
        HttpSession session = request.getSession();
        session.invalidate();
        // http://localhost:8080/lhjiaju
        response.sendRedirect(request.getContextPath());
    }


    /**
     * 验证用户名是否存在
     *
     * @param request  请求
     * @param response 响应
     * @throws ServletException servlet异常
     * @throws IOException      ioexception
     */
    protected void isExistsUsername(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1 获取用户名
        String username = request.getParameter("username");
        // 2 调用方法
        boolean existsUsername = memberService.isExistsUsername(username);
        // 3 返回json格式
        // 4 先使用最简单的拼接演示，等会改进成可扩展的
        // 4.1 String resJson = "{\"isExist\":" + existsUsername + "}";
        // 4.2 将返回的数据放入map再转为json
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("isExist",existsUsername);
        Gson gson = new Gson();
        String resJson = gson.toJson(resultMap);
        // 5 返回
        response.getWriter().write(resJson);
    }
}
