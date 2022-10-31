// 当用户文本框失去焦点时，触发此事件 - 验证用户是否已经注册
$("#uname").blur(function (){
    // 获取当前文本框输入的帐号
    // var name =  $(this).val() ;

    // 使用 JQ 向服务器发起异步请求 - $ = jQuery
    $.ajax({
        // 请求服务器的 URL 地址（Servlet）
        url:'check_user.do',
        // 指定请求的类型
        type:'post',
        // 向服务器传递的数据
        data:'username=' +  $(this).val()  ,
        // 设置服务器响应数据的类型
        dataType:'json',
        // 设置成功响应的回调函数
        success:function( res ) {
            console.log(res) ;
            // 显示提示消息
            $("#msg").text(res.msg) ;
            $("#msg").show();

            // 设置注册按钮是否可用
            if(res.code==200) {
                $("#reg").prop("disabled",false) ;
            } else {
                $("#reg").prop("disabled",true) ;
            }
        }
    }) ;


}) ;