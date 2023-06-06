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
                            <img src="${pageContext.request.contextPath}/assets/${item.pimage}" width="80" height="60">
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
                        <input type="radio" name="pd_FrpId" value="ICBC-NET-B2C" checked="checked"/>工商银行
                        <img src="${pageContext.request.contextPath}/assets/bank_img/icbc.bmp" align="middle"/>&nbsp;&nbsp;&nbsp;&nbsp;
                        <input type="radio" name="pd_FrpId" value="BOC-NET-B2C"/>中国银行
                        <img src="${pageContext.request.contextPath}/assets/bank_img/bc.bmp" align="middle"/>&nbsp;&nbsp;&nbsp;&nbsp;
                        <input type="radio" name="pd_FrpId" value="ABC-NET-B2C"/>农业银行
                        <img src="${pageContext.request.contextPath}/assets/bank_img/abc.bmp" align="middle"/>
                        <br/>
                        <br/>
                        <input type="radio" name="pd_FrpId" value="BOCO-NET-B2C"/>南京银行
                        <img src="${pageContext.request.contextPath}/assets/bank_img/nanjing.bmp" align="middle"/>&nbsp;&nbsp;&nbsp;&nbsp;
                        <input type="radio" name="pd_FrpId" value="PINGANBANK-NET"/>平安银行
                        <img src="${pageContext.request.contextPath}/assets/bank_img/pingan.bmp" align="middle"/>&nbsp;&nbsp;&nbsp;&nbsp;
                        <input type="radio" name="pd_FrpId" value="CCB-NET-B2C"/>建设银行
                        <img src="${pageContext.request.contextPath}/assets/bank_img/ccb.bmp" align="middle"/>
                        <br/>
                        <br/>
                        <input type="radio" name="pd_FrpId" value="CEB-NET-B2C"/>光大银行
                        <img src="${pageContext.request.contextPath}/assets/bank_img/guangda.bmp" align="middle"/>&nbsp;&nbsp;&nbsp;&nbsp;
                        <input type="radio" name="pd_FrpId" value="CMBCHINA-NET-B2C"/>招商银行
                        <img src="${pageContext.request.contextPath}/assets/bank_img/cmb.bmp" align="middle"/>

                    </p>
                    <hr/>
                    <p style="text-align:right;margin-right:100px;">
                        <a href="">
                            <input type="submit" width="190" value="暂不付款,回到首页" name="submit" border="0"
                                   style="background: url('${pageContext.request.contextPath}/assets/images/register.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0);
                                           height:35px;width:130px;color:white;">
                        </a>
                        <a href="orderServlet?action=confirmPayment&state=${InfoText.state}&orderId=${orderId}">
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
<script type="text/javascript">

    var total = 0;
    for (var i = 1; i < table.rows.length; i++) {
        total += parseFloat(table.rows[i].cells[4].innerHTML);
    }
    // 将总金额显示在页面上
    document.getElementById("total").innerHTML = total.toFixed(2);
</script>
</body>

</html>