package xyz.lhweb.furns.filter;

import xyz.lhweb.furns.bean.Member;
import xyz.lhweb.furns.service.impl.MemberServiceImpl;
import xyz.lhweb.furns.utils.JDBCUtilsByDruid;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 事务过滤器
 *
 * @author 罗汉
 * @date 2023/04/09
 */
public class TransactionFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        // 得到请求的url
        // StringBuffer requestURL = request.getRequestURL();
        // System.out.println("requestURL:"+requestURL);
        String url = request.getServletPath();
        System.out.println("url:" + url);

        Cookie[] cookies = request.getCookies();
        Cookie findCookie = null;
        if (cookies!=null){
            for (Cookie cookie : cookies) {
                if ("autoLoginCookie".equals(cookie.getName())) {
                    // System.out.println("autoLoginCookie");
                    findCookie = cookie;
                }
            }
        }
        if (findCookie != null) {
            String[] msg = findCookie.getValue().split("@");
            Member login = new Member();
            login.setUsername(msg[0]);
            login.setPassword(msg[1]);
            System.out.println("AuthFilter_login:"+login);
            boolean existsUsername = new MemberServiceImpl().isExistsUsername(login.getUsername());
            if (existsUsername) {// 放行
                // System.out.println(getClass().getName()+"放行");
                session.setAttribute("member", login);
                session.setMaxInactiveInterval(10 * 60);
                Member member = (Member)session.getAttribute("member");
                System.out.println("AuthFilter_member:"+member);
            }
        }

        try {
            //放行
            filterChain.doFilter(servletRequest, servletResponse);
            JDBCUtilsByDruid.commit();//统一提交
        } catch (Exception e) {
            //解读: 只有在try{} 中出现了异常,才会进行catch{} 执行
            //, 才会进行回滚.
            JDBCUtilsByDruid.rollBack();
            //抛出异常, 给tomcat, tomcat会根据errorpage 来显示对应
            //这里请体会: 异常机制是可以参与业务逻辑
            throw new RuntimeException(e);
        }
    }

    @Override
    public void destroy() {

    }
}
