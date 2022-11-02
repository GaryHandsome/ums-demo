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











## 商品信息的管理

1、数据库

```mssql
create database pms ;

use pms ;

-- 产品信息表
create table product
(
	  product_id varchar(50) primary key  not null ,					-- 产品编号
	  product_name varchar(50) not null,								-- 产品名称
	  product_type varchar(50)  not null ,								-- 产品分类
	  product_price float not null,										-- 产品价格
	  product_count int not null default 1,								-- 产品库存
	  product_image varchar(50) default 'default.jpeg',					-- 产品图片
	  product_date	datetime default getdate(),							-- 上货日期
	  product_desc varchar(200) default '暂无描述',						 -- 描述
	  product_sale int,													-- 销量
	  product_status int 												-- 是否上架
) ;
```

