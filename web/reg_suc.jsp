<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册成功</title>
    <script src="js/jquery-1.12.4.js"></script>
</head>
<body>
    <h1>恭喜，${requestScope.user}注册成功，请<a href="login.html">登录</a></h1>

    <h3><span id="second">5</span>秒后自动跳转到登录页面</h3>

    <script>
        setInterval(function(){
            // 获取原来的值
            var second = $("#second").text() * 1;

            // 减1
            second -- ;

            // 判断、页面跳转
            if(second==0) {
                location.href = "login.html" ;
                return ;
            }

            $("#second").text(second) ;
        },1000)
    </script>
</body>
</html>
