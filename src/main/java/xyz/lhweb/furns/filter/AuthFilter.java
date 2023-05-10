package xyz.lhweb.furns.filter;

import com.google.gson.Gson;
import xyz.lhweb.furns.bean.Member;
import xyz.lhweb.furns.utils.WebUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 身份验证过滤器
 * 登陆过放行，没有登录，就回到登录
 *
 * @author 罗汉
 * @date 2023/04/08
 */
public class AuthFilter implements Filter {
    // 把要排除的url放入到excludedUrls
    private List<String> excludedUrls;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 获取到配置的excludedUrls
        String strExcludedUrls = filterConfig.getInitParameter("excludedUrls");
        String[] splitUrl = strExcludedUrls.split(",");
        // 将splitUrl转成list
        excludedUrls = Arrays.asList(splitUrl);
        System.out.println("excludedUrls" + excludedUrls);

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // 得到请求的url
        // StringBuffer requestURL = request.getRequestURL();
        // System.out.println("requestURL:"+requestURL);
        String url = request.getServletPath();
        System.out.println("url:" + url);

        // 判断是否要验证
        if (!excludedUrls.contains(url)) {

            // 得到session中的member对象
            Member member = (Member) request.getSession().getAttribute("member");
            // Null说明该用户并没有登录过
            if (member == null) {

                //判断是不是ajax请求
                if(!WebUtils.isAjaxRequest(request)){
                    request.getRequestDispatcher("/views/member/login.jsp").forward(servletRequest, servletResponse);
                }else {//如果是ajax请求 返回一个url  要用json格式
                    HashMap<String, Object> resMap = new HashMap<>();
                    resMap.put("url", request.getContextPath()+"/views/member/login.jsp");
                    String resJson = new Gson().toJson(resMap);
                    servletResponse.getWriter().write(resJson);
                }

                // 下面代码不走了
                return;
            }
        }
        // 放行
        filterChain.doFilter(servletRequest, servletResponse);
    }


    @Override
    public void destroy() {

    }


}
