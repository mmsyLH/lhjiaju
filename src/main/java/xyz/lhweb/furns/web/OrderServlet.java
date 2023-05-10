package xyz.lhweb.furns.web;

import xyz.lhweb.furns.bean.Cart;
import xyz.lhweb.furns.bean.Member;
import xyz.lhweb.furns.service.OrderService;
import xyz.lhweb.furns.service.impl.OrderServiceImpl;
import xyz.lhweb.furns.utils.JDBCUtilsByDruid;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/orderServlet")
public class OrderServlet extends BasicServlet {
    private OrderService orderService=new OrderServiceImpl();
    protected void saveOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //内容 业务逻辑 todo
        Cart cart = (Cart)request.getSession().getAttribute("cart");
        //如果cart为空,说明会员没有购买任何家具,转发到首页
        //！！！当购买过后 cart虽然不为空，但是里面没有数据  多加一个逻辑判断
        if(cart==null||cart.isEmpty()){
            request.getRequestDispatcher("/index.jsp").forward(request,response);
            return;//直接返回
        }

        Member member = (Member) request.getSession().getAttribute("member");
        if(member==null){//说明用户没有登录
            request.getRequestDispatcher("/views/member/login.jsp").forward(request,response);
            return;//直接返回
        }
        //如果只是对orderService.saveOrder 进行事务控制 呢么不使用过滤器也可以
        //都不为空
        // String orderId = null;
        // try {
        //     orderId = orderService.saveOrder(cart, member.getId());
        //     JDBCUtilsByDruid.commit();
        // } catch (Exception e) {
        //     JDBCUtilsByDruid.rollBack();
        //     // throw new RuntimeException(e);
        // }
        //都不为空
        String orderId =orderService.saveOrder(cart, member.getId());
        request.getSession().setAttribute("orderId",orderId);
        // orderService.saveOrder(cart,)
        response.sendRedirect(request.getContextPath()+"/views/order/checkout.jsp");
    }
}
