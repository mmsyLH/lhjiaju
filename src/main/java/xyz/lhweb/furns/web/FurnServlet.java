package xyz.lhweb.furns.web;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import xyz.lhweb.furns.bean.Furn;
import xyz.lhweb.furns.bean.Page;
import xyz.lhweb.furns.service.FurnService;
import xyz.lhweb.furns.service.impl.FurnServiceImpl;
import xyz.lhweb.furns.utils.DataUtils;
import xyz.lhweb.furns.utils.WebUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * 家具servlet
 *
 * @author 罗汉
 * @date 2023/04/01
 */

public class FurnServlet extends BasicServlet {
    private FurnService furnService = new FurnServiceImpl();


    /**
     * 列表
     *
     * @param request  请求
     * @param response 响应
     * @throws ServletException servlet异常
     * @throws IOException      ioexception
     */
    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // System.out.println("FurnServlet——list方法被调用");
        List<Furn> furns = furnService.queryFurns();
        for (Furn furn : furns) {
            // System.out.println(furn);
        }
        request.setAttribute("furns", furns);
        // 请求转发
        request.getRequestDispatcher("/views/manage/furn_manage.jsp").forward(request, response);
    }

    /**
     * 处理添加家居的请求
     *
     * @param request  请求
     * @param response 响应
     * @throws ServletException servlet异常
     * @throws IOException      ioexception
     */
    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // System.out.println("FurnServlet——add方法被调用");
        // String name = request.getParameter("name");
        // String maker = request.getParameter("maker");
        // String price = request.getParameter("price");
        // String sales = request.getParameter("sales");
        // String stock = request.getParameter("stock");
        // 对获取到的数据进行效验
        // 1. 使用java的正则表达式来验证 sales是一个正整数
        // 2. 如果没有通过校验，则直接返回furn_add.jsp -> request.setAttribute("mes","xx")
        // 3. 这里可以直接进行转换
        // try {
        //    int i = Integer.parseInt(sales);
        //}catch (NumberFormatException e) {
        //    //System.out.println("转换异常...");
        //    req.setAttribute("mes", "销量数据格式不对...");
        //    //返回到furn_add.jsp
        //    req.getRequestDispatcher("/views/manage/furn_add.jsp")
        //            .forward(req, resp);
        //    return;
        //}

        // SpringMVC -> 专门的用于数据校验的规则/框架 JSR303... Hibernate Validator
/*
        Furn furn = null;
        try {
            furn = new Furn(null, name, maker, new BigDecimal(price), Integer.valueOf(sales), Integer.valueOf(stock),
                    "assets/images/product-image/default.jpg");


        } catch (NumberFormatException e) {
            request.setAttribute("mes", "添加数据格式不对...");
            // 返回到furn_add.jsp
            request.getRequestDispatcher("/views/manage/furn_add.jsp")
                    .forward(request, response);
            return;
        }*/
        // 使用第二种方式完成将前端提交的数据封装称Furn的javabean对象
        // 使用BeanUtils完成对javabean对象的自动封装 commons-beanutils、commons-logging

        // 封装了一个工具类，用途是 把数据自动封装到bean对象
        Furn furn = DataUtils.copyParamToBean(request.getParameterMap(), new Furn());
