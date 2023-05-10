package xyz.lhweb.furns.web;

import com.google.gson.Gson;
import xyz.lhweb.furns.bean.Cart;
import xyz.lhweb.furns.bean.CartItem;
import xyz.lhweb.furns.bean.Furn;
import xyz.lhweb.furns.service.FurnService;
import xyz.lhweb.furns.service.impl.FurnServiceImpl;
import xyz.lhweb.furns.utils.DataUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.HashMap;

/**
 * 购物车servlet
 *
 * @author 罗汉
 * @date 2023/04/07
 */
@WebServlet("/cartServlet")
public class CartServlet extends BasicServlet {
    private FurnService furnService = new FurnServiceImpl();

    /**
     * 添加物品
     *
     * @param request  请求
     * @param response 响应
     * @throws ServletException servlet异常
     * @throws IOException      ioexception
     */
    protected void addItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 得到添加商品的家具信息
        int id = DataUtils.parseInt(request.getParameter("id"), 0);
        // 根据id获取对应的Furn
        Furn furn = furnService.queryFurnById(id);
        // 判断是否为空 todo
        // if(furn==null){
        //
        // }
        // 根据furn构建cartitem
        CartItem cartItem = new CartItem(furn.getId(), furn.getName(), furn.getPrice(), 1, furn.getPrice());
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart == null) {// 说明session中没有cart
            cart = new Cart();
            request.getSession().setAttribute("cart", cart);
        }

        // 将cartItem加入到cart
        cart.addItem(cartItem);
        System.out.println("cart:" + cart);

        // 添加完毕后需要返回到添加家具的页面
        String referer = request.getHeader("Referer");
        System.out.println(referer);
        response.sendRedirect(referer);
    }
    protected void addItemByAjax(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 得到添加商品的家具信息
        int id = DataUtils.parseInt(request.getParameter("id"), 0);
        // 根据id获取对应的Furn
        Furn furn = furnService.queryFurnById(id);
        // 判断 todo
        // if(furn==null){
        //
        // }
        // 根据furn构建cartitem
        CartItem cartItem = new CartItem(furn.getId(), furn.getName(), furn.getPrice(), 1, furn.getPrice());
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart == null) {// 说明session中没有cart
            cart = new Cart();
            request.getSession().setAttribute("cart", cart);
        }

        // 将cartItem加入到cart
        cart.addItem(cartItem);
        // System.out.println("cart:" + cart);
        // 添加完毕后,返回json数据，给前端
        //前端得到json数据后，进行局部刷新即可
        //1 规定json格式{"cartTotalCount",3}
        //2 创建map
        HashMap<String, Object> res = new HashMap<>();
        res.put("cartTotalCount",cart.getTotalCount());
        //3 转成json
        String resJson=new Gson().toJson(res);
        //4 返回
        response.getWriter().write(resJson);

    }
    /**
     * 更新某个cartItem的数量,即更新购物车
     *
     * @param request  请求
     * @param response 响应
     * @throws ServletException servlet异常
     * @throws IOException      ioexception
     */
    protected void updateCount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 得到添加商品的家具信息
        int id = DataUtils.parseInt(request.getParameter("id"), 0);
        int count = DataUtils.parseInt(request.getParameter("count"), 1);
        System.out.println("CartServlet_updateCount  id,count" + id + ":" + count);
        // 获取session中的购物车
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart != null) {
            cart.updateCount(id, count);
        }
        // 回到请求更新购物车的页面
        String referer = request.getHeader("Referer");
        // System.out.println(referer);
        response.sendRedirect(referer);
    }

    /**
     * 删除项目
     *
     * @param request  请求
     * @param response 响应
     * @throws ServletException servlet异常
     * @throws IOException      ioexception
     */
    protected void deleteItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 得到添加商品的家具信息
        int id = DataUtils.parseInt(request.getParameter("id"), 0);
        // 获取session中的购物车
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart != null) {
            cart.deleteItem(id);
        }
        // 回到请求更新购物车的页面
        String referer = request.getHeader("Referer");
        // System.out.println(referer);
        response.sendRedirect(referer);
    }


    /**
     * 清除购物车
     *
     * @param request  请求
     * @param response 响应
     * @throws ServletException servlet异常
     * @throws IOException      ioexception
     */
    protected void clear(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart != null) {
            cart.clear();

        }
        // 回到请求更新购物车的页面
        String referer = request.getHeader("Referer");
        // System.out.println(referer);
        response.sendRedirect(referer);
    }
}
