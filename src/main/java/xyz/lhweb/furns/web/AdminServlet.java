package xyz.lhweb.furns.web;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * 管理servlet
 *
 * @author 罗汉
 * @date 2023/04/01
 */
public class AdminServlet extends BasicServlet {

    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // todo 管理员登录未完成
        request.getRequestDispatcher("/views/manage/manage_menu.jsp")
                .forward(request, response);
    }
}
