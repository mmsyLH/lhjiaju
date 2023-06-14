<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>罗汉家居网购</title>
    <base href="<%=request.getContextPath() + "/"%>">
    <link rel="stylesheet" href="assets/css/manage/style.css">
    <%--    引入jquery--%>
    <script type="text/javascript" src="script/jquery-3.6.0.min.js"></script>
    <%--    右边样式--%>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <link rel="stylesheet" href="assets/css/vendor/vendor.min.css"/>
    <link rel="stylesheet" href="assets/css/plugins/plugins.min.css"/>
    <link rel="stylesheet" href="assets/css/style.min.css">
    <script type="text/javascript">
        $(function () {
            // alert("引入jquery成功")
            //绑定一个点击事件 基础选择器
            $("a.deleteCss").click(function () {
                //2个paremt找到tr          eq(1)第二个
                var furnName = $(this).parent().parent().find("td:eq(1)").text();
                //confirm 对话窗口
                //点击确定返回ture,点击取消返回false
                return confirm("你确定要删除[" + furnName + "]?");
            })
        })
    </script>
    <style type="text/css">

        #pic {
            position: relative;
        }

        input[type="file"] {
            position: absolute;
            left: 0;
            top: 0;
            height: 150px;
            opacity: 0;
            cursor: pointer;
        }
    </style>

    <script type="text/javascript">
        function prev(event) {
            //获取展示图片的区域
            var img = document.getElementById("prevView");
            //获取文件对象
            var file = event.files[0];
            //获取文件阅读器： Js的一个类，直接使用即可
            var reader = new FileReader();
            reader.readAsDataURL(file);
            reader.onload = function () {
                //给img的src设置图片url
                img.setAttribute("src", this.result);
            }
        }
    </script>
</head>
<body id="body-pd">
<div class="l-navbar" id="navbar">
    <nav class="nav">
        <div>
            <div class="nav_brand">
                <!-- <ion-icon name="menu-outline" class="nav_toggle" id="nav_toggle"></ion-icon> -->
                <ion-icon name="menu-outline" class="nav_toggle" id="nav-toggle"></ion-icon>
            </div>
            <div class="nav_list">
                <a href="views/manage/manage_menu.jsp" class="nav_link active" data-page="furn_add.jsp">
                    <ion-icon name="home-outline" class="nav_icon"></ion-icon>
                    <span class="nav_name">后台首页</span>
                </a>
                <a class="nav_link" data-page="furn_manage.jsp">
                    <ion-icon name="chatbubbles-outline" class="nav_icon"></ion-icon>
                    <span class="nav_name">订单管理</span>
                </a>
                <div class="nav_link collapse2" data-page="manage_login.jsp">
                    <ion-icon name="folder-outline" class="nav_icon"></ion-icon>
                    <span class="nav_name">家居后台管理</span>
                    <ion-icon name="chevron-down-outline" class="collapse2__link"></ion-icon>
                    <ul class="collapse2_menu">
                        <a href="manage/furnServlet?action=page&pageNo=1&pageSize=5" class="collapse2__sublink">家居管理</a>
                        <a href="views/manage/furn_add.jsp?pageNo=${requestScope.page.pageNo}" class="collapse2__sublink">添加家居</a>
                        <a href="#" class="collapse2__sublink">bbb</a>
                        <a href="#" class="collapse2__sublink">bbb</a>
                    </ul>
                </div>
                <a href="#" class="nav_link" data-page="furn_update.jsp.jsp">
                    <ion-icon name="pie-chart-outline" class="nav_icon"></ion-icon>
                    <span class="nav_name">统计</span>
                </a>
                <div class="nav_link collapse2">
                    <ion-icon name="people-outline" class="nav_icon"></ion-icon>
                    <span class="nav_name">用户</span>
                    <ion-icon name="chevron-down-outline" class="collapse2__link"></ion-icon>
                    <ul class="collapse2_menu">
                        <a href="#" class="collapse2__sublink">aaaaaaaaaaaaaaaaaaaaaaaaaaaaa</a>
                        <a href="#" class="collapse2__sublink">bbbb</a>
                        <a href="#" class="collapse2__sublink">cccc</a>
                    </ul>
                </div>

                <a href="#" class="nav_link">
                    <ion-icon name="settings-outline" class="nav_icon"></ion-icon>
                    <span class="nav_name">设置</span>
                </a>
            </div>
        </div>
        <a href="#" class="nav_link">
            <ion-icon name="log-out-outline" class="nav_icon"></ion-icon>
            <span class="nav_name">退出登陆</span>
        </a>
    </nav>
