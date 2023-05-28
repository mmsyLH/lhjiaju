<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>确认订单信息</title>

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
</div>


<input type="hidden" value="${requestScope.commodity.id}" id="orderId">
<table id="cartDetails">
    <tr style="background-color: #FFFFFF;height: 60px;text-align: center">
        <th>商品信息</th>
        <th>单价</th>
        <th>数量</th>
        <th>小计</th>
    </tr>
    <tr>
        <td>
            <img src="/img/${requestScope.commodity.pic}" style="width: 98px;height: 98px">
            <span>${requestScope.commodity.name}</span>
        </td>
        <td>
            ${requestScope.commodity.price}
        </td>
        <td id="orderNum">
            ${requestScope.num}
        </td>

        <td>
            ${requestScope.subtotal}
        </td>
    </tr>
</table>

<div id="submitDiv" style="text-align: right;padding-right: 20px">
    <p><b>实付款:</b><span style="font-size: 20px;color: #C0C0C0">￥</span><span style="color: #FF0036;font-size: 20px">${requestScope.subtotal}</span></p>
    <p><b>配送至:</b>${sessionScope.customer.address}</p>
    <p><b>收货人:</b>${sessionScope.customer.name},${sessionScope.customer.phone}</p>
</div>
<input type="button" id="buyBtn" value="提交订单" class="btn btn-default" style="float: right;clear: right;margin-right: 140px;background-color: black;color: #FFFFFF;width: 130px;height: 50px">
</body>
</html>
