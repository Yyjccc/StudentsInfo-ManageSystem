# 后端接口文档

统一返回数据格式
```
"code": 状态码,
"info":{
"data": 数据,
("token": 登录凭证)

}     
"ok" : 操作是否成功
}
```

- ## 登录
1. 用户登录  /api/login  
   - 请求方式 POST
   - 请求参数：username:用户名;password:密码


- ## 学生基本信息数据操作
#### GET请求
1. 获取所有 /api/students/getAll
#### POST请求
取值:
   - value:["StudentId","name","sex","classId","department","birthday","nativePlace"]
2. 查询 /api/students/query
    - 请求参数
      - type:根据什么字段查询 
      - value:字段的值

3. 增加 /api/students/insert
   - 请求参数
     - StudentId:学号(int)
     - name:姓名
     可选参数:
       - sex:性别
       - classId:班级(int)
       - department:学院
       - birthday:生日(DATE:yyyy-mm-dd)
       - nativePlace:籍贯

4. 修改 /api/students/update
   - 请求参数
     - StudentId
     - type
     - value

   5. 删除 /api/students/delete
      - 请求参数
        - StudentId

- ## 学生行为数据操作
POST请求
参数取值:
- ActionType:["change","reward","punishment"]
- type:["StudentId","id","recTime","levels","description","enable","change"]

  1. 获取所有信息 /api/action/getAll
     - 请求参数
       - ActionType
     
  2. 查询 /api/action/query
     - 请求参数
       - ActionType
       - type
       - value
  3. 增加 
  - 变更 /api/action/insert/change
    - 请求参数
      - StudentId
      - changeId:变更代码(int)
      可选参数
      - recTime:记录时间(DATE:yyyy-mm-dd)
      - description:描述
  - 奖励 /api/action/insert/reward
    - 请求参数
      - StudentId
      - levels:级别(int)
      可选参数:
      - recTime
      - description
    
  - 处分 /api/action/insert/punishment
    - 请求参数
    - StudentId
    - levels
    - enable:是否生效(bool)
    可选参数
    - recTime
    - description
    
  4. 修改 /api/action/update
   - 请求参数
     - ActionType
     - id(int)
     - type
     - value

  5. 删除 /api/action/delete
   - 请求参数
     - ActionType
     - id

