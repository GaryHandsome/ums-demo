// 页面加载时，查询所有的商品信息
$.ajax({
    url:'product_list.do',
    method:'get',
    // data:?,
    dataType:'json',
    success:function( res ) {
        // DOM 操作
        console.log(res) ;
    }
}) ;