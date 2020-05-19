# 存储小能手

#### 介绍
一个使用Java编写的数据库工具jar包。

#### 软件架构
- 环境：JDK1.8及以上
- jar包管理：Maven


#### 安装教程

1. 下载发布的jar包
2. 将下载好的jar包导入classpath

#### 使用说明

1.  生成java.sql.Connection的实例化对象
2.  使用1得到的Connection实例化对象，获取ClientCommand的实例化对象。
``` java
    ClientCommand command = ClientCommand.build(connection);
```
3.  插入操作
``` java
    int resultCount = command.insert("insert into xxx (...) values (?,?,?...)", new Objcet[]{...}).execute().toResultCount();
```
4.  查询操作
``` java
   List<?> list = command.select("select * from xxx where a = ? and b = ?", new Object[]{a, b}).execute().toList();
```
5.  更新操作
``` java
    int resultCount = command.update("update xxx set a = ?, b = ? where c = ?", new Object[]{a, b, c}).execute().toResultCount();
```
6.  删除操作
``` java
    int resultCount = command.delete("delete from xxx where a = ? and b = ?", new Object[]{a, b}).execute().toResultCount();
```

#### 参与贡献
请联系我
- 邮箱：1042583309@qq.com