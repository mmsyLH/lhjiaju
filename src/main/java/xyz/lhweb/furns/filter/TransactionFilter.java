package xyz.lhweb.furns.filter;

import xyz.lhweb.furns.utils.JDBCUtilsByDruid;

import javax.servlet.*;
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
