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