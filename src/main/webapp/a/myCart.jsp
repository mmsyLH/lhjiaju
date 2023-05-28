<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>我的购物车</title>

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
    <div style="display: inline-block;width: 200px;height: 100px;position: absolute;font-size: 20px;text-align: center">购物车</div>
    <div style="display: inline-block;float: right;margin-right: 20px"><a href="${pageContext.request.contextPath}/permission/commodity/allType">返回首页</a></div>

</div>


    <table id="cartDetails">
        <tr style="background-color: #FFFFFF;height: 60px;text-align: center">
            <th>商品信息</th>
            <th>单价</th>
            <th>数量</th>
            <th>金额</th>
            <th>操作</th>
        </tr>
        <c:if test="${empty sessionScope.cart}">
            <tr>
                <td colspan="5" style="text-align: center">
                    购物车暂无商品
                </td>
            </tr>
        </c:if>
        <c:forEach items="${sessionScope.cart}" varStatus="status" var="c">
            <tr>
                <td>
                    <img src="/img/${c.commodity.pic}" class="cartImg" style="margin-left: 20px">
                    <div class="cartName">${c.commodity.name}</div>
                </td>
                <td>${c.commodity.price}</td>
                <td>${c.num}</td>
                <td>${c.subtotal}</td>
                <td><a href="javascript:void(0)" onclick="deleteCart(${c.commodity.id})">删除</a></td>
            </tr>
        </c:forEach>
        <c:if test="${not empty sessionScope.cart}">
            <tr style="background-color: #FFFFFF;text-align: right"><td colspan="5">
                <input id="settlement" type="button" value="结算购物车" style="width: 150px;height: 60px;background-color: #FF6347;color: #FFFFFF;border: 0px">
            </td></tr>
        </c:if>
    </table>


</body>
</html>
