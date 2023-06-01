package xyz.lhweb.furns.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * 基本servlet
 * 模板模式+反射+动态绑定  简化多个if else
 * @author 罗汉
 * @date 2023/04/01
 */
public abstract class BasicServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //解决接受到的数据是乱码的问题
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        // System.out.println("BasicServlet_doPost");
        String action = request.getParameter("action");
        System.out.println(action);
        //使用反射获取到当前对象的方法
        //1 this 就是请求的那一个真实servlet
        //System.out.println(this); 
        //2 declaredMethod 方法对象就是当前请求的的servlet对应的action名字
        try {
            Method declaredMethod =
                    this.getClass().getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);
            // System.out.println("declaredMethod:"+declaredMethod);
            //调用方法
            /**
             * protected void xyz.lhweb.furns.web.MemberServlet.login(javax.servlet.http.HttpServletRequest,
             * javax.servlet.http.HttpServletResponse) throws javax.servlet.ServletException,java.io.IOException
             */
            declaredMethod.invoke(this,request,response);
        } catch (Exception e) {
            //java基础->异常机制
            //将发生的异常,继续throw
            //心得体会: 异常机制是可以参与业务逻辑
            //暴露出来的，让你们看到. 让小伙伴学到东西.
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doPost(req, resp);
    }
}
