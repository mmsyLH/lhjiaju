package xyz.lhweb.furns.web;

import com.google.gson.Gson;
import xyz.lhweb.furns.bean.*;
import xyz.lhweb.furns.service.MemberService;
import xyz.lhweb.furns.service.UserService;
import xyz.lhweb.furns.service.impl.MemberServiceImpl;
import xyz.lhweb.furns.service.impl.UserServiceImpl;
import xyz.lhweb.furns.utils.DataUtils;

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
        String password = request.getParameter("userpassword");
        System.out.println("密码为：--------"+password);
        // System.out.println("用户名："+username+"密码："+userpassword);

        // 自动登录
        String autoLogin = request.getParameter("autoLogin");
        if (autoLogin != null) {
            Cookie autoLoginCookie = new Cookie("autoLoginCookie", username + "@" + password);
            autoLoginCookie.setPath("/");
            autoLoginCookie.setMaxAge(60 * 60 * 24 * 7);
            response.addCookie(autoLoginCookie);
        } else {
            Cookie autoLoginCookie = new Cookie("autoLoginCookie", "");
            autoLoginCookie.setPath("/");
            autoLoginCookie.setMaxAge(0);
            response.addCookie(autoLoginCookie);
        }

        Member member = memberService.login(new Member(null, username, password, null, null, null));
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
        System.out.println("注册密码为："+userpassword);
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
                Member member = new Member();
                member.setUsername(username);
                member.setPassword(userpassword);
                member.setEmail(email);
                member.setState(0);
                member.setCode(DataUtils.getCode());
                if (memberService.registerMember(member)) {
                    request.setAttribute("username", username);
                    request.setAttribute("url", request.getContextPath() + "/index.jsp");
                    request.setAttribute("second", 15);
                    request.setAttribute("infomation", "注册成功,收到邮件后请去邮箱激活！<br>即将返回首页");
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
        // 清除自动登录的cookie
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("autoLoginCookie")) {
                System.out.println("autoLoginCookie找到了，进行清除");
                cookie.setMaxAge(0);
                response.addCookie(cookie);
                break;
            }
        }
        // Cookie autoLoginCookie = new Cookie("autologin", "");
        // autoLoginCookie.setMaxAge(0);
        // response.addCookie(autoLoginCookie);
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
        resultMap.put("isExist", existsUsername);
        Gson gson = new Gson();
        String resJson = gson.toJson(resultMap);
        // 5 返回
        response.getWriter().write(resJson);
    }

    protected void active(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        System.out.println(code);
        try {
            MemberService memberService = new MemberServiceImpl();
            Member member = memberService.findByCode(code);
            System.out.println("需要更新的用户"+member);
            if (member != null) {
                member.setState(1);
                member.setCode(null);
                memberService.updateMember(member);
                request.setAttribute("username", member.getUsername());
                request.setAttribute("url", request.getContextPath() + "/index.jsp");
                request.setAttribute("second", 3);
                request.setAttribute("infomation", "激活成功！点击登录");
                request.getRequestDispatcher("/views/member/tip.jsp").forward(request, response);
            } else {
                request.setAttribute("username", member.getUsername());
                request.setAttribute("url", request.getContextPath() + "/index.jsp");
                request.setAttribute("second", 3);
                request.setAttribute("infomation", "激活失败！点击返回首页");
                request.getRequestDispatcher("/views/member/tip.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    protected void memberPageByname(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageNo = DataUtils.parseInt(request.getParameter("pageNo"), 1);
        int pageSize = DataUtils.parseInt(request.getParameter("pageSize"), 8);
        // 1 如果参数有name但是没有值,接受到的是"" 如果参数都没有 接受到的是null
        // 2 把""和null合并处理
        String name = request.getParameter("name");
        if (null == name) {
            name = "";
        }
        // 调用service方法, 获取Page对象
        // Page<Furn> page = furnService.pageByName(pageNo, pageSize,name);
        Page<Member> page = memberService.pageByName(pageNo, pageSize, name);
        // 根据
        StringBuilder url = new StringBuilder("memberServlet?action=memberPageByname");
        if (!"".equals(name)) {// 如果name不是空串
            url.append("&name=").append(name);
        }
        page.setUrl(url.toString());
        // 将page放入到request域
        request.setAttribute("page", page);
        // 请求转发到furn_manage.jsp
        request.getRequestDispatcher("/views/manage/member_manage.jsp")
                .forward(request, response);
    }

    protected void addMember(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        boolean existsUsername = memberService.isExistsUsername(name);
        Member loginMember = (Member) request.getSession().getAttribute("member");
        if (existsUsername) {
            request.setAttribute("username", loginMember.getUsername());
            String referer = request.getHeader("Referer");
            request.setAttribute("url", referer);
            request.setAttribute("second", 3);
            request.setAttribute("infomation", "用户名已经存在！");
            request.getRequestDispatcher("/views/member/tip.jsp").forward(request, response);
        }
        String pwd = request.getParameter("pwd");
        String email = request.getParameter("email");
        Member member = new Member();
        member.setUsername(name);
        member.setPassword(pwd);
        member.setEmail(email);
        member.setState(0);
        member.setCode(DataUtils.getCode());
        boolean res = memberService.registerMember(member);
        if (res) {
            request.setAttribute("username", loginMember.getUsername());
            request.setAttribute("url", request.getContextPath() + "/memberServlet?action=memberPageByname");
            request.setAttribute("second", 3);
            request.setAttribute("infomation", "添加成功！");
            request.getRequestDispatcher("/views/member/tip.jsp").forward(request, response);
        } else {
            request.setAttribute("username", loginMember.getUsername());
            String referer = request.getHeader("Referer");
            request.setAttribute("url", referer);
            request.setAttribute("second", 3);
            request.setAttribute("infomation", "添加失败！");
            request.getRequestDispatcher("/views/member/tip.jsp").forward(request, response);
        }

    }

    /**
     * 根据id删除
     *
     * @param request  请求
     * @param response 响应
     * @throws ServletException servlet异常
     * @throws IOException      ioexception
     */
    protected void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // System.out.println("del");
        int id = DataUtils.parseInt(request.getParameter("id"), 0);
        memberService.delById(id);
        // 重定向到家居列表页
        response.sendRedirect(request.getContextPath() + "/memberServlet?action=memberPageByname&pageNo=" + request.getParameter("pageNo"));
    }
    /**
     * 显示会员
     *
     * @param request  请求
     * @param response 响应
     * @throws ServletException servlet异常
     * @throws IOException      ioexception
     */
    protected void showMember(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = DataUtils.parseInt(request.getParameter("id"), 0);
        Member member = memberService.queryMemberById(id);
        request.setAttribute("member", member);
        // 请求转发到
        request.getRequestDispatcher("/views/manage/member_update.jsp").forward(request, response);
    }
    /**
     * 显示会员
     *
     * @param request  请求
     * @param response 响应
     * @throws ServletException servlet异常
     * @throws IOException      ioexception
     */
    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = DataUtils.parseInt(request.getParameter("id"), 0);
        String name = request.getParameter("membername");
        String pwd = request.getParameter("pwd");
        String email = request.getParameter("email");
        int state = DataUtils.parseInt(request.getParameter("state"), 0);
        Member member = memberService.queryMemberById(id);
        member.setPassword(pwd);
        member.setEmail(email);
        member.setUsername(name);
        member.setState(state);
        member.setId(id);
        memberService.updateMember(member);
        request.setAttribute("member", member);
        // 请求转发到
        request.getRequestDispatcher("/memberServlet?action=memberPageByname").forward(request, response);
    }
}
