<%--
  Created by IntelliJ IDEA.
  User: 韩顺平
  Version: 1.0
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--直接请求CustomerFurnServlet, 获取网站首页要显示的分页数据
 类似我们网站的入口页面
--%>
<jsp:forward page="/customerFurnServlet?action=pageByName&pageNo=1"></jsp:forward>