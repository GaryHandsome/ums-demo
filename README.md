## IDEA工具实现用户登录（复习）

<h6 style="color:red">非常重要，反复练习</h6>
<h6 style="color:blue">具体操作步骤：</h6>
第一：创建Web项目



第二：编写前端页面（HTML、CSS、JS、JQ）



第三：编写后端程序
1、创建数据库及相关的数据表

```mssql
create database ums ;
use ums ;

create table userinfo
(
    id int identity(1,1) primary key ,
    username varchar(50) ,
    password char(6),
    user_age int ,
    user_sex char(2) default '男',
    weight float
) ;

insert into userinfo(username,password,user_age,user_sex,weight)
    values ('admin','123456',18,'男',60.5) ;

insert into userinfo(username,password,user_age,user_sex,weight)
    values ('zhangsan','123456',28,'男',70.5) ;

insert into userinfo(username,password,user_age,user_sex,weight)
    values ('lisi','123456',38,'女',20.5) ;
```



2、编写实体对象

> 作用：封装数据、数据传递

**命名规范：xxx.xxx.entity.XxxXxx**

    ctrl + alt + l : 代码格式化
    ctrl + alt + o : 清除多余的包
    alt + insert : 生成代码
    alt + enter : 万能键（代码提示）
    alt + shift + 向上|向下 ：移动代码
    ctrl + d ：复制当前选中代码
    ctrl + y : 删除当前选中代码



3、编写DAO接口

> DAO（Data Access Object） : 数据访问对象 - 操作数据库(CRUD) - 服务于业务

**命名规范：xxx.xxx.dao.XxxXxxDao**



4、编写DAO接口实现类
**命名规范：xxx.xxx.dao.impl.XxxXxxDaoImpl**



5、编写业务(Service)接口
**命名规范：xxx.xxx.service.XxxXxxService**



6、编写业务(Service)接口的实现类
**命名规范：xxx.xxx.service.impl.XxxXxxServiceImpl**



7、编写Servlet（控制中心，建立前端页面和后台程序之间的联系）

**注意：在实际开发中,以上步骤不是固定不变的，而是灵活运用。**



## AJAX

### 1、概念

**异步** JavaScript 及 XML（Asynchronous JavaScript And XML）。它是一系列交互式网页应用技术的结合体，包含知识有：



1）基于XHTML和CSS标准的表示；



2）使用Document Object Model（DOM）进行动态显示和交互；



3）使用XMLHttpRequest与服务器进行异步通信；



4）使用JavaScript绑定一切



AJAX 是一种用于创建快速动态网页的技术。



异步请求的好处：页面不刷新，更新数据 - 增强用户体验



通过在后台与服务器进行少量数据交换，AJAX 可以使网页实现异步更新。这意味着可以在不重新加载整个网页的情况下，对网页的某部分进行更新；而传统的网页（不使用 AJAX）如果需要更新内容，必需重载整个网页面





### 2、JSON

#### 1）概念

```js
// 定义 Javascript 对象 - 文本标记法
var stu = {
    name:'zs',
    age:18
}

// 字符串 - JavaScript对象的表示 - 实现后端与前端之间的数据转递
var json = "{'name':'zs','age':18}" ;
```



JSON 指的是 JavaScript 对象表示法（JavaScript Object Notation）



JSON 是**轻量级**的文本数据交换格式，相对于 XML而言



JSON 独立于语言



JSON 具有自我描述性，更易理解



JSON 是存储和交换文本信息的语法。类似 XML，但JSON 比 XML 更小、更快，更易解析。



#### 2）作用

实现后端与前端之间的数据转递



#### 3）语法

JSON 语法是 JavaScript 对象表示法语法的子集。

-  数据在名称 / 值对中 

-  数据由逗号分隔 

-  花括号保存对象 

-  方括号保存数组 

```javascript
var 对象 = {
    属性名称1:值 ,
    ...
    属性名称N:值 
}
```

JSON 值可以是：

-  数字（整数或浮点数） 

-  字符串（使用双引号或单引号） 

-  布尔值（true 或 false） 

-  数组（在方括号中） 

-  对象（在花括号中） 

-  null / undefind



JSON 文件 - JSON数据根据需求，也可以定义在一个文件中

-  JSON 文件的文件类型是 ".json" 

-  JSON 文本的 MIME 类型是 "application/json" 
  - text/html
  - text/css
  - text/javascript



### 3、实现

#### 1）原生实现

1.1）XMLHTTPRequest对象的相关属性方法



1.2）操作步骤

第一：创建 XMLHttpRequest 对象

```javascript
 var req = new XMLHttpRequest();
```