</div>
<%--右侧内容开始--%>
<!-- Header Area start  -->
<div class="header section">
    <!-- Header Top  End -->
    <!-- Header Bottom  Start -->
    <div class="header-bottom d-none d-lg-block">
        <div class="container position-relative">
            <div class="row align-self-center">
                <!-- Header Logo Start -->
                <div class="col-auto align-self-center">
                    <div class="header-logo">
                        <a href="manage_menu.jsp"><img src="assets/images/logo/logo.png" alt="Site Logo"/></a>
                    </div>
                </div>
                <!-- Header Logo End -->

                <!-- Header Action Start -->
                <div class="col align-self-center">
                    <div class="header-actions">

                        <!-- Single Wedge Start -->
                        <div class="header-bottom-set dropdown">
                            <a href="#">家居管理</a>
                        </div>
                        <div class="header-bottom-set dropdown">
                            <a href="#">订单管理</a>
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
                        <a href="manage_menu.jsp"><img width="280px" src="assets/images/logo/logo.png" alt="Site Logo"/></a>
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
        <h3 class="cart-page-title">用户后台管理-修改用户</h3>
        <div class="row">
            <div class="col-lg-12 col-md-12 col-sm-12 col-12">
                <%--自己一定要多思考，多看代码, 多动手, 因为是post,所以我们需要使用到隐藏域id,action--%>
                <form action="memberServlet?id=${requestScope.member.id}&action=update&pageNo=${param.pageNo}" method="post">
                    <%--传id--%>
                    <%--<input type="hidden" name="id" value="${requestScope.furn.id}">--%>
                    <%--&lt;%&ndash;确定调用servlet的update方法&ndash;%&gt;--%>
                    <%--<input type="hidden" name="action" value="update">--%>
                    <%--<input type="hidden" name="pageNo" value="${param.pageNo}">--%>
                    <div class="table-content table-responsive cart-table-content">
                        <table>
                            <thead>
                            <tr>
                                <th>用户名</th>
                                <th>邮箱</th>
                                <th>密码</th>
                                <th>是否激活(0未激活、1已激活)</th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td class="product-name"><input name="membername" style="width: 50%" type="text"
                                                                value="${requestScope.member.username}"/></td>
                                <td class="product-name"><input name="email" style="width: 90%" type="text"
                                                                value="${requestScope.member.email}"/></td>
                                <td class="product-name"><input name="pwd" style="width: 90%" type="text"
                                                                value="${requestScope.member.password}"/></td>
                                <%--<td class="product-name"><input name="pwd" style="width: 90%" type="text"--%>
                                <%--                                value="${requestScope.member.pwd}"/></td>--%>
                                <td class="product-price-cart"><input name="state" style="width: 30%" type="text"
                                                                      value="${requestScope.member.state}"/>
                                </td>
                                <td>
                                    <input type="submit"
                                           style="width: 100px;background-color: silver;border: silver;border-radius: 20%;"
                                           value="修改用户"/>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </form>
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
                        <p class="copy-text">Copyright &copy; 2023 罗汉家居~</p>
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
<%--右侧内容结束--%>
<%--左侧导航栏样式与Js--%>
<script type="module" src="https://unpkg.com/ionicons@5.1.2/dist/ionicons/ionicons.esm.js"></script>
<script nomodule="" src="https://unpkg.com/ionicons@5.1.2/dist/ionicons/ionicons.js"></script>
<script type="text/javascript">
    const showMenu = (toggleId,navbarId,bodyId)=>{
        const toggle = document.getElementById(toggleId),
            navbar = document.getElementById(navbarId);
        bodypadding = document.getElementById(bodyId);

        if(toggle && navbar){
            toggle.addEventListener('click',()=>{
                navbar.classList.toggle('expander')
                bodypadding.classList.toggle('body-pd')
            })
        }
    }

    showMenu('nav-toggle','navbar','body-pd')


    const linkColor = document.querySelectorAll(".nav_link")
    function colorLink(){
        linkColor.forEach(l=> l.classList.remove('active'))
        this.classList.add('active')
    }
    linkColor.forEach(l=> l.addEventListener('click',colorLink))

    const linkCollapse2 = document.getElementsByClassName('collapse2__link')
    var i

    for(i=0;i<linkCollapse2.length;i++){
        linkCollapse2[i].addEventListener('click',function(){
            const collapse2Menu = this.nextElementSibling
            collapse2Menu.classList.toggle('showCollapse2')

            const rotate = collapse2Menu.previousElementSibling
            rotate.classList.toggle("rotate")
        })
    }
</script>
</body>
</html>