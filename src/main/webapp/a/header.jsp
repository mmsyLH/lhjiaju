<%--
  Created by IntelliJ IDEA.
  User: 49572
  Date: 2020/9/19
  Time: 10:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-default navbar-static-top navbar-inverse" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="javascript:void(0)">欢迎来到在线小商城</a>
        </div>
        <div>
            <ul class="nav navbar-nav navbar-right ">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        欢迎您,${sessionScope.customer.username} <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="javascript:void(0)" onclick="toUpdateCustomer(${sessionScope.customer.id})">修改个人信息</a></li>
                        <li><a href="javascript:void(0)" onclick="toMyCart()">我的购物车</a></li>
                        <li><a href="${pageContext.request.contextPath}/permission/order/showMyOrders">我的订单</a></li>
                        <li><a href="javascript:void(0)" onclick="logout()">安全退出</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>