<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge"/>
    <title>罗汉家居网购</title>
    <!--    设置参考路径-->
    <base href="<%=request.getContextPath()+"/"%>">
    <!--  先引入jq-->
    <script type="text/javascript" src="script/jquery-3.6.0.min.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <link rel="stylesheet" href="assets/css/vendor/vendor.min.css"/>
    <link rel="stylesheet" href="assets/css/plugins/plugins.min.css"/>
    <link rel="stylesheet" href="assets/css/style.min.css"/>
</head>
<body>
<!-- Header Area start  -->
<div class="header section">
    <!-- Header Top Message Start -->
    <!-- Header Top  End -->
    <!-- Header Bottom  Start -->
    <div class="header-bottom d-none d-lg-block">
        <div class="container position-relative">
            <div class="row align-self-center">
                <!-- Header Logo Start -->
                <div class="col-auto align-self-center">
                    <div class="header-logo">
                        <a href="index.jsp"><img src="assets/images/logo/logo.png" alt="Site Logo"/></a>
                    </div>
                </div>
                <!-- Header Logo End -->
                <!-- Header Action Start -->
                <div class="col align-self-center">
                    <div class="header-actions">
                        <div class="header-bottom-set dropdown">
                            <a>欢迎: ${sessionScope.member.username}</a>
                        </div>
                        <div class="header-bottom-set dropdown">
                            <a href="#">订单管理</a>
                        </div>
                        <div class="header-bottom-set dropdown">
                            <a href="memberServlet?action=logout">安全退出</a>
                        </div>
                    </div>
                </div>
                <!-- Header Action End -->
            </div>
        </div>
    </div>
    <!-- Header Bottom  Start 手机端的header -->
    <div class="header-bottom d-lg-none sticky-nav bg-white">
        <div class="container position-relative">
            <div class="row align-self-center">
                <!-- Header Logo Start -->
                <div class="col-auto align-self-center">
                    <div class="header-logo">
                        <a href="index.jsp"><img width="280px" src="assets/images/logo/logo.png"
                                                 alt="Site Logo"/></a>
                    </div>
                </div>
                <!-- Header Logo End -->
            </div>
        </div>
    </div>
    <!-- Main Menu Start -->
    <div style="width: 100%;height: 50px;background-color: black"></div>
    <!-- Main Menu End -->
</div>
<!-- Header Area End  -->
<!-- login area start -->
<div class="container">
    <div class="row">

        <div style="margin:0 auto; margin-top:10px;width:1300px;">
            <strong>我的订单</strong>
            <table class="table table-bordered">
                <c:forEach items="${page.items}" var="order">
                    <tbody>
                    <tr class="success">
                        <th colspan="7">订单编号:${order.id}</th>
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
                                <c:when test="${order.status==0}">未付款</c:when>
                                <c:when test="${order.status==1}">已付款未发货</c:when>
                                <c:when test="${order.status==2}">已发货未确认收货</c:when>
                                <c:when test="${order.status==3}">已确认收货</c:when>
                                <c:otherwise>未知状态</c:otherwise>
                            </c:choose>
                        </td>
                        <td width="18%">
                            <a target="_blank"> ${order.createTime}</a>
                        </td>
                        <td width="20%">
                                ${order.createTime}
                        </td>
                        <td width="15%">
                            <span class="subtotal">${order.createTime}</span>
                        </td>
                        <td width="15%">
                            <span class="subtotal">${order.createTime}</span>
                        </td>
                        <td width="15%">
                            <span class="subtotal">￥：${order.price}</span>
                        </td>
                        <td width="10%">
                            <a href="orderServlet?action=showOrderInfo&orderId=${order.id}">进入编辑</a>
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
</div>
    <!--  分页导航 End -->
<!-- login area end -->

<!-- Footer Area Start -->
<div class="footer-area" style="width: 100%">
    <div class="footer-container">
        <div class="footer-top">
            <div class="container">
                <div class="row">
                    <!-- Start single blog -->
                    <!-- End single blog -->
                    <!-- Start single blog -->
                    <div class="col-md-6 col-sm-6 col-lg-3 mb-md-30px mb-lm-30px" data-aos="fade-up"
                         data-aos-delay="400">
                        <div class="single-wedge">
                            <h4 class="footer-herading">信息</h4>
                            <div class="footer-links">
                                <div class="footer-row">
                                    <ul class="align-items-center">
                                        <li class="li"><a class="single-link" href="about.html">关于我们</a></li>
                                        <li class="li"><a class="single-link" href="#">交货信息</a></li>
                                        <li class="li"><a class="single-link" href="privacy-policy.html">隐私与政策</a></li>
                                        <li class="li"><a class="single-link" href="#">条款和条件</a></li>
                                        <li class="li"><a class="single-link" href="#">制造</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- End single blog -->
                    <!-- Start single blog -->
                    <div class="col-md-6 col-lg-2 col-sm-6 mb-lm-30px" data-aos="fade-up" data-aos-delay="600">
                        <div class="single-wedge">
                            <h4 class="footer-herading">我的账号</h4>
                            <div class="footer-links">
                                <div class="footer-row">
                                    <ul class="align-items-center">
                                        <li class="li"><a class="single-link" href="my-account.html">我的账号</a>
                                        </li>
                                        <li class="li"><a class="single-link" href="cart.html">我的购物车</a></li>
                                        <li class="li"><a class="single-link" href="login.html">登录</a></li>
                                        <li class="li"><a class="single-link" href="wishlist.html">感兴趣的</a></li>
                                        <li class="li"><a class="single-link" href="checkout.html">结账</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- End single blog -->
                    <!-- Start single blog -->
                    <div class="col-md-6 col-lg-3" data-aos="fade-up" data-aos-delay="800">

                    </div>
                    <!-- End single blog -->
                </div>
            </div>
        </div>
        <div class="footer-bottom">
            <div class="container">
                <div class="row flex-sm-row-reverse">
                    <div class="col-md-6 text-right">
                        <div class="payment-link">
                            <img src="#" alt="">
                        </div>
                    </div>
                    <div class="col-md-6 text-left">
                        <p class="copy-text">Copyright &copy; 2023 罗汉家居</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Footer Area End -->
<script src="assets/js/vendor/vendor.min.js"></script>
<script src="assets/js/plugins/plugins.min.js"></script>
<!-- Main Js -->
<script src="assets/js/main.js"></script>
</body>
</html>