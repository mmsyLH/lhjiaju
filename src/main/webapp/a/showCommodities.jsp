<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="icon" href="${pageContext.request.contextPath}/img/dsfw.png">
<html>
<head>
    <title>商品页面</title>

    <%@ include file="include.jsp"%>

<%--    <link rel="stylesheet" href="//at.alicdn.com/t/font_2079725_v6o44zzi6eo.css">--%>
<%--    <link rel="icon" href="${pageContext.request.contextPath}/img/dsfw.png">--%>
<%--    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">--%>
<%--    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">--%>
<%--    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>--%>
<%--    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>--%>
<%--    <script src="${pageContext.request.contextPath}/js/script.js"></script>--%>
</head>
<body>
<input value="${requestScope.type}" type="hidden" id="searchType"/>
<div id="registerHeader">
    <img src="${pageContext.request.contextPath}/img/tubiao.png">
    <div style="display: inline-block;padding-left: 230px;margin-left: 0px">
        <form class="form-inline" role="form">
        <input type="text" name="" class="form-control" style="border: 2px solid #000000;width: 300px" id="searchValue" value="${requestScope.name}">
        <input type="button" value="搜索" class="btn btn-default" style="border: 2px solid #000000" id="serchBtn" onclick="searchCommodity()">
        </form>
    </div>
</div>
<div style="margin-top: 20px;margin-left: 60px"><p>当前位置>>><a href="${pageContext.request.contextPath}/permission/commodity/allType">首页</a>>>>${requestScope.type}</p></div>
<div style="width: 100%;margin-left: 36px">
<c:forEach items="${requestScope.commodities}" var="c">
    <div class="commodity col-lg-2" onclick="toCommodityDetail(${c.id})">
        <img src="/img/${c.pic}" class="commodityPic">
        <p class="commodityName">${c.name}</p>
        <p class="commodityPrice">￥${c.price}</p>
    </div>
</c:forEach>
</div>

</body>
</html>
