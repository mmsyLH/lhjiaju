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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css" type="text/css"/>
    <%--    --%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style2.css" type="text/css"/>
    <style>
        .carousel-inner .item img {
            width: 100%;
            height: 300px;
        }
    </style>

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
        <div style="margin:0 auto;margin-top:10px;width:950px;">
            <strong>订单详情</strong>
            <table class="table table-bordered">
                <thead>
                <tr class="warning">
                    <th colspan="5">订单编号:${orderId}</th>
                </tr>
                <tr class="warning">
                    <th>图片</th>
                    <th>商品</th>
                    <th>价格</th>
                    <th>数量</th>
                    <th>小计</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${orderItems}" var="item">
                    <tr class="active">
                        <td width="60">
                            <input type="hidden" name="id" value="${item.id}">
                            <img src="${pageContext.request.contextPath}/${item.pimage}" width="80" height="60">
                        </td>
                        <td>
                            <a href="#">${item.name}</a>
                        </td>
                        <td>
                            ￥${item.price}
                        </td>
                        <td>
                                ${item.count}
                        </td>
                        <td>
                            <span class="subtotal">￥${item.totalPrice}</span>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div style="text-align:right;margin-right:120px;">
            商品金额: <strong style="color:#ff6600;">￥${InfoText.totalPrices}元</strong>
            <%--商品金额: <strong style="color:#ff6600;">￥${totalPrices}元</strong>--%>
        </div>
    </div>


    <div>
        <hr/>
        <form class="form-horizontal" style="margin-top:5px;margin-left:150px;"
              method="post"
              action="${pageContext.request.contextPath }/orderServlet">
            <input type="hidden" name="action" value="updateOrder">
            <input type="hidden" name="orderId" value="${orderId}">
            <div class="form-group">
                <label for="username" class="col-sm-5 control-label">收货信息：</label>
            </div>
            <div class="form-group">
                <label for="username" class="col-sm-1 control-label">地址</label>
                <div class="col-sm-5">
                    <input type="text" class="form-control" id="username" name="address" placeholder="请输入收货地址"
                           value="${InfoText.address}">
                </div>
            </div>
            <div class="form-group">
                <label for="inputPassword3" class="col-sm-1 control-label">收货人</label>
                <div class="col-sm-5">
                    <input type="text" class="form-control" id="inputPassword3" name="username" placeholder="请输收货人"
                           value="${InfoText.name}">
                </div>
            </div>
            <div class="form-group">
                <label for="confirmpwd" class="col-sm-1 control-label">电话</label>
                <div class="col-sm-5">
                    <input type="text" class="form-control" id="confirmpwd" name="telephone" placeholder="请输入联系方式"
                           value="${InfoText.telephone}">
                </div>
            </div>
            <c:choose>
                <c:when test="${InfoText.state==1 || InfoText.state==0}">
                    <input type="submit" width="190" value="更改收货信息" name="submit" border="0"
                           style="background: url('${pageContext.request.contextPath}/assets/images/register.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0);
                                   height:35px;width:130px;color:white;">
                </c:when>
            </c:choose>

        </form>

        <hr/>
        <%--根据订单的不同，显示不同的页面开始--%>
        <c:choose>
            <c:when test="${InfoText.state==1}">
                <div>已付款未发货</div>
            </c:when>
            <c:when test="${InfoText.state==2}">
                <div>已发货但未确认收货</div>
                <a href="orderServlet?action=confirmPayment&state=${InfoText.state}&orderId=${orderId}">
                    <input type="submit" width="100" value="确认收货" name="submit" border="0"
                           style="background: url('${pageContext.request.contextPath}/assets/images/register.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0);
                                   height:35px;width:100px;color:white;">
                </a>
            </c:when>
            <c:when test="${InfoText.state==3}">
                <div>已确认收货</div>
            </c:when>
            <c:otherwise>
                <div>订单未付款</div>
                <div style="margin-top:5px;margin-left:150px;">
                    <strong>选择银行：</strong>
                    <p>
                        <br/>
                        &nbsp  &nbsp &nbsp
                        <input type="radio" name="pd_FrpId" value="CMBCHINA-NET-B2C"/>支付宝
                        <img src="${pageContext.request.contextPath}/assets/bank_img/zfb.png" align="middle" style="height: 55px"/>
                    </p>
                    <hr/>
                    <p style="text-align:right;margin-right:100px;">
                        <a href="">
                            <input type="submit" width="190" value="暂不付款,回到首页" name="submit" border="0"
                                   style="background: url('${pageContext.request.contextPath}/assets/images/register.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0);
                                           height:35px;width:130px;color:white;">
                        </a>
                        <a >
                            <input type="submit" width="100" value="确认付款" name="submit" border="0"
                                   style="background: url('${pageContext.request.contextPath}/assets/images/register.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0);
                                           height:35px;width:100px;color:white;">
                        </a>

                    </p>
                    <hr/>
                </div>
            </c:otherwise>
        </c:choose>
        <%--根据订单的不同，显示不同的页面结束--%>

    </div>

</div>
<!-- login area end -->

<!-- Footer Area Start -->
<div class="footer-area">
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