<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改我的个人信息</title>

    <%@ include file="include.jsp"%>

<%--    <link rel="icon" href="${pageContext.request.contextPath}/img/dsfw.png">--%>
<%--    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">--%>
<%--    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">--%>
<%--    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>--%>
<%--    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>--%>
<%--    <script src="${pageContext.request.contextPath}/js/script.js"></script>--%>
</head>
<body>
<div id="registerHeader">
    <img src="${pageContext.request.contextPath}/img/tubiao.png">
    <div style="display: inline-block;width: 200px;height: 100px;position: absolute;font-size: 20px;text-align: center">修改个人信息</div>
    <div style="display: inline-block;float: right;margin-right: 20px"><a href="${pageContext.request.contextPath}/permission/commodity/allType">返回首页</a></div>

</div>

<div id="updateFormDiv">
    <form id="updateForm">
        <input type="hidden" name="id" value="${requestScope.customer.id}">
        <div class="input-group">
            <span class="input-group-addon" style="background-color: white">姓名</span>
            <input type="text" name="name" class="form-control" required placeholder="请输入您的姓名">
        </div><br><br>

        <div class="input-group">
            <span class="input-group-addon" style="background-color: white">电话</span>
            <input type="text" name="phone" class="form-control" required placeholder="请输入您的电话">
        </div><br><br>

        <div class="input-group">
            <span class="input-group-addon" style="background-color: white">地址</span>
            <input type="text" name="address" class="form-control" required placeholder="请输入您的详细地址">
        </div><br><br>

        <div class="input-group">
            <span class="input-group-addon" style="background-color: white">邮编</span>
            <input type="text" name="postCode" class="form-control" required placeholder="请输入您的邮编">
        </div><br><br>

        <input type="submit" value="确认修改" class="btn btn-primary" style="width: 300px">
    </form>
</div>
</body>
</html>
