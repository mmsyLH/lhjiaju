package xyz.lhweb.furns.web;

import xyz.lhweb.furns.bean.*;
import xyz.lhweb.furns.service.MemberService;
import xyz.lhweb.furns.service.OrderService;
import xyz.lhweb.furns.service.impl.MemberServiceImpl;
import xyz.lhweb.furns.service.impl.OrderServiceImpl;
import xyz.lhweb.furns.utils.DataUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet("/orderServlet")
public class OrderServlet extends BasicServlet {
    private MemberService memberService=new MemberServiceImpl();
    private OrderService orderService=new OrderServiceImpl();
    protected void saveOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //内容 业务逻辑 todo
        Cart cart = (Cart)request.getSession().getAttribute("cart");
        //如果cart为空,说明会员没有购买任何家具,转发到首页
        //！！！当购买过后 cart虽然不为空，但是里面没有数据  多加一个逻辑判断
        if(cart==null||cart.isEmpty()){
            request.getRequestDispatcher("/manage_menu.jsp").forward(request,response);
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
        String orderId =orderService.saveOrder(cart, member.getUsername());
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
        Order order = orderService.queryOrderByOid(orderId);

        // 订单项
        List<CartItem> orderItems = orderService.getOrderInfoById(0, 5, orderId);
        request.setAttribute("orderItems", orderItems);
        // System.out.println("orderServlet_orderItems:"+orderItems);
        //
        // // 总金额
        BigDecimal totalPrices = BigDecimal.valueOf(0);
        for (CartItem orderItem : orderItems) {
            totalPrices = totalPrices.add(orderItem.getTotalPrice());
        }
        request.setAttribute("totalPrices", totalPrices);
        // // 显示个人信息 可设置更改
        Member loginUser = (Member) request.getSession().getAttribute("member");
        Member user = memberService.queryMemberByUsername(loginUser.getUsername());

        InfoText infoText = new InfoText(order.getAddress(), user.getUsername(), "18259421368", totalPrices, order.getStatus());
        // System.out.println("orderServlet_infoText:" + infoText);
        request.setAttribute("InfoText", infoText);
        // // 页面转发
        request.getRequestDispatcher("/views/order/order_info.jsp").forward(request, response);
    }
    protected void showOrderInfoByManage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderId = request.getParameter("orderId");
        System.out.println(orderId);
        request.setAttribute("orderId", orderId);
        Order order = orderService.queryOrderByOid(orderId);

        // 订单项
        List<CartItem> orderItems = orderService.getOrderInfoById(0, 5, orderId);
        request.setAttribute("orderItems", orderItems);
        // System.out.println("orderServlet_orderItems:"+orderItems);
        //
        // // 总金额
        BigDecimal totalPrices = BigDecimal.valueOf(0);
        for (CartItem orderItem : orderItems) {
            totalPrices = totalPrices.add(orderItem.getTotalPrice());
        }
        request.setAttribute("totalPrices", totalPrices);
        // // 显示个人信息 可设置更改
        Member loginUser = (Member) request.getSession().getAttribute("member");
        Member user = memberService.queryMemberByUsername(loginUser.getUsername());

