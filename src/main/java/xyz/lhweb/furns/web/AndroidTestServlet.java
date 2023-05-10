package xyz.lhweb.furns.web;

import com.google.gson.Gson;
import xyz.lhweb.furns.bean.Furn;
import xyz.lhweb.furns.service.FurnService;
import xyz.lhweb.furns.service.impl.FurnServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * 安卓测试servlet
 *
 * @author 罗汉
 * @date 2023/04/04
 */
@WebServlet("/androidTestServlet")
public class AndroidTestServlet extends BasicServlet {
    private FurnService furnService = new FurnServiceImpl();

    protected void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Gson gson = new Gson();
        Furn furn = furnService.queryFurnById(1);
        // 1. 演示把javebean -> json字符串
        String strBook = gson.toJson(furn);
        System.out.println("strBook=" + strBook);
        response.getWriter().write(gson.toJson(furn));
    }
}
