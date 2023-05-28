<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>首页</title>
    <%@ include file="include.jsp"%>

<%--    <link rel="icon" href="${pageContext.request.contextPath}/img/dsfw.png">--%>
<%--    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">--%>
<%--    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">--%>
<%--    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>--%>
<%--    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>--%>
<%--    <script src="${pageContext.request.contextPath}/js/script.js"></script>--%>
</head>
<body style="background-color: #F5F5F5">
    <%@ include file="header.jsp"%>
    <div id="myCarousel" class="carousel slide ">
        <!-- 轮播（Carousel）指标 -->
        <ol class="carousel-indicators">
            <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
            <li data-target="#myCarousel" data-slide-to="1"></li>
            <li data-target="#myCarousel" data-slide-to="2"></li>
        </ol>
        <!-- 轮播（Carousel）项目 -->
        <div class="carousel-inner">
            <div class="item active">
                <img src="${pageContext.request.contextPath}/img/1.jpg" alt="First slide">
            </div>
            <div class="item">
                <img src="${pageContext.request.contextPath}/img/2.jpg" alt="Second slide">
            </div>
            <div class="item">
                <img src="${pageContext.request.contextPath}/img/3.jpg" alt="Third slide">
            </div>
        </div>
        <!-- 轮播（Carousel）导航 -->
        <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
            <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
            <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>


    <div>
        <nav class="navbar navbar-default" role="navigation">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse"
                            data-target="#example-navbar-collapse">
                        <span class="sr-only">切换导航</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                </div>
                <div class="collapse navbar-collapse" id="example-navbar-collapse">
                    <ul class="nav navbar-nav">
                        <c:forEach items="${requestScope.allType}" var="t">
                            <li><a href="javascript:void(0)" onclick="showCommodityByType('${t}')">${t}</a></li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </nav>
    </div>
<div>
    <img src="${pageContext.request.contextPath}/img/tejia.png" style="width: 168px;height: 60px;margin-left: 39px;margin-top: 10px">
</div>

<div style="margin: 40px 60px" >
    <c:forEach items="${requestScope.pageBean.datas}" var="c">
        <div class="commodity col-lg-2" onclick="toCommodityDetail(${c.id})">
            <img src="/img/${c.pic}" class="commodityPic">
            <p class="commodityName">${c.name}</p>
            <p class="commodityPrice">￥${c.price}</p>
        </div>
    </c:forEach>

    <div class="pageNum" style="clear: both;text-align: center;padding-top: 30px">
            <ul>
                <c:if test="${requestScope.pageBean.pageNow > 1}">
                    <li onclick="page(1)"><a href="javascript:void(0)">首页</a></li>
                    <li onclick="page(${requestScope.pageBean.pageNow - 1})"><a href="javascript:void(0)">上一页</a></li>
                </c:if>
                <span>${requestScope.pageBean.pageNow}/${requestScope.pageBean.pages}</span>
                <c:if test="${requestScope.pageBean.hasNext}">
                    <li onclick="page(${requestScope.pageBean.pageNow + 1})"><a href="javascript:void(0)">下一页</a></li>
                    <li onclick="page(${requestScope.pageBean.pages})"><a href="javascript:void(0)">尾页</a></li>
                </c:if>
            </ul>
    </div>
</body>
</html>
