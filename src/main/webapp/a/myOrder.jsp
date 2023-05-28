<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 49572
  Date: 2020/10/3
  Time: 15:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>我的订单</title>

    <%@ include file="include.jsp"%>

<%--    <link rel="icon" href="${pageContext.request.contextPath}/img/dsfw.png">--%>
<%--    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">--%>
<%--    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">--%>
<%--    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>--%>
<%--    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>--%>
<%--    <script src="${pageContext.request.contextPath}/js/script.js"></script>--%>
</head>
<body>

<div id="registerHeader">
    <img src="${pageContext.request.contextPath}/img/tubiao.png">
    <div style="display: inline-block;width: 200px;height: 100px;position: absolute;font-size: 20px;text-align: center">我的订单</div>
    <div style="display: inline-block;float: right;margin-right: 20px"><a href="${pageContext.request.contextPath}/permission/commodity/allType">返回首页</a></div>

</div>

    <c:forEach items="${requestScope.orderDetailsList}" var="o">
        <div style="width: 800px;height: 150px;border: 1px solid #D3D3D3;margin: 20px auto">
            <div style="height: 30px;background-color: #D3D3D3;line-height: 30px;padding-left: 20px">
                <b>${o.order.createTime}</b>&nbsp;&nbsp;&nbsp;&nbsp;
                订单号:${o.order.orderId}
            </div>
            
            <img src="/img/${o.commodity.pic}" style="width: 80px;height: 80px;margin: 20px 20px">


            <div style="display: inline-block;width: 200px;height: 100px">
                ${o.commodity.name}
            </div>

            <div style="display: inline-block;margin-left: 50px">
                单价￥${o.commodity.price}
            </div>


            <div style="display: inline-block;margin-left: 50px">
                数量:${o.num}
            </div>

            <div style="display: inline-block;margin-left: 50px">
                实付款:${o.subtotal}
            </div>
        </div>
    </c:forEach>
</body>
</html>
