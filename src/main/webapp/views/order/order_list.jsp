<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>

<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>订单详情</title>
    <%--解释 http://localhost:8080/项目名/jsp/index.jsp --%>
    <base href="<%=request.getContextPath() + "/"%>">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css" type="text/css"/>
    <script src="${pageContext.request.contextPath}/assets/js/jquery-1.11.3.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js" type="text/javascript"></script>
    <!-- 引入自定义css文件 style.css -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css" type="text/css"/>

    <style>
        body {
            margin-top: 20px;
            margin: 0 auto;
        }

        .carousel-inner .item img {
            width: 100%;
            height: 300px;
        }
    </style>
</head>

<body>

<!--
    时间：2015-12-30
    描述：菜单栏
-->
<div class="container-fluid">
    <div class="col-md-4">
        <img src="${pageContext.request.contextPath}/assets/img/logo2.png"/>
    </div>
    <div class="col-md-5">
        <img src="${pageContext.request.contextPath}/assets/img/header.png"/>
    </div>
    <div class="col-md-3" style="padding-top:20px">
        <ol class="list-inline">
            <c:if test="${empty sessionScope.loginUser}">
                <li><a href="jsp/login.jsp">登录</a></li>
                <li><a href="jsp/register.jsp">注册</a></li>
            </c:if>
            <c:if test="${not empty sessionScope.loginUser}">
                <a>欢迎: ${sessionScope.loginUser.username}</a>
                <li><a href="userServlet?action=logout">退出登录</a></li>
                <li><a href="orderServlet?action=showOrdersByuid">历史订单</a></li>
            </c:if>
            <li><a href="jsp/cart.jsp">购物车${sessionScope.cart.totalCount}</a></li>
        </ol>
    </div>
</div>
<!--
    时间：2015-12-30
    描述：导航条
-->
<div class="container-fluid">
    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                        data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">首页</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li class="active"><a>手机数码<span class="sr-only">(current)</span></a></li>
                    <li><a href="#">电脑办公</a></li>
                    <li><a href="#">电脑办公</a></li>
                    <li><a href="#">电脑办公</a></li>
                </ul>
                <form class="navbar-form navbar-right" role="search">
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Search">
                    </div>
                    <button type="submit" class="btn btn-default">Submit</button>
                </form>

            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container-fluid -->
    </nav>
</div>


</nav>

<div class="container">
    <div class="row">

        <div style="margin:0 auto; margin-top:10px;width:1300px;">
            <strong>我的订单</strong>
            <table class="table table-bordered">
                <c:forEach items="${page.items}" var="order">
                <tbody>
                <tr class="success">
                    <th colspan="7">订单编号:${order.oid}</th>
                </tr>
                <tr class="warning">
                    <th>订单状态</th>
                    <th>订单时间</th>
                    <th>电话</th>
                    <th>地址</th>
                    <th>收货人</th>
                    <th>订单价格</th>
                    <th>修改订单</th>
                </tr>

                    <tr class="active">
                        <td width="10%">
                            <input type="hidden" name="id" value="22">
                            <c:choose>
                                <c:when test="${order.state==0}">未付款</c:when>
                                <c:when test="${order.state==1}">已付款未发货</c:when>
                                <c:when test="${order.state==2}">已发货未确认收货</c:when>
                                <c:when test="${order.state==3}">已确认收货</c:when>
                                <c:otherwise>未知状态</c:otherwise>
                            </c:choose>
                        </td>
                        <td width="18%">
                            <a target="_blank"> ${order.ordertime}</a>
                        </td>
                        <td width="20%">
                                ${order.telephone}
                        </td>
                        <td width="15%">
                            <span class="subtotal">${order.address}</span>
                        </td>
                        <td width="15%">
                            <span class="subtotal">${order.name}</span>
                        </td>
                        <td width="15%">
                            <span class="subtotal">￥：${order.total}</span>
                        </td>
                        <td width="10%">
                            <a href="orderServlet?action=showOrderInfo&orderId=${order.oid}">进入编辑</a>
                        </td>
                    </tr>
                </tbody>
                </c:forEach>

            </table>
        </div>
    </div>
    <!--  分页导航条 -->
    <div class="pro-pagination-style text-center mb-md-30px mb-lm-30px mt-6" data-aos="fade-up">
        <ul class="pagination">
            <li><a href="${pageContext.request.contextPath}/orderServlet?action=showOrdersByuid&pageNo=1">首页</a></li>
            <c:if test="${requestScope.page.pageNo > 1}">
                <li><a href="${pageContext.request.contextPath}/orderServlet?action=showOrdersByuid&pageNo=${requestScope.page.pageNo - 1}">上一页</a></li>
            </c:if>
            <c:set var="begin" value="1"/>
            <c:set var="end" value="${requestScope.page.pageTotalCount}"/>
            <c:forEach begin="${begin}" end="${end}" var="i">
                <c:if test="${i == requestScope.page.pageNo}">
                    <li class="active">
                        <a class="active" href="${pageContext.request.contextPath}/orderServlet?action=showOrdersByuid&pageNo=${i}">${i}</a>
                    </li>
                </c:if>
                <c:if test="${i != requestScope.page.pageNo}">
                    <li>
                        <a href="${pageContext.request.contextPath}/orderServlet?action=showOrdersByuid&pageNo=${i}">${i}</a>
                    </li>
                </c:if>
            </c:forEach>
            <c:if test="${requestScope.page.pageNo < requestScope.page.pageTotalCount}">
                <li><a href="${pageContext.request.contextPath}/orderServlet?action=showOrdersByuid&pageNo=${requestScope.page.pageNo + 1}">下一页</a></li>
            </c:if>
            <li><a href="${pageContext.request.contextPath}/orderServlet?action=showOrdersByuid&pageNo=${requestScope.page.pageTotalCount}">尾页</a></li>
            <li><a>共 ${requestScope.page.pageTotalCount} 页</a></li>
        </ul>
    </div>
    <!--  分页导航 End -->

</div>

<div style="margin-top:50px;">
    <img src="${pageContext.request.contextPath}/assets/image/footer.jpg" width="100%" height="78" alt="我们的优势"
         title="我们的优势"/>
</div>

<div style="text-align: center;margin-top: 5px;">
    <ul class="list-inline">
        <li><a>关于我们</a></li>
        <li><a>联系我们</a></li>
        <li><a>招贤纳士</a></li>
        <li><a>法律声明</a></li>
        <li><a>友情链接</a></li>
        <li><a target="_blank">支付方式</a></li>
        <li><a target="_blank">配送方式</a></li>
        <li><a>服务声明</a></li>
        <li><a>广告声明</a></li>
    </ul>
</div>
<div style="text-align: center;margin-top: 5px;margin-bottom:20px;">
    Copyright &copy; 2005-2016 传智商城 版权所有
</div>
</body>

</html>