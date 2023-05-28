<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>

    <%@ include file="include.jsp"%>

<%--    <link rel="stylesheet" href="//at.alicdn.com/t/font_2079725_v6o44zzi6eo.css">--%>
<%--    <link rel="icon" href="${pageContext.request.contextPath}/img/dsfw.png">--%>
<%--    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">--%>
<%--    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">--%>
<%--    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>--%>
<%--    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>--%>
<%--    <script src="${pageContext.request.contextPath}/js/script.js"></script>--%>
</head>
<body>
<div id="registerHeader">
    <img src="${pageContext.request.contextPath}/img/tubiao.png">
    <div style="display: inline-block;width: 200px;height: 100px;position: absolute;font-size: 20px;text-align: center">欢迎注册</div>
    <div style="display: inline-block;float: right;margin-right: 20px">已有账号?<a href="../index.jsp">请登录></a></div>
</div>
<div id="registerBody">
    <form class="bs-example bs-example-form" role="form" id="registerForm">
        <div class="input-group">
            <span class="input-group-addon" style="background-color: white">用&ensp;户&ensp;名</span>
            <input type="text" name="username" class="form-control" onblur="checkUsername()" id="registerUsername" required>
        </div><br>
        <div class="input-group">
            <span class="input-group-addon" style="background-color: white">密&ensp;&ensp;&ensp;&ensp;码</span>
            <input type="password" name="password" class="form-control" required id="registerPassword">
        </div><br>
        <div class="input-group">
            <span class="input-group-addon" style="background-color: white">确认密码</span>
            <input type="password" name="checkPassword" class="form-control" required id="registerCheckpsw" onblur="checkPwd()">
        </div><br><br>
        <input id="registerSubmint" type="submit" value="注册" class="btn btn-primary" style="width: 100%">
    </form>

    <div style="text-align: left;width: 300px;height: 30px;position: absolute;left: 760px;top: 298px"><span id="passwordHint"></span></div>
    <div style="text-align: left;width: 300px;height: 30px;position: absolute;left: 760px;top: 190px"><span id="usernameHint"></span></div>
</div>
<div id="foot">
    <a href="https://zhangliyuanblog.club/">关于我们</a>|
    <a href="https://zhangliyuanblog.club/">联系我们</a>|
    <a href="https://zhangliyuanblog.club/">人才招聘</a>|
    <a href="https://zhangliyuanblog.club/">友情连接</a>
    <div style="margin-top: 15px">Copyright © 2004-2020  jay_zly 版权所有</div>
</div>
</body>
</html>
