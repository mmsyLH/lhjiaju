<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>商品详情</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <%@ include file="include.jsp"%>
</head>
<body>
<div id="registerHeader">
    <img src="${pageContext.request.contextPath}/img/tubiao.png">
</div>
<div style="margin-top: 20px">
    <input type="hidden" name="id" value="${requestScope.customer.id}">
    <img src="/img/${requestScope.commodity.pic}" class="detailsImg">
    <div class="detailsName">${requestScope.commodity.name}</div>
    <div class="detailsPrice">价格<span style="color: red ;font-size: 40px">￥${requestScope.commodity.price}</span></div>

    <div class="detailsNum">
        <div class="input-group">
            <span class="input-group-addon" style="background-color: #FFFFFF">数量</span>
            <input type="number" value="1" id="commodityNum" class="form-control">
            <span class="input-group-addon" style="background-color: #FFFFFF">件</span>
        </div>
        <p style="position: absolute;left: 230px;width: 100px;top: 6px;">库存${requestScope.commodity.repertory}</p>
    </div>

    <div id="buttons">
        <input type="button" value="立即购买" style="background-color: #FFEDED;color: red" onclick="toBuy(${requestScope.commodity.id})">
        <input type="button" value="添加购物车" style="background-color: #FF0036;color: #FFFFFF" onclick="addCart(${requestScope.commodity.id})">
    </div>
    <div style="display: inline-block;position: absolute;top: 560px;margin-left: 80px">
        服务承诺&ensp;
        破损包退&ensp;正品保证&ensp;极速退款退货&ensp;运费险&ensp;七天无理由退换
    </div>
</div>
</body>
</html>