/*        try {
            // 将getParameterMap() 数据封装到furn对象中
            //右键 alt+F8 可以在debug中查看getParameterMap()的值 前提是表单里的name和bean保持一致
            BeanUtils.populate(furn, request.getParameterMap());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }*/
        // System.out.println(furn);
        furnService.addFurns(furn);
        // 请求转发会导致重复添加
        // 重定向实质是让浏览器重新发起请求，因此使用完整的url会比较好
        // response.sendRedirect(request.getContextPath() + "/manage/furnServlet?action=list");
        response.sendRedirect(request.getContextPath() + "/manage/furnServlet?action=page&pageNo=" + request.getParameter("pageNo"));
        // request.getRequestDispatcher("/manage/furnServlet?action=list").forward(request,response);
    }


    /**
     * ▽
     * 删除
     *
     * @param request  请求
     * @param response 响应
     * @throws ServletException servlet异常
     * @throws IOException      ioexception
     */
    protected void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // System.out.println("del");
        int id = DataUtils.parseInt(request.getParameter("id"), 0);
        furnService.deleteFurnById(id);
        // 重定向到家居列表页
        response.sendRedirect(request.getContextPath() + "/manage/furnServlet?action=page&pageNo=" + request.getParameter("pageNo"));
    }

    /**
     * 展示回显家具
     *
     * @param request  请求
     * @param response 响应
     * @throws ServletException servlet异常
     * @throws IOException      ioexception
     */
    protected void showFurn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // System.out.println("showFurn");
        // String id1 = request.getParameter("id");
        // System.out.println(id1);
        int id = DataUtils.parseInt(request.getParameter("id"), 0);
        Furn furn = furnService.queryFurnById(id);
        request.setAttribute("furn", furn);
        // 如果请求带来的参数pageNo=1,而且是在请求转发到下一个页面，在下一个页面可以通过param.pageNo
        // request.setAttribute("pageNo",request.getParameter("pageNo"));
        // 请求转发到
        request.getRequestDispatcher("/views/manage/furn_update.jsp").forward(request, response);
    }

    /**
     * 更新
     *
     * @param request  请求
     * @param response 响应
     * @throws ServletException servlet异常
     * @throws IOException      ioexception
     */
    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // System.out.println("update");
        int id = DataUtils.parseInt(request.getParameter("id"), 0);
        Furn furn = furnService.queryFurnById(id);
        // Todo 做一个判断furn是否为空
        // 1. 判断是不是文件表单(enctype="multipart/form-data")
        if (ServletFileUpload.isMultipartContent(request)) {
            // System.out.println("OK");
            // 2. 创建 DiskFileItemFactory 对象, 用于构建一个解析上传数据的工具对象
            DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
            // 3. 创建一个解析上传数据的工具对象
            /**
             *     表单提交的数据就是 input 元素
             *     <input type="file" name="pic" id="" value="2xxx.jpg" onchange="prev(this)"/>
             *     家居名: <input type="text" name="name"><br/>
             *     <input type="submit" value="上传"/>
             */
            ServletFileUpload servletFileUpload =
                    new ServletFileUpload(diskFileItemFactory);
            // 解决接收到文件名是中文乱码问题
            servletFileUpload.setHeaderEncoding("utf-8");

            // 4. 关键的地方, servletFileUpload 对象可以把表单提交的数据text / 文件
            //   将其封装到 FileItem 文件项中
            //   如果我们不知道一个对象是什么结构[1.输出 2.debug 3. 底层自动看到]
            try {
                List<FileItem> list = servletFileUpload.parseRequest(request);
                // System.out.println("list==>" + list);
                for (FileItem fileItem : list) {
                    System.out.println("fileItem:" + fileItem);
                    // 判断是不是一个文件
                    if (fileItem.isFormField()) {// 不是一个文件 是表单
                        System.out.println("TRUE：fileItem.getName:--" + fileItem.getString("utf-8"));
                        if ("name".equals(fileItem.getFieldName())) {
                            furn.setName(fileItem.getString("utf-8"));
                        } else if ("maker".equals(fileItem.getFieldName())) {
                            furn.setMaker(fileItem.getString("utf-8"));
                        } else if ("price".equals(fileItem.getFieldName())) {
                            furn.setPrice(new BigDecimal(fileItem.getString()));
                        } else if ("sales".equals(fileItem.getFieldName())) {// 销量
                            furn.setSales(new Integer(fileItem.getString()));
                        } else if ("stock".equals(fileItem.getFieldName())) {// 库存
                            furn.setStock(new Integer(fileItem.getString()));
                        }
                    } else {// 是一个文件
                        // 首先获取文件名
                        String name = fileItem.getName();

                        // 如果用户没有选择新的图片，name="";
                        if (!name.equals("")) {


                            // System.out.println("false：fileItem.getName:--" + name);
                            // 用一个方法
                            // 把这个上传到 服务器的 temp下的文件保存到你指定的目录
                            // 1.指定一个目录 , 就是我们网站工作目录下
                            // F:\JavaWorksparce\javaweb\out\artifacts\fileupdown_war_exploded\`upload\
                            String filePath = "/" + WebUtils.FURN_IMG_DIRECTORY;
                            // 2.获取到完整目录
                            String fileRealPath =
                                    request.getServletContext().getRealPath(filePath);
                            System.out.println("完整目录 " + fileRealPath);
                            // 写一个工具类，可以返回/2024/11/11
                            // 2创建这个上传的文件目录=> io基础
                            // File file = new File(fileRealPath+ DataUtils.getYearMonthDay()+"\\");
                            String date = DataUtils.getYearMonthDay();
                            File file = new File(fileRealPath + date + "\\");
                            if (!file.exists()) {// 不存在
                                if (file.mkdirs()) {
                                    System.out.println("创建目录成功:" + fileRealPath);
                                } else {
                                    System.out.println("创建目录失败");
                                }
                            }
                            // 3 将我们的文件拷贝到这个目录 fileRealPath,FileFullname 文件上传的完整路径 目录+文件名
                            // 对上传的文件名进行处理，前面增加一个前缀 保证文件名唯一
                            name = UUID.randomUUID() + "_" + name;
                            String FileFullname = file + "\\" + name;
                            System.out.println("FileFullname:" + FileFullname);
                            fileItem.write(new File(FileFullname));
                            fileItem.getOutputStream().close();// 关闭流
                            furn.setImgPath(WebUtils.FURN_IMG_DIRECTORY + date + "/" + name);
                        }
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("不是文件表单");
        }
        System.out.println("furnservlet_update_furn:（更新完后）" + furn);
        // 更新furn
        furnService.updateFurn(furn);
        response.sendRedirect(request.getContextPath() + "/manage/furnServlet?action=page&pageNo=" + request.getParameter("pageNo"));
    }

    /**
     * 页面
     *
     * @param request  请求
     * @param response 响应
     * @throws ServletException servlet异常
     * @throws IOException      ioexception
     */
    protected void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // System.out.println("page");
        int pageNo = DataUtils.parseInt(request.getParameter("pageNo"), 1);
        int pageSize = DataUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
        Page<Furn> page = furnService.page(pageNo, pageSize);
        // System.out.println(page);
        request.setAttribute("page", page);
        // 请求转发
        // 请求转发
        request.getRequestDispatcher("/views/manage/furn_manage.jsp").forward(request, response);
    }
}
