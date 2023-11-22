## 学生信息管理系统(数据库课程设计)

湖南科技大学数据库课程设计




<i>说明：应为经历有限，只完成了基本的功能，大部分功能还未实现完善，刚好是运用刚学了的内容，本人不善于写前端，所以界面不是很美观</i>

### 介绍
技术架构
采用B/S架构，采用了前后端分离的模式

- 前端:vue框架、element-plus
- 后端:springboot、springmvc、mybatis
- 数据库：mysql


<b>环境要求： java 17</b>  
### 源码

```
- src
    - java :java后端源码
        -studentsystem
            -src
                -main
                    -resources
                        -application.properties springboot配置文件
                        -mybatis.xml mybatis配置文件
    - vue :前端vue项目源码
    - test.sql:建立数据库(数据库名要为test)
```

在上面的配置文件中配置好数据库连接

下载源码：分别启动里面的前端项目和后端项目

运行test.sql文件，执行里面的sql语句





### 一键使用

下载Release包


运行

```
java -jar 下载的jar包文件名
```

默认开启端口:13355

test.sql 一键执行里面

sql语句

### 未完善功能
(主要是后端)
- 对学生信息的个人所有详细信息的操作
- 未完善授权登录与用户权限限制
- 未对定义代码进行替换(没有多表查询)
- 未使用对使用用户信息的修改与展示(头像、用户名与密码)
- 未实现模糊搜索
- 未实现对数据上传与导出的功能
- 界面缺乏主页
- 未设计数据分析的功能并缺乏数据可视化
- 缺乏异常处理机制
- 缺乏数据库自动初始化创建
- 缺乏用户对项目的设置模块与界面