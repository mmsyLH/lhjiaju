<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>会员管理</title>
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
                <a href="views/manage/manage_menu.jsp" class="nav_link " data-page="furn_add.jsp">
                    <ion-icon name="home-outline" class="nav_icon"></ion-icon>
                    <span class="nav_name">后台首页</span>
                </a>
                <a class="nav_link" data-page="furn_manage.jsp">
                    <ion-icon name="chatbubbles-outline" class="nav_icon"></ion-icon>
                    <span class="nav_name">订单管理</span>
                </a>
                <div class="nav_link collapse2 active" data-page="manage_login.jsp">
                    <ion-icon name="folder-outline" class="nav_icon"></ion-icon>
                    <span class="nav_name">会员管理</span>
                    <ion-icon name="chevron-down-outline" class="collapse2__link"></ion-icon>
                    <ul class="collapse2_menu">
                        <a href="manage/furnServlet?action=page&pageNo=1&pageSize=5" class="collapse2__sublink" style="width: 100px">家居管理</a>
                        <a href="views/manage/furn_add.jsp?pageNo=${requestScope.page.pageNo}" class="collapse2__sublink">添加家居</a>
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
        <h3 class="cart-page-title">会员管理</h3>
        <div class="row">
            <div class="col-lg-12 col-md-12 col-sm-12 col-12">
                <form action="#">
                    <div class="table-content table-responsive cart-table-content">
                        <table>
                            <thead>
                            <tr>
                                <th>会员名称</th>
                                <th>会员邮箱</th>
                                <th>是否激活</th>
                                <th>编辑</th>
                                <th>删除</th>
                            </tr>
                            </thead>
                            <tbody>
                            <%--取出members集合，循环显示--%>
                            <c:forEach items="${requestScope.page.items}" var="member">
                                <tr>
                                    <td class="product-name"><a href="#">${member.username}</a></td>
                                    <td class="product-name"><a href="#">${member.email}</a></td>
                                    <td class="product-price-cart">
                                        <c:choose>
                                            <c:when test="${member.state == 1}">
                                                <span class="amount">激活</span>
                                            </c:when>
                                            <c:when test="${member.state == 0}">
                                                <span class="amount">未激活</span>
                                            </c:when>
                                        </c:choose>
                                    </td>
                                    <td class="product-remove">
                                        <a href="memberServlet?action=showMember&id=${member.id}&pageNo=${requestScope.page.pageNo}"><i
                                                class="icon-pencil"></i></a>
                                    </td>
                                    <td class="product-remove">
                                        <a class="deleteCss"
                                           href="memberServlet?action=del&id=${member.id}&pageNo=${requestScope.page.pageNo}"><i
                                                class="icon-close"></i></a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </form>
            </div>
        </div>
        <!--  Pagination Area Start 分页导航条 -->
        <div class="pro-pagination-style text-center mb-md-30px mb-lm-30px mt-6" data-aos="fade-up">
            <ul>
                <%--如果当前页 > 1 , 就显示上一页--%>
                <c:if test="${requestScope.page.pageNo > 1}">
                    <li><a href="memberServlet?action=memberPageByname&pageNo=${requestScope.page.pageNo - 1}">上一页</a></li>
                </c:if>
                <%--<li><a class="active" href="#">3</a></li>--%>
                <%--<li><a href="#">4</a></li>--%>
                <%--<li><a href="#">5</a></li>--%>
                <%--    显示所有的分页数， 先容易，再困难
                    思路: 先确定开始页数 begin 第1页
                            再确定结束页数 end 第pageTotalCount页
                    困惑：如果页数很多，怎么办? => 算法最多显示5页[这个规则可以由程序员决定.]
                    分析
                    1. 如果总页数<=5, 就全部显示
                    2. 如果总页数>5, 按照如下规则显示(这个规则是程序员/业务来确定):
                    2.1 如果当前页是前3页, 就显示1-5
                    2.2 如果当前页是后3页, 就显示最后5页
                    2.3 如果当前页是中间页, 就显示 当前页前2页, 当前页 , 当前页后两页
                    这里的关键就是要根据不同的情况来初始化begin, end
                --%>
                <c:choose>
                    <%--1. 如果总页数<=5, 就全部显示--%>
                    <c:when test="${requestScope.page.pageTotalCount <=5 }">
                        <c:set var="begin" value="1"/>
                        <c:set var="end" value="${requestScope.page.pageTotalCount}"/>
                    </c:when>
                    <%--2. 如果总页数>5--%>
                    <c:when test="${requestScope.page.pageTotalCount > 5 }">
                        <c:choose>
                            <%--2.1 如果当前页是前3页, 就显示1-5--%>
                            <c:when test="${requestScope.page.pageNo <=3 }">
                                <c:set var="begin" value="1"/>
                                <c:set var="end" value="5"/>
                            </c:when>
                            <%--2.2 如果当前页是后3页, 就显示最后5页--%>
                            <c:when test="${requestScope.page.pageNo >requestScope.page.pageTotalCount-3 }">
                                <%--总页数-4  比如1 2 3 4 5 6 7 8 当前是6,7,8 则显示4,5,6,7,8--%>
                                <c:set var="begin" value="${requestScope.page.pageTotalCount-4}"/>
                                <c:set var="end" value="${requestScope.page.pageTotalCount}"/>
                            </c:when>
                            <%--2.3 如果当前页是中间页, 就显示 当前页前2页, 当前页 , 当前页后两页--%>
                            <c:otherwise>
                                <%--其他情况--%>
                                <c:set var="begin" value="${requestScope.page.pageNo-2}"/>
                                <c:set var="end" value="${requestScope.page.pageNo+2}"/>
                            </c:otherwise>
                        </c:choose>
                    </c:when>
                </c:choose>
                <%--                <c:set var="begin" value="1"/>--%>
                <%--                <c:set var="end" value="${requestScope.page.pageTotalCount}"/>--%>
                <c:forEach begin="${begin}" end="${end}" var="i">
                    <%--如果i是当前页, 就使用class="active" 修饰--%>
                    <c:if test="${i == requestScope.page.pageNo}">
                        <li><a class="active" href="memberServlet?action=memberPageByname&pageNo=${i}">${i}</a></li>
                    </c:if>
                    <c:if test="${i != requestScope.page.pageNo}">
                        <li><a href="memberServlet?action=memberPageByname&pageNo=${i}">${i}</a></li>
                    </c:if>
                </c:forEach>
                <%--如果当前页 < 总页数 , 就显示下一页--%>
                <c:if test="${requestScope.page.pageNo < requestScope.page.pageTotalCount}">
                    <li><a href="memberServlet?action=memberPageByname&pageNo=${requestScope.page.pageNo + 1}">下一页</a></li>
                </c:if>
                <li><a>共 ${requestScope.page.pageTotalCount} 页</a></li>


            </ul>
        </div>
        <!--  Pagination Area End -->
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
                            <%--bug终于找到了！！！！！！！
                            #会去请求当前页 =>http://localhost:8080/lhjiaju/

                            ！！！！！！--%>

                            <%--<img src="#" alt="">--%>
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