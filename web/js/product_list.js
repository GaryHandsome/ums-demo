/**
 * DOM操作 - 在表格中，创建一个新的行，并设置相关的数据
 * @param {表格} table
 * @param {商品对象} p
 */
function createTr(table, p) {
    // 第一：创建td节点
    var td1 = $("<td><input type='checkbox' class='ckAll' name='productIds' value='" + p.productId + "'></input></td>");
    var td2 = $("<td>" + p.productId + "</td>");
    var td3 = $("<td>" + p.productName + "</td>");
    var td4 = $("<td>" + p.productPrice + "</td>");
    var td5 = $("<td>" + p.productDate + "</td>");
    var td6 = $("<td><input type='checkbox'></input></td>");
    var td7 = $("<td><span class='spanDel'>删除</span><span class='spanUpdate'>修改</span><span class='spanDetail'>详情</span></td>");

    // 设置商品是否下架 - find方法是向下查找匹配元素
    td6.find(":checkbox").prop("checked", (p.productStatus == 0));
    ;

    // 第二：创建 tr 节点
    var tr = $("<tr></tr>");

    // 第三：把 td 节点，添加到tr节点中
    tr.append(td1).append(td2).append(td3).append(td4).append(td5).append(td6).append(td7);

    // 第四：把 tr节点 添加 table节点中
    table.append(tr);
}

/**
 * 初始化年份
 */
function initYear() {
    // 第一：获取当前年份
    var now = new Date();
    var year = now.getFullYear();

    // 第二：创建 option 节点
    for (i = year - 5; i <= year + 5; i++) {
        // var option = $("<option value='"+i+"' " + (year==i?'selected':'') + ">"+i+"</option>")
        var option = $("<option value='" + i + "'>" + i + "</option>")
        // 第三：把 option 节点，添加到 select 节点中
        $("#year").append(option);
    }

    // 第四：默认选中当前年份
    $("#year option").filter(function (index) {
        return $(this).text() == year;
    }).prop("selected", true);
}

/**
 * 初始化月份
 */
function initMonth() {
    // 第一：获取当前月份
    var now = new Date();
    var currentMonth = now.getMonth() + 1;
    currentMonth = currentMonth < 10 ? '0' + currentMonth : currentMonth;

    // 第一：循环12次，创建 option 节点
    for (var i = 1; i <= 12; i++) {

        var m = i < 10 ? '0' + i : i;

        var option = $("<option value='" + m + "'>" + m + "</option>");
        // 第二：把 option 节点，添加到 select节点中
        $("#month").append(option);
    }

    // 第三：默认选中当前月份
    $("#month option").filter(function (index) {
        return $(this).text() == currentMonth;
    }).prop("selected", true);
}

/**
 * 根据年份和月份，计算日期天数
 * @param year
 * @param month
 * @returns {number}
 */
function getDay(year, month) {

    var day = 28;

    switch (month * 1) {
        case 1:
        case 3:
        case 5:
        case 7:
        case 8:
        case 10:
        case 12:
            day = 31;
            break;
        case 4:
        case 6:
        case 9:
        case 11:
            day = 30;
            break;
        default:
            if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
                day = 29;
            }
    }

    return day;
}

/**
 * 初始化日期
 * @param year
 * @param month
 */
function initDay(year, month) {
    // 第一：获取当前日期
    var now = new Date();
    var currentDate = now.getDate();

    // 第二：计算天数
    var day = getDay(year, month);

    // 第三：循环遍历天数，创建 option 节点
    for (var i = 1; i <= day; i++) {
        var riQi = i < 10 ? '0' + i : i;
        var option = $("<option value='" + riQi + "'>" + riQi + "</option>");
        // 第四：添加到 select 节点
        $("#day").append(option);
    }

    // 第四：默认选中当前日期
    $("#day option").filter(function () {
        var riQi = currentDate < 10 ? '0' + currentDate : currentDate;
        return $(this).text() == riQi;
    }).prop("selected", true);
}

