package xyz.lhweb.furns.web;


import com.google.gson.Gson;
import xyz.lhweb.furns.bean.Member;
import xyz.lhweb.furns.bean.User;
import xyz.lhweb.furns.service.UserService;
import xyz.lhweb.furns.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

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
        // System.out.println("loginServlet被调用");
        String username = request.getParameter("username");
        String password = request.getParameter("userpassword");
        // System.out.println("用户名："+username+"密码："+userpassword);
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        User login = userService.login(user);
        HashMap<String, Object> map = new HashMap<>();
        String code="";
        if (login==null){
            //登录失败
            code="400";
            map.put("msg", "登录失败，用户名或者密码错误");
            response.getWriter();
        }else {
            //登录成功
            code="200";
            map.put("msg", "登录成功");
        }
        map.put("code", code);
        map.put("data",login);
        String strRes = gson.toJson(map);
        response.getWriter().write(strRes);
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
        String sex = request.getParameter("sex");


        System.out.println(username+userpassword+email+hobbys);
        // 判断用户名是否存在
        boolean existsUsername = userService.isExistsUsername(username);
        System.out.println(existsUsername+"existsUsername");
        HashMap<String, Object> map = new HashMap<>();
        if (!existsUsername) {
            User user = new User(null, username, userpassword, email, hobbys,sex);
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
        String code="";
        // if (login==null){
        //     //登录失败
        //     code="400";
        //     map.put("msg", "登录失败，用户名或者密码错误");
        //     response.getWriter();
        // }else {
        //     //登录成功
        //     code="200";
        //     map.put("msg", "登录成功");
        // }
        // map.put("code", code);
        // map.put("data",login);
        // String strRes = gson.toJson(map);
        // response.getWriter().write(strRes);
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
