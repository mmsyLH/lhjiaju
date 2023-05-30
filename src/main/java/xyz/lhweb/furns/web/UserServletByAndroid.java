package xyz.lhweb.furns.web;

import com.google.gson.Gson;
import xyz.lhweb.furns.bean.Member;
import xyz.lhweb.furns.bean.User;
import xyz.lhweb.furns.service.MemberService;
import xyz.lhweb.furns.service.UserService;
import xyz.lhweb.furns.service.impl.MemberServiceImpl;
import xyz.lhweb.furns.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
@WebServlet("/userServletByAndroid")
public class UserServletByAndroid extends BasicServlet {
    private UserService userService = new UserServiceImpl();
    private Gson gson = new Gson();

    /**
     * 登录
     *
     * @param request  请求
     * @param response 响应
     * @throws ServletException servlet异常
     * @throws IOException      ioexception
     */
    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


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
        String hobbys = request.getParameter("hobbys");

        // 获取用户提交的验证码
        String code = request.getParameter("code");

        // System.out.println(username+userpassword+email);
        // 判断用户名是否存在
        boolean existsUsername = userService.isExistsUsername(username);
        if (!existsUsername) {
            User user = new User(null, username, userpassword, email, hobbys);
            boolean res = userService.registerUser(user);
            if (res) {
                // 注册成功
                response.getWriter().write("1");
            } else {
                response.getWriter().write("0");

            }
        } else {
            // 用户名存在
            response.getWriter().write("2");
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
}