/**
 * 年份切换
 */
$("#year").change(function(){
    // 第一：删除日期下拉列表框中的所有日期
    $("#day option").remove() ;

    // 第二：获取当前选中的年份和月份
    var year = $("#year option:selected").text() ;
    var month = $("#month option:selected").text() ;

    initDay(year,month) ;
}) ;

/**
 * 月份切换
 */
$("#month").change(function(){
    // 第一：删除日期下拉列表框中的所有日期
    $("#day option").remove() ;

    // 第二：获取当前选中的年份和月份
    var year = $("#year option:selected").text() ;
    var month = $("#month option:selected").text() ;

    initDay(year,month) ;
}) ;


/**
 * 查询所有的商品
 */
function queryAllProduct() {
    $.ajax({
        url: 'product_list.do',
        method: 'get',
        // data:?,
        dataType: 'json',
        success: function (res) {
            // DOM 操作 - ResponseData
            $.each(res.data, function (index, p) {
                createTr($("#tbl tbody"), p);
            });
        }
    });
}


// 页面准备就绪时
$(function(){
    // 1.初始化页面中的年、月、日
    initYear() ;
    initMonth() ;
    var now = new Date() ;
    var year = now.getFullYear();
    var month = now.getMonth() + 1 ;

    initDay(year,month) ;

    // 2.查询所有的商品列表
    queryAllProduct() ;
}) ;

/**
 * 清空表单
 */
function clearForm() {
    $("#pid").val("") ;
    $("#name").val("");
    $("#type option:eq(0)").prop("selected",true) ;
    // $("#type").find("option").eq(0).prop("selected",true)
    $("#price").val("");

    initYear() ;
    initMonth() ;
    let now = new Date() ;
    let year = now.getFullYear();
    let month = now.getMonth() + 1 ;
    initDay(year,month) ;

    $("input[name=isDown]:eq(0)").prop("checked",true)

}


// 添加商品信息
$("#btnAdd").click(function (){
    // let formData = $("#frm").serialize();
    $.ajax({
        url:'product_add.do',
        method:'post',
        data:$("#frm").serialize(),
        dataType:'json',
        success:function( res ){
            alert(res.msg) ;
            console.log(res.data) ;

            if(res.code == 200) {
                clearForm() ;
                createTr($("#tbl tbody"),res.data) ;
                $("#pid").focus() ;
            }
        },
        error:function() {
            console.log("服务器发生异常，请联系管理员：110")
        }
    }) ;
}) ;

// 删除商品信息 - 注意：动态生成节点，必须使用on方法实现事件的绑定
$("#tbl").on("click",".spanDel",function(){

    if(confirm("您真要删除码？")) {

        // 1.获取当前要删除的行
        let delTr = $(this).closest("tr") ;

        // 2.获取删除商品的编号
        let pid = $(this).closest("tr").find("td").eq(1).text() ;

        // 3.定义传递到服务器的数据（两种方法）
        // let data = {productId:pid } ;
        let data = "productId=" + pid ;

        // 4.向服务器发起 get 请求，实现商品的删除
        $.get('product_delete.do', data , function( res ){
            alert(res.msg) ;

            if(res.code==200) {
                // DOM操作 - 删除当有行
                delTr.remove() ;
            }
        }, 'json');
    }
}) ;



/**
 * 全选
 */
$("#checkAll").click(function(){
    // 获取全选复选框的状态
    let checkAllState = $("#checkAll").prop("checked") ;

    // 设置各行复选框的状态与全选复选框的状态一致
    $(".ckAll").prop("checked",checkAllState) ;
}) ;

// 批量删除
$("#btnDels").click(function (){


    if(confirm("您真要删除选中的数据码？")) {
        // 1.获取选中的复选框的商品编号 - productIds=102&productIds=103
        let data = $(".ckAll:checked").serialize() ;

        // 2.发起异步 get 请求
        $.get('product_deletes.do', data, function( res ){
            console.log( res ) ;
        }, 'json');
    }
}) ;