        InfoText infoText = new InfoText(order.getAddress(), user.getUsername(), "18259421368", totalPrices, order.getStatus());
        // System.out.println("orderServlet_infoText:" + infoText);
        request.setAttribute("InfoText", infoText);
        // 页面转发
        request.getRequestDispatcher("/views/manage/order_infoByManage.jsp").forward(request, response);
    }
    protected void showOrdersByuid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageNo= DataUtils.parseInt(request.getParameter("pageNo"), 1);
        int pageSize= DataUtils.parseInt(request.getParameter("pageSize"), 6);
        Integer memberId = memberService.queryMemberByUsername(((Member) request.getSession().getAttribute("member")).getUsername()).getId();
        Page<Order> orderPage = orderService.pageByUid(pageNo, pageSize, memberId);
        request.setAttribute("page", orderPage);
        // request.setAttribute("page", orderService.pageByUid(DataUtils.parseInt(request.getParameter("pageNo"), 1),
        //         DataUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE),
        //         memberService.queryMemberByUsername(((User) request.getSession().getAttribute("member")).getUsername()).getId()));
        // 页面转发
        request.getRequestDispatcher("/views/order/order_list.jsp").forward(request, response);
    }
    protected void OrdersByuid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageNo= DataUtils.parseInt(request.getParameter("pageNo"), 1);
        int pageSize= DataUtils.parseInt(request.getParameter("pageSize"), 6);
        //1 如果参数有name但是没有值,接受到的是"" 如果参数都没有 接受到的是null
        //2 把""和null合并处理
        String memberId = request.getParameter("oid");
        if(null==memberId){
            memberId="";
        }
        // int memberId = DataUtils.parseInt(request.getParameter("uid"), 0);
        // Integer memberId = memberService.queryMemberByUsername(((Member) request.getSession().getAttribute("member")).getUsername()).getId();
        Page<Order> orderPage = orderService.pageByOid(pageNo, pageSize, memberId);
        request.setAttribute("page", orderPage);
        // 页面转发
        request.getRequestDispatcher("/views/manage/order_manage.jsp").forward(request, response);
    }
    protected void delByOid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // System.out.println("del");
        String id=request.getParameter("id");
        orderService.deleteOrderById(id);
        // 重定向到家居列表页
        response.sendRedirect(request.getContextPath() + "/orderServlet?action=OrdersByuid&pageNo=" + request.getParameter("pageNo"));
    }
    /**
     * 确认付款
     *
     * @param request  请求
     * @param response 响应
     * @throws ServletException servlet异常
     * @throws IOException      ioexception
     */
    protected void confirmPayment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //支付宝支付功能
        //跳转回原来页面
        int state= DataUtils.parseInt(request.getParameter("state"), -1);
        String orderId = request.getParameter("orderId");
            Order order = orderService.queryOrderByOid(orderId);
            order.setStatus(state+1);
        Boolean res = orderService.updateOrder(order);
        if (res) {
                // request.setAttribute("url", request.getContextPath() + "/jsp/order_info.jsp");
                request.setAttribute("url", "orderServlet?action=OrdersByuid");
                request.setAttribute("second", 5);
                request.setAttribute("infomation", "订单状态已更新");
                // 页面转发
                request.getRequestDispatcher("/views/member/tip.jsp").forward(request, response);
            }
    }
    /**
     * 确认付款
     *
     * @param request  请求
     * @param response 响应
     * @throws ServletException servlet异常
     * @throws IOException      ioexception
     */
    protected void confirmPayment2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //支付宝支付功能
        //跳转回原来页面
        int state= DataUtils.parseInt(request.getParameter("state"), -1);
        String orderId = request.getParameter("orderId");
        Order order = orderService.queryOrderByOid(orderId);
        order.setStatus(state+1);
        Boolean res = orderService.updateOrder(order);
        if (res) {
            // request.setAttribute("url", request.getContextPath() + "/jsp/order_info.jsp");
            request.setAttribute("url", "orderServlet?action=showOrdersByuid");
            request.setAttribute("second", 5);
            request.setAttribute("infomation", "订单状态已更新");
            // 页面转发
            request.getRequestDispatcher("/views/member/tip.jsp").forward(request, response);
        }
    }
    /**
     * 更新订单
     *
     * @param request  请求
     * @param response 响应
     * @throws ServletException servlet异常
     * @throws IOException      ioexception
     */
    protected void updateOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1 获取到登录的user对象
        Member loginUser = (Member) request.getSession().getAttribute("member");
        // System.out.println("OrderServlet_loginUser"+loginUser);
        String username = loginUser.getUsername();
        String orderId = request.getParameter("orderId");
        String address = request.getParameter("address");
        // String name = request.getParameter("username");
        // String telephone = request.getParameter("telephone");
        Order order = orderService.queryOrderByOid(orderId);
        order.setAddress(address);
        // order.setTelephone(telephone);
        // order.se(name);
        Boolean aBoolean = orderService.updateOrder(order);
        if (aBoolean) {
            // request.setAttribute("url", request.getContextPath() + "/jsp/order_info.jsp");
            request.setAttribute("url", "orderServlet?action=showOrderInfo&orderId=" + orderId);
            request.setAttribute("second", 5);
            request.setAttribute("infomation", "收货信息修改成功~<br>点击跳回到订单详情");
            request.setAttribute("username", username);
            // 页面转发
            request.getRequestDispatcher("/jsp/tip.jsp").forward(request, response);
        }else {
            request.setAttribute("url", "orderServlet?action=showOrderInfo&orderId=" + orderId);
            request.setAttribute("second", 5);
            request.setAttribute("infomation", "收货信息修改失败~<br>点击跳回到订单详情");
            request.setAttribute("username", username);
            // 页面转发
            request.getRequestDispatcher("/jsp/tip.jsp").forward(request, response);
        }
    }

}
