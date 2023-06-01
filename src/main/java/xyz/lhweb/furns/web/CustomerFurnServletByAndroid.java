package xyz.lhweb.furns.web;

import com.google.gson.Gson;
import xyz.lhweb.furns.bean.Furn;
import xyz.lhweb.furns.bean.Page;
import xyz.lhweb.furns.service.FurnService;
import xyz.lhweb.furns.service.impl.FurnServiceImpl;
import xyz.lhweb.furns.utils.DataUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 客户家具servlet
 *
 * @author 罗汉
 * @date 2023/04/02
 */
@WebServlet("/customerFurnServletByAndroid")
public class CustomerFurnServletByAndroid extends BasicServlet {
    //定义一个FurnService属性
    private FurnService furnService = new FurnServiceImpl();

    /**
     * 安卓用的
     *
     * @param req  要求事情
     * @param resp 分别地
     * @throws ServletException servlet异常
     * @throws IOException      ioexception
     */
    protected void pageByName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // System.out.println("page");
        //这里的业务逻辑和后台分页显示家居信息非常相似
        int pageNo = DataUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = DataUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
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

        Gson gson = new Gson();
        String pageJson = gson.toJson(page);
        resp.getWriter().write(pageJson);
    }

}
