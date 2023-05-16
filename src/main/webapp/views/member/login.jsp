<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge"/>
    <title>罗汉家居网购</title>
    <!--    设置参考路径-->
    <base href="<%=request.getContextPath()+"/"%>">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <link rel="stylesheet" href="assets/css/vendor/vendor.min.css"/>
    <link rel="stylesheet" href="assets/css/plugins/plugins.min.css"/>
    <link rel="stylesheet" href="assets/css/style.min.css"/>
    <!--  先引入jq-->
    <script type="text/javascript" src="script/jquery-3.6.0.min.js"></script>
    <script type="text/javascript">
        $(function () {//页面加载完毕后执行的方法
            //给用户名输入框绑定一个blur
            $("#username").blur(function () {
                //获取输入的用户名  this代表的是当前的javascript对象
                var username = this.value;
                //发出ajax请求
                $.getJSON(
                    "memberServlet",
                    // "action=isExistsUsername&username="+username,
                    {
                        //相当于发送的ajax请求是通过json对象放入
                        "action": "isExistsUsername",
                        "username": username
                    },
                    function (data) {
                        // console.log("data=", data.isExist)
                        if (data.isExist) {
                            $("span.errorMsg").text("用户名已存在");
                        } else {
                            $("span.errorMsg").text("用户名不存在,可用");
                        }
                    }
                )
            })

            //决定是显示登录还是注册tab “” 不能少
            //如果注册失败，显示注册tab , 而不是默认的登录tab, 增加该代码  使用request.active不行 因为是经过tip.jsp跳转的
            <%--console.log("login.jsp页面的active","${param.active}")--%>
            if ("${param.active}" === "register") {
                $("#register_tab")[0].click();//模拟点击
            }

            //绑定点击事件
            //ctrl+home快速定位到页面最上方  ctrl+end 最下方
            //对验证码信息进行处理,获取新的验证码
            $("#codeImg").click(function () {
                this.src = "<%=request.getContextPath()%>/kaptchaServlet?d=" + new Date();
            })

            $("#sub-btn").click(function () {
                //获取到输入的用户名
                var usernameVal = $("#username").val();
                //编写正则表达式来验证
                var usernamePattern = /^\w{6,10}$/;
                //验证
                if (!usernamePattern.test(usernameVal)) {
                    $("span[class='errorMsg']").text("用户名格式不对,需要6-10个字符")
                    return false;//不提交
                }

                //完成对密码的校验
                var passwordVal = $("#password").val();
                var passwordPattern = /^\w{6,10}$/;
                if (!passwordPattern.test(passwordVal)) {
                    //展示密码错误提示-基本过滤器, 希望小伙伴感到知识不是每个都是新
                    //信心-》潜意识我学过.
                    $("span.errorMsg").text("密码格式不对, 需要6-10字符");
                    return false;
                }

                //两次密码相同
                //得到第二次输入密码
                var repwdVal = $("#repwd").val();
                if (repwdVal !== passwordVal) {
                    $("span.errorMsg").text("输入的两次密码不同");
                    return false;
                }

                //验证邮箱
                //得到邮箱 => 去看html
                var emailVal = $("#email").val();
                //老师说明 在java中，正则表达式的转义是\\, 在js 正则表达式 转义是\
                //如果你看不懂，回看java正则表达式
                var emailPattern = /^[\w-]+@([a-zA-Z]+\.)+[a-zA-Z]+$/; //偷懒->java
                if (!emailPattern.test(emailVal)) {
                    $("span.errorMsg").text("电子邮件格式不对");
                    return false;
                }

                // 验证码：浏览器这里验证不能为空
                var codeText = $("#code").val();
                //去掉验证码前后空格
                codeText = $.trim(codeText);
                if (codeText == null || codeText === "") {
                    //提示
                    $("span.errorMsg").text("验证码不能为空！");
                    return false;
                }

                //到这里就全部过关. => 我们暂时不提交，显示验证通过信息
                $("span.errorMsg").text("验证通过...");
                //目前我们写了后台，当验证通过时，就提交给后台
                return true;
            })

        })
    </script>
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
                        <a href="index.html"><img width="280px" src="assets/images/logo/logo.png"
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
<div class="login-register-area pt-70px pb-100px">
    <div class="container">
        <div class="row">
            <div class="col-lg-7 col-md-12 ml-auto mr-auto">
                <div class="login-register-wrapper">
                    <div class="login-register-tab-list nav">
                        <a class="active" data-bs-toggle="tab" href="#lg1">
                            <h4>会员登录</h4>
                        </a>
                        <a id="register_tab" data-bs-toggle="tab" href="#lg2">
                            <h4>会员注册</h4>
                        </a>
                    </div>
                    <div class="tab-content">
                        <div id="lg1" class="tab-pane active">
                            <div class="login-form-container">
                                <div class="login-register-form">
                                    <!--登录验证-->
                                    <form action="memberServlet" method="post">
                                        <%--增加一隐藏域--%>
                                        <input type="hidden" name="action" value="login"/>
                                        <input type="text" name="username" placeholder="Username"/>
                                        <input type="password" name="userpassword" placeholder="Password"/>
                                        <div class="button-box">
                                            <div class="login-toggle-btn">
                                                <label>
                                                    <input type="checkbox" name="autoLogin"/>
                                                    <a class="flote-none" href="javascript:void(0)">autoLogin</a>
                                                </label>
                                                <a href="#">Forgot Password?</a>
                                            </div>
                                            <button type="submit"><span>Login</span></button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <div id="lg2" class="tab-pane">
                            <div class="login-form-container">
                                <div class="login-register-form">
                                    <span class="errorMsg"
                                          style="float: right; font-weight: bold; font-size: 20pt; margin-left: 10px;">
                                        ${requestScope.msg}
                                    </span>
                                    <!--注册表单-->
                                    <form action="memberServlet" method="post">
                                        <%--增加隐藏域--%>
                                        <input type="hidden" name="action" value="register"/>
                                        <input type="text" id="username" name="username"
                                               value="${requestScope.username}" placeholder="Username"/>
                                        <input type="password" id="password" value="${requestScope.userpassword}"
                                               name="userpassword" placeholder="输入密码"/>
                                        <input type="password" id="repwd" name="userrepwd" placeholder="确认密码"/>
                                        <input name="email" id="email" value="${sessionScope.member.username}"
                                               placeholder="电子邮件" type="email"/>
                                        <input type="text" id="code" name="code" style="width: 50%"
                                               placeholder="验证码"/>　 　<img id="codeImg" alt="" src="kaptchaServlet">
                                        <div class="button-box">
                                            <button type="submit" id="sub-btn"><span>会员注册</span></button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
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
                                        <li class="li"><a class="single-link" href="login.jsp">登录</a></li>
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
</body>
</html>