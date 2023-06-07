<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>侧边展开导航栏</title>
    <base href="<%=request.getContextPath()+"/"%>">
    <link rel="stylesheet" href="views/manage/index.css">
    <!-- 引入字体图标 -->
    <link href="https://cdn.bootcdn.net/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <!-- 移动端适配 -->
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <link rel="stylesheet" href="assets/css/vendor/vendor.min.css"/>
    <link rel="stylesheet" href="assets/css/plugins/plugins.min.css"/>
    <link rel="stylesheet" href="assets/css/style.min.css">
</head>
<body>
<div class="navbar" style="">
    <div>
        <input type="checkbox" id="checkbox">
        <label for="checkbox">
            <i class="fa fa-bars" aria-hidden="true">
            </i>
        </label>
        <ul>
            <li>
                <img src="./images/1.png">
                <span>欢迎你,管理员!</span>
            </li>
            <li>
                <a href="#">
                    <i class="fa fa-home" aria-hidden="true"></i>
                    <span>后台首页</span>
                </a>
            </li>
            <li>
                <a href="#">
                    <i class="fa fa-sitemap" aria-hidden="true"></i>
                    <span>商品列表</span>
                </a>
            </li>
            <li>
                <a href="#">
                    <i class="fa fa-user-circle-o" aria-hidden="true"></i>
                    <span>用户列表</span>
                </a>
            </li>
            <li>
                <a href="#">
                    <i class="fa fa-shopping-bag" aria-hidden="true"></i>
                    <span>订单列表</span>
                </a>
            </li>
            <li>
                <a href="#">
                    <i class="fa fa-th-large" aria-hidden="true"></i>
                    <span>功能列表</span>
                </a>
            </li>
        </ul>
    </div>
    <%--内容区域--%>
    <div style="width: 100vh">
        <%--      header--%>
        <div class="header section" style="display: flex; flex-direction: column;">
            <!-- Header Top  End -->
            <!-- Header Bottom  Start -->
            <div class="header-bottom d-none d-lg-block">
                <div class="container position-relative">
                    <div class="row align-self-center">
                        <!-- Header Logo Start -->
                        <div class="col-auto align-self-center">
                            <div class="header-logo">
                                <a href="index.html"><img src="assets/images/logo/logo.png" alt="Site Logo"/></a>
                            </div>
                        </div>
                        <!-- Header Logo End -->

                        <!-- Header Action Start -->
                        <div class="col align-self-center">
                            <div class="header-actions">
                                <!-- Single Wedge Start -->
                                <div class="header-bottom-set dropdown">
                                    <%--<a href="manage/furnServlet?action=list">家居管理</a>--%>
                                    <a href="manage/furnServlet?action=page&pageNo=1&pageSize=5">家居管理</a>
                                </div>
                                <div class="header-bottom-set dropdown">
                                    <a href="pages/manager/manager.html">订单管理</a>
                                </div>
                                <div class="header-bottom-set dropdown">
                                    <a href="views/manage/furn_count.jsp">库存管理</a>
                                </div>
                                <div class="header-bottom-set dropdown">
                                    <a href="views/manage/index.jsp">index.jsp</a>
                                </div>
                            </div>
                        </div>
                        <!-- Header Action End -->
                    </div>
                </div>
            </div>
            <!-- Header Bottom  End -->
            <!-- Header Bottom  Start 手机端的header -->
            <div class="header-bottom d-lg-none sticky-nav bg-white">
                <div class="container position-relative">
                    <div class="row align-self-center">
                        <!-- Header Logo Start -->
                        <div class="col-auto align-self-center">
                            <div class="header-logo">
                                <a href="index.html"><img width="280px" src="assets/images/logo/logo.png" alt="Site Logo"/></a>
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
        <!-- Cart Area Start -->
        <div class="cart-main-area pt-100px pb-100px">
            <div class="container">
                <h3 class="cart-page-title">家居后台管理-菜单</h3>
                <div class="row">
                    <div class="col-lg-12 col-md-12 col-sm-12 col-12">

                    </div>
                </div>
            </div>
        </div>
        <!-- Cart Area End -->

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
                                                <li class="li"><a class="single-link" href="privacy-policy.html">隐私与政策</a>
                                                </li>
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
                                <p class="copy-text">Copyright &copy; 2023 罗汉家居~</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>


    <%--内容区域结束--%>
</div>
</body>
</html>