默认情况下，客户端可以通过以下两个方法发起同步请求：

- 超链接
  - a标签
  - JS中location.href
- 表单提交



第二：初始化HTTP请求参数

> 调用 open() 方法

```js
req.open(method,uri,async,[username],[password]))
```

-  metod:请求方式，get,post,put,delete或head 

-  uri：请求的服务器地址 

-  async:设置异步或同步，true(默认) / false 

-  根据需要可传username和password给服务器进行用户验证 



第三：编写服务器端程序 - Servlet 

- 处理同步请求的Serlvet
  - 把响应的数据，设置在作用域对象中，并跳转到 JSP 页面中 
  - 然后，在JSP中使用 JSTL + EL 读取作用域数据
- 处理异步请求的Serlvet
  - 把响应数据，使用 PrintWriter 对象，以**字符串**的方式打印输出到页面（JSP、HTML）中
  - 使用 XMLHttpRequest 的responseText属性接收Servlet打印输出的**字符串**
  - 使用 DOM 操作 - 节点、样式



第四：发送请求 - 调用 send 方法

```js
req.send([data])
```

- data：表示向服务器传递的参数
  
  - 如果不传递，则使用 null 表示即可
  
  - GET请求 + 传参：
  
    ```js
    // get请求，并指定传递参数
    req.open("get","hello.do?name=zs&age=18",true) ;
    req.send();
    ```
  
  - POST请求 + 传参
  
    ```js
    // post请求
    req.open("post","hello.do",true) ;
    
    // 设置表单传输的编码格式（注意，必须在open方法之后设置）
    req.setRequestHeader("Content-type","application/x-www-form-urlencoded");
    
    // 语法：send(参数名１=值&参数名２=值,...,&参数名N=值)
    req.send("name=zing&age=18") ;
    ```
  
    









第五：设置回调函数 - 获取服务器响应的结果

```js
// 绑定 onreadystatechange 事件
req.onreadystatechange = function() {
    // readyState=4 表示完成请求响应这个过程
    // status=200 表示正常响应
   	if (req.readyState==4 && req.status==200) {
       
       	// DOM 操作
		document.getElementById("result").innerHTML = 
            // 获取服务器应用的数据
        	req.responseText ;
	} 
}
```





#### 2）JQ实现

2.1）$.ajax

```java
// 向服务器发起异步的请求
$.ajax({
    url:'服务器程序的URL地址',
    method:'请求方式（get、post）',
    data:'传递服务器的数据',
    dataType:'设置响应数据的类型，默认是text，可以设置为json',
    success:function( res ){
    // 成功响应的处理，其中参数res就是服务器响应回来的数据（JSON）
    },
    error:function() {
        // 服务器错误的响应的处理
    },....
}) ;
```

说明：

- data选项有两种方式传值

  - URL重写传递：参数名称1=值&参数名称2=值&...

  - 对象传递：`{属性名称1:值,...,属性名称N:值}`

  - 注：在JQ中，提供了一个方法`serialize()`，帮助我们把数据序列化字符串，便于提交给服务器。另外，此方法常用于表单，语法如下所示：

    ```js
    // 表单控件必须要有name属性
    $("#表单节点").serialize() ;
    ```

    



2.2）$.get

```js
// 向服务器发起一个异步的get请求
$.get(服务器程序URL地址, [传递服务器的数据], [成功响应的回调函数], [数据类型]);
```



2.3）$.post

```js
// 向服务器发起一个异步的post请求
$.post(url, [data], [callback], [type]);
```



### 4、延迟对象

1）发起多个异步请求且保证顺序执行

需求：同时向服务器发起多个异步请求，则响应的顺序是不能保证顺序执行！

如果我们希望发起的多个异步请求能顺序响应，怎么办呢？

- 方法一：使用同步请求，丢失异步请求的特性
- 方法二：回调函数的嵌套调用 - 回调地狱 - 不便于阅读，维护性差
- 方法三：使用延迟对象
  - then方法
  - $.when方法



2）延迟对象常用的API方法



3）自定义延迟对象

deferred对象可以应用在异步请求、同步请求，同时也可以应用在本地方法中。操作步骤如下：

```js
// 第一：定义一个返回 deferred 对象的方法
function wait() {
    // 1：创建延迟对象
    let dfd = $.Deferred()

    // 文本标记定义法
    let tasks = function(){
        console.log("任务执行完毕！");

        // 2：改变延迟对象的状态（三种） -- 业务逻辑进行判断
        // dfd.resolve();
        dfd.reject() ;
    };
    // 5秒后，调用 tasks 函数
    setTimeout(tasks,5000);

    // 3：返回延迟对象
    return dfd ;
};

// 第二：通过$.when方法使用延迟对象
$.when(wait()).done(function(){
    console.log("成功执行")
}).fail(function(){
    console.log("失败执行")
}) ;
```

