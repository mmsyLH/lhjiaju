package xyz.lhweb.furns.web;

import xyz.lhweb.furns.bean.Cart;
import xyz.lhweb.furns.bean.CartItem;
import xyz.lhweb.furns.bean.Member;
import xyz.lhweb.furns.bean.User;
import xyz.lhweb.furns.service.OrderService;
import xyz.lhweb.furns.service.impl.OrderServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

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
    /**
     * 显示订单信息
     *
     * @param request  请求
     * @param response 响应
     * @throws ServletException servlet异常
     * @throws IOException      ioexception
     */
    protected void showOrderInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderId = request.getParameter("orderId");
        System.out.println(orderId);
        request.setAttribute("orderId", orderId);

        orderService.queryOrderByOid(orderId);

        // 订单项
        // List<CartItem> orderItems = orderService.getOrderInfoById(0, 5, orderId);
        // request.setAttribute("orderItems", orderItems);
        // // System.out.println("orderServlet_orderItems:"+orderItems);
        //
        // // 总金额
        // double totalPrices = 0;
        // for (CartItem orderItem : orderItems) {
        //     totalPrices += orderItem.getTotalPrice();
        // }
        // request.setAttribute("totalPrices", totalPrices);
        // // 显示个人信息 可设置更改
        // User loginUser = (User) request.getSession().getAttribute("loginUser");
        // User user = userService.queryUserByUsername(loginUser.getUsername());
        // InfoText infoText = new InfoText(order.getAddress(), order.getName(), user.getTelephone(), totalPrices, order.getState());
        // System.out.println("orderServlet_infoText:" + infoText);
        // request.setAttribute("InfoText", infoText);
        // // 页面转发
        // request.getRequestDispatcher("/jsp/order_info.jsp").forward(request, response);
    }
}
