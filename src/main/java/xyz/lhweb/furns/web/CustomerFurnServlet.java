package xyz.lhweb.furns.web;

import xyz.lhweb.furns.bean.Furn;
import xyz.lhweb.furns.bean.Page;
import xyz.lhweb.furns.service.FurnService;
import xyz.lhweb.furns.service.impl.FurnServiceImpl;
import xyz.lhweb.furns.utils.DataUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 客户家具servlet
 *
 * @author 罗汉
 * @date 2023/04/02
 */
public class CustomerFurnServlet extends BasicServlet {
    //定义一个FurnService属性
    private FurnService furnService = new FurnServiceImpl();

    /**
     * 这里仍然是一个分页请求家居信息的API/方法
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // System.out.println("page");
        //这里的业务逻辑和后台分页显示家居信息非常相似
        int pageNo = DataUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = DataUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        //调用service方法, 获取Page对象
        Page<Furn> page = furnService.page(pageNo, pageSize);
        // System.out.println(page);
        //将page放入到request域
        req.setAttribute("page", page);
        //请求转发到furn_manage.jsp
        req.getRequestDispatcher("/views/customer/index.jsp")
                .forward(req, resp);
    }

    protected void pageByName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // System.out.println("page");
        //这里的业务逻辑和后台分页显示家居信息非常相似
        int pageNo = DataUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = DataUtils.parseInt(req.getParameter("pageSize"), 4);
        //1 如果参数有name但是没有值,接受到的是"" 如果参数都没有 接受到的是null
        //2 把""和null合并处理
        String name = req.getParameter("name");
        if(null==name){
            name="";
        }

        //调用service方法, 获取Page对象
        Page<Furn> page = furnService.pageByName(pageNo, pageSize,name);
        //根据
        StringBuilder url = new StringBuilder("customerFurnServlet?action=pageByName");
        if (!"".equals(name)){//如果name空串
            url.append("&name=").append(name);
        }
        page.setUrl(url.toString());
        // System.out.println(url);
        // System.out.println(page);
        //将page放入到request域
        // System.out.println("page:"+page);
        req.setAttribute("page", page);
        // for (Furn item : page.getItems()) {
            // System.out.println(item);
        // }
        // System.out.println("page"+page);
        //请求转发到furn_manage.jsp
        req.getRequestDispatcher("/views/customer/index.jsp")
                .forward(req, resp);
    }
    /*
安卓测试
* */
    protected void text(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write("hello");
    }
}