- dtd.resolve()

  把deferred对象的未完成状态，改变为已完成状态，从而触发调用done方法

- dtd.reject()

  把deferred对象的未完成状态，改变为已失败状态，从而触发调用fail方法

当然，根据业务逻辑进行判断调用 



## 商品信息的管理

1、数据库

```mssql
create database pms ;

use pms ;

-- 产品信息表
create table product
(
	product_id varchar(50) primary key  not null ,			-- 产品编号
	product_name varchar(50) not null,							-- 产品名称
	product_type varchar(50)  not null ,							-- 产品分类
	product_price float not null,										-- 产品价格
	product_count int default 1,										-- 产品库存
	product_image varchar(50) default 'default.jpeg',	-- 产品图片
	product_date	datetime ,											-- 上货日期
	product_desc varchar(200) default '暂无描述',			-- 描述
	product_sale int default 0,										-- 销量
	product_status int  													-- 是否上架
) ;

-- 初始化数据
insert into product(product_id,product_name,product_type,product_price,product_count,product_image,product_date,product_desc,product_sale,product_status) 
values ('101','米家智能插座WIFI版','家电',39.5,100,'101.png','2013-01-07','101..',100,1) ;

insert into product(product_id,product_name,product_type,product_price,product_count,product_image,product_date,product_desc,product_sale,product_status)  
	values ('102','德尔玛多功能蒸汽清洁机','家电',59.5,100,'102.png','2015-11-01','102..',200,1) ;

insert into product(product_id,product_name,product_type,product_price,product_count,product_image,product_date,product_desc,product_sale,product_status)  
	values ('103','90分框体旅行箱','家居',200,100,'103.png','2013-09-04','103..',300,1) ;

insert into product(product_id,product_name,product_type,product_price,product_count,product_image,product_date,product_desc,product_sale,product_status)  
	values ('104','米家两六冰箱160L','家电',1999.5,100,'104.png','2018-04-03','104..',400,1) ;

insert into product(product_id,product_name,product_type,product_price,product_count,product_image,product_date,product_desc,product_sale,product_status)  
	values ('105','流浪地球CN171运兵车','玩具',199.5,100,'105.png','2019-03-02','105..',500,1) ;

insert into product(product_id,product_name,product_type,product_price,product_count,product_image,product_date,product_desc,product_sale,product_status)   
	values ('106','回力袜子男抗菌中筒袜秋冬新品情侣款','服装',42,100,'106.jpeg','2019-03-02','106..',500,1) ;
	
insert into product(product_id,product_name,product_type,product_price,product_count,product_image,product_date,product_desc,product_sale,product_status)  
	values ('107','周生生水波纹手链','首饰',1629,100,'107.jpeg','2017-06-14','107..',500,1) ;

insert into product(product_id,product_name,product_type,product_price,product_count,product_image,product_date,product_desc,product_sale,product_status)   
	values ('108','腾讯视频年卡+京东PLUS会员','硬件',148,100,'108.png','2019-06-24','108..',500,1) ;

insert into product(product_id,product_name,product_type,product_price,product_count,product_image,product_date,product_desc,product_sale,product_status)  
	values ('109','Dyson戴森 手持无线吸尘器 V7','家电',1199,100,'109.jpeg','2013-05-22','109..',500,1) ;

insert into product(product_id,product_name,product_type,product_price,product_count,product_image,product_date,product_desc,product_sale,product_status)  
	values ('110','京东京造 哥窑自动茶具套装','家居',519,100,'110.jpeg','2018-05-08','110..',500,1) ;

insert into product(product_id,product_name,product_type,product_price,product_count,product_image,product_date,product_desc,product_sale,product_status)  
	values ('112','鸭鸭佟丽娅同款工装羽绒服','服装',699,100,'112.jpeg','2022-11-02','112..',500,1) ;

insert into product(product_id,product_name,product_type,product_price,product_count,product_image,product_date,product_desc,product_sale,product_status)  
	values ('113','JBL T115TWS真无线耳机','家电',168,100,'113.jpeg','2022-01-01','113..',500,1) ;

insert into product(product_id,product_name,product_type,product_price,product_count,product_image,product_date,product_desc,product_sale,product_status)  
	values ('114','玖合 256GB SATA3 SSD固态硬盘','硬件',96,100,'114.jpeg','2012-12-12','114..',500,1) ;
```

