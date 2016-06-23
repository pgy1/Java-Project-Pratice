

#注意事项

###1.通过Maven获取项目lib包

###2.数据库连接配置[地址](https://github.com/pgy1/Java-Project-Pratice/blob/master/SpringMVCHibernate1.1/src/main/resources/spring-bean-config/database.properties)

database.properties
```java
mysql.driver = com.mysql.jdbc.Driver
mysql.url = jdbc:mysql://localhost:3306/pgy
mysql.username = admin
mysql.password = admin
```

###3.数据库创建

-数据库名pgy

-数据表文件pgy.sql

###4.Spring Security

-增加框架安全模块

--相关文件
```java
applicationContext-security.xml 安全配置xml
GrantedAuthorityImpl.java 授权
UserDetailsServiceImpl.java 数据库用户名密码查询
```

###5.状态设计模式

我的思路是把数据放到具体的状态class中处理，<br />
Controller只需要调用StateContext上下文切换状态，<br />
就可以完成不同的状态的工作任务。

```java
CommonBean.java 公共数据结构
State.java 状态接口
StateContext.java 状态上下文管理
NewState.java 新增状态
CheckState.java 审核状态
SendState.java 发送状态
StateController.java 状态控制器
```

###6.增加单元测试的原文件夹
在test中测试各种即将使用的技术和验证程序的正确性


######作者pengguangyu[博客](http://boke.iflsy.com/)
