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
                var furnName = $(this).parent().parent().find("td:eq(0)").text();
                //confirm 对话窗口
                //点击确定返回ture,点击取消返回false
                return confirm("你确定要删除[" + furnName + "]?");
            })
        })
    </script>
</head>
<body id="body-pd">
<%--左侧内容--%>
<div class="l-navbar" id="navbar">
    <nav class="nav">
        <div>
            <div class="nav_brand">
                <ion-icon name="menu-outline" class="nav_toggle" id="nav-toggle"></ion-icon>
            </div>
            <div class="nav_list">
                <a href="views/manage/manage_menu.jsp" class="nav_link">
                    <ion-icon name="home-outline" class="nav_icon"></ion-icon>
                    <span class="nav_name">后台首页</span>
                </a>
                <a href="orderServlet?action=OrdersByuid" class="nav_link active">
                    <ion-icon name="chatbubbles-outline" class="nav_icon "></ion-icon>
                    <span class="nav_name">订单管理</span>
                </a>
                <%--<div class="nav_link collapse2">--%>
                <%--    <ion-icon name="chatbubbles-outline" class="nav_icon "></ion-icon>--%>
                <%--    <span class="nav_name">订单管理</span>--%>
                <%--    <ion-icon name="chevron-down-outline" class="collapse2__link"></ion-icon>--%>
                <%--    <ul class="collapse2_menu">--%>
                <%--        <a href="memberServlet?action=memberPageByname" class="collapse2__sublink">用户管理</a>--%>
                <%--        <a href="#" class="collapse2__sublink">添加用户</a>--%>
                <%--        <a href="#" class="collapse2__sublink">cccc</a>--%>
                <%--    </ul>--%>
                <%--</div>--%>
                <div class="nav_link collapse2">
                    <ion-icon name="folder-outline" class="nav_icon"></ion-icon>
                    <span class="nav_name">家居后台</span>
                    <ion-icon name="chevron-down-outline" class="collapse2__link"></ion-icon>
                    <ul class="collapse2_menu">
                        <a href="manage/furnServlet?action=page&pageNo=1&pageSize=5" class="collapse2__sublink">家居管理</a>
                        <a href="views/manage/furn_add.jsp?pageNo=${requestScope.page.pageNo}"
                           class="collapse2__sublink">添加家居</a>
                    </ul>
                </div>
                <%--<a href="#" class="nav_link" data-page="furn_update.jsp.jsp">--%>
                <%--    <ion-icon name="pie-chart-outline" class="nav_icon"></ion-icon>--%>
                <%--    <span class="nav_name">库存管理（未做）</span>--%>
                <%--</a>--%>
                <div class="nav_link collapse2">
                    <ion-icon name="people-outline" class="nav_icon"></ion-icon>
                    <span class="nav_name">用户</span>
                    <ion-icon name="chevron-down-outline" class="collapse2__link"></ion-icon>
                    <ul class="collapse2_menu">
                        <a href="memberServlet?action=memberPageByname" class="collapse2__sublink">用户管理</a>
                        <a href="views/manage/member_add.jsp" class="collapse2__sublink">添加用户</a>
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
                <a href="orderServlet?action=confirmPayment&state=${InfoText.state}&orderId=${orderId}">
                    <input type="submit" width="100" value="发货" name="submit" border="0"
                           style="background: url('${pageContext.request.contextPath}/assets/images/register.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0);
                                   height:35px;width:100px;color:white;">
                </a>
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
                <a href="orderServlet?action=confirmPayment&state=${InfoText.state}&orderId=${orderId}">
                    <input type="submit" width="100" value="付款" name="submit" border="0"
                           style="background: url('${pageContext.request.contextPath}/assets/images/register.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0);
                                   height:35px;width:100px;color:white;">
                </a>
            </c:otherwise>
        </c:choose>
        <%--根据订单的不同，显示不同的页面结束--%>
    </div>
</div>
<%--右侧内容结束--%>
<%--左侧导航栏样式与Js--%>
<script type="module" src="https://unpkg.com/ionicons@5.1.2/dist/ionicons/ionicons.esm.js"></script>
<script nomodule="" src="https://unpkg.com/ionicons@5.1.2/dist/ionicons/ionicons.js"></script>
<script type="text/javascript">
    const showMenu = (toggleId, navbarId, bodyId) => {
        const toggle = document.getElementById(toggleId),
            navbar = document.getElementById(navbarId);
        bodypadding = document.getElementById(bodyId);

        if (toggle && navbar) {
            toggle.addEventListener('click', () => {
                navbar.classList.toggle('expander')
                bodypadding.classList.toggle('body-pd')
            })
        }
    }
    showMenu('nav-toggle', 'navbar', 'body-pd')
    const linkColor = document.querySelectorAll(".nav_link")
    function colorLink() {
        linkColor.forEach(l => l.classList.remove('active'))
        this.classList.add('active')
    }

    linkColor.forEach(l => l.addEventListener('click', colorLink))

    const linkCollapse2 = document.getElementsByClassName('collapse2__link')
    var i

    for (i = 0; i < linkCollapse2.length; i++) {
        linkCollapse2[i].addEventListener('click', function () {
            const collapse2Menu = this.nextElementSibling
            collapse2Menu.classList.toggle('showCollapse2')

            const rotate = collapse2Menu.previousElementSibling
            rotate.classList.toggle("rotate")
        })
    }
</script>
</body>
</html>