/**
 * DOM操作 - 在表格中，创建一个新的行，并设置相关的数据
 * @param {表格} table
 * @param {商品对象} p
 */
function createTr(table,p) {
    // 第一：创建td节点
    var td1 = $("<td><input type='checkbox' class='ckAll' value='" + p.pid + "'></input></td>") ;
    var td2 = $("<td>" + p.productId + "</td>") ;
    var td3 = $("<td>" + p.productName + "</td>") ;
    var td4 = $("<td>" + p.productPrice + "</td>") ;
    var td5 = $("<td>" + p.productDate + "</td>") ;
    var td6 = $("<td><input type='checkbox'></input></td>") ;
    var td7 = $("<td><span class='spanDel'>删除</span><span class='spanUpdate'>修改</span><span class='spanDetail'>详情</span></td>") ;

    // 设置商品是否下架 - find方法是向下查找匹配元素
    td6.find(":checkbox").prop("checked",(p.productStatus==0)) ; ;

    // 第二：创建 tr 节点
    var tr = $("<tr></tr>") ;

    // 第三：把 td 节点，添加到tr节点中
    tr.append(td1).append(td2).append(td3).append(td4).append(td5).append(td6).append(td7);

    // 第四：把 tr节点 添加 table节点中
    table.append(tr) ;
}


// 页面加载时，查询所有的商品信息
$.ajax({
    url:'product_list.do',
    method:'get',
    // data:?,
    dataType:'json',
    success:function( res ) {
        // DOM 操作 - ResponseData
        $.each(res.data,function (index,p){
            createTr($("#tbl tbody"),p) ;
        }) ;
    }
}) ;