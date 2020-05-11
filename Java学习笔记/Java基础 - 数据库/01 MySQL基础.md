# **MySQL 基础**
## 主要内容
- SQL 语句分类
- DDL 操作数据库，表
- DML 增删改表中数据
- DQL 查询表中的记录
- DCL 管理用户，授权

win+R 输入CMD打开运行窗口
> 分号结尾，建议大写
* 登录： `mysql -uroot -proot`
* 登出： `quit`
* 查看全部数据库的名称： `show databases;`
* 单行注释：`-- 注释内容` ； `#注释内容`
* 多行注释：`/* 多行注释内容 */`
---
## **第一章 SQL 语句分类**
1. DDL 数据定义语言 -> 建库，建表（操作数据库，表）
    - 关键字：`create` , `drop` , `alter` 等
2. DML 数据操纵语言 -> 对表中的记录操作增删改（增删改表中的数据）
    - 关键字：`insert` , `delete` , `update` 等
3. DQL 数据查询语言 -> 对表中的查询操作（查询表中的数据）
    - 关键字：`select` , `where` 等
4. DCL 数据控制语言 -> 对用户权限的设置（授权）
    - 关键字：`GRANT` , `REVOKE` 等
---
## **第二章 DDL：操作数据库，表**
## **2. 1 操作数据库：CRUD（增删改查）**
### C(Create):创建
* 创建数据库：`create database 数据库名;`
* 创建前判断是否存在该数据库（不存在则创建）：`create database if not exists 数据库名;`
* 创建数据库时指定编码：`create database 数据库名 character set 编码;`
* 创建指定编码数据库同时判断是否已存在：`create database if not exists 数据库名 character set 编码;`
### R(Retrieve):查询
* 查看全部数据库的名称： `show databases;` 
* 查询某个数据库的字符集（查询某个数据库的创建语句）：`show create database 数据库名;`
### U(Update):修改
- 修改数据库的编码：`alter database 数据库名 character set 编码;`
### D(Delete):删除
- 删除数据库：`drop database 数据库名;`
- 删除数据库，先判断是否存在该数据库（存在则删除）：`drop database if exists 数据库名;`
### 使用数据库:
- 查询当前正在使用的数据库：`select database();`
- 使用数据库：`use 数据库名;`

## **2. 2 操作表：CRUD(增删改查)**
### C(Create):创建
1. 语法：
~~~sql
	create table 表名(
		列名1 数据类型1,
		列名2 数据类型2,
		....
		列名n 数据类型n
	);
~~~
> 注意：最后一列，不需要加逗号 ,
* 常用数据类型：
	1. int：整数类型
	2. double:小数类型
		* score double(5,2)
	3. date:日期，只包含年月日，yyyy-MM-dd
	4. datetime:日期，包含年月日时分秒	 yyyy-MM-dd HH:mm:ss
	5. timestamp:时间戳类型	包含年月日时分秒	 yyyy-MM-dd HH:mm:ss	
		* 如果将来不给这个字段赋值，或赋值为null，则默认使用当前的系统时间，来自动赋值
	6. varchar：字符串
		* name varchar(20):姓名最大20个字符
		* zhangsan 8个字符  张三 2个字符

* 创建表：
~~~sql
	create table student(
		id int,
		name varchar(32),
		age int ,
		score double(4,1),
		birthday date,
		insert_time timestamp
	);
~~~
* 复制表：`create table 表名 like 被复制的表名;`	  	
### R(Retrieve)：查询
* 查询某个数据库中所有的表名称：`show tables;`
* 查询表结构：`desc 表名;`
### U(Update):修改
- 修改表名：`alter table 表名 rename to 新的表名;`
- 修改表的编码：`alter table 表名 character set 编码;`
- 添加一列：`alter table 表名 add 列名 数据类型;`
- 修改列名称 类型：\
	`alter table 表名 change 列名 新列名 新数据类型;`\
	`alter table 表名 modify 列名 新数据类型;`
- 删除列：`alter table 表名 drop 列名;`
### D(Delete):删除
* 删除表：`drop table 表名;`
* 删除表，先判断表是否存在：`drop table  if exists 表名;`
---
## **第三章 DML：增删改表中数据**
### **3. 1 添加数据：**
* 语法：`insert into 表名(列名1,列名2,...列名n) values(值1,值2,...值n);`
* 注意：
	1. 列名和值要一 一对应。
	2. 如果表名后，不定义列名，则默认给所有列添加值
		insert into 表名 values(值1,值2,...值n);
	3. 除了数字类型，其他类型需要使用引号(单双都可以)引起来

### **3. 2 删除数据：**
* 语法：`delete from 表名 [where 条件]`
* 注意：
	1. 如果不加条件，则删除表中所有记录。
	2. 如果要删除所有记录
		- `delete from 表名;` -- 不推荐使用。有多少条记录就会执行多少次删除操作
		- `TRUNCATE TABLE 表名;` -- 推荐使用，效率更高 先删除表，然后再创建一张一样的表。

### **3. 3修改数据：**
* 语法：`update 表名 set 列名1 = 值1, 列名2 = 值2,... where 条件;`
>注意：如果不加任何条件，则会将表中所有记录全部修改。
---
## **第四章 DQL：查询表中的记录**
查询表中所有数据：`select * from 表名;`
	
### **4. 1 语法：**
- `select` 字段列表
- `from` 表名列表
- `where` 条件列表
- `group by` 分组字段
- `having` 分组之后的条件
- `order by` 排序
- `limit` 分页限定


### **4. 2 基础查询：**
1. 多个字段的查询：`select 字段名1，字段名2... from 表名;`
> 注意：如果查询所有字段，则可以使用*来替代字段列表。
2. 去除重复：`distinct`
3. 计算列
	* 一般可以使用四则运算计算一些列的值。（一般只会进行数值型的计算）
	* `ifnull`(表达式1,表达式2)：null参与的运算，计算结果都为null
		* 表达式1：哪个字段需要判断是否为null
		* 如果该字段为null后的替换值。
4. 起别名：
	* `as`：as也可以省略
> 例：`select distinct 字段1，字段2，... from 表名;`
			
### **4. 3 条件查询：**
1. where子句后跟条件

	例： `select 字段列表 from 表名 where 条件;`
2. 运算符
	* `> 、< 、<= 、>= 、= 、<>`
	* `BETWEEN...AND`  
	* `IN`(集合) 
	* `LIKE`：模糊查询
	* 占位符：\
	`_ `:单个任意字符\
	`%`：多个任意字符
	* `IS NULL`  
	* `and`  或 `&&`
	* `or`  或 `||` 
	* `not`  或 `!`
---	
~~~sql
查询举例：表名 student，列：年龄 age ；英语 english

	-- 查询年龄大于20岁 或 等于20岁
	SELECT * FROM student WHERE age > 20;		
	SELECT * FROM student WHERE age >= 20;
			
	-- 查询年龄等于20岁
	SELECT * FROM student WHERE age = 20;
			
	-- 查询年龄不等于20岁
	SELECT * FROM student WHERE age != 20;
	SELECT * FROM student WHERE age <> 20;
			
	-- 查询年龄大于等于20 小于等于30
	SELECT * FROM student WHERE age >= 20 &&  age <=30;
	SELECT * FROM student WHERE age >= 20 AND  age <=30;
	SELECT * FROM student WHERE age BETWEEN 20 AND 30;
			
	-- 查询年龄22岁，18岁，25岁的信息
	SELECT * FROM student WHERE age = 22 OR age = 18 OR age = 25
	SELECT * FROM student WHERE age IN (22,18,25);
				
	-- 查询英语成绩为null
	SELECT * FROM student WHERE english = NULL; -- 不对的。null值不能使用 = （!=） 判断
	SELECT * FROM student WHERE english IS NULL;
				
	-- 查询英语成绩不为null
	SELECT * FROM student WHERE english  IS NOT NULL;
	
	-- 查询姓陈的有哪些？ like
	SELECT * FROM student WHERE NAME LIKE '陈%';

	-- 查询姓名第二个字是成的人	
	SELECT * FROM student WHERE NAME LIKE "_成%";
			
	-- 查询姓名是3个字的人
	SELECT * FROM student WHERE NAME LIKE '___'; -- 这里是三个 ‘-’
				
	-- 查询姓名中包含成的人
	SELECT * FROM student WHERE NAME LIKE '%成%';
~~~
### **4. 4 排序查询：**
* 语法：`order by` 子句
	- 例：`select * from 表名 order by 列名1 排序方式1, 列名2 排序方法2;`
* 排序方式：
	* `ASC`：升序，默认的。
	* `DESC`：降序。
* 注意：
	* 如果有多个排序条件，则当前边的条件值一样时，才会判断第二条件。

### **4. 5 聚合函数：**
将一列数据作为一个整体，进行纵向的计算。
1. `count`：计算个数
	1. 一般选择非空的列：主键
	2. `count(*)`
2. `max`：计算最大值
3. `min`：计算最小值
4. `sum`：计算和
5. `avg`：计算平均值	
- 例：`select count(列名) from 表名;`
* 注意：聚合函数的计算，排除null值。\
解决方案：
	1. 选择不包含非空的列进行计算
		- 例：`select count(主键) from 表名;`
	2. `IFNULL`函数
		- 例：`select count(ifnull(列名,0)) from 表名;`
### **4. 6 分组查询:**
1. 语法：`group by` 分组字段；
2. 注意：
	1. 分组之后查询的字段：分组字段、聚合函数
	2. `where` 和 `having` 的区别？
		1.`where` 在分组之前进行限定，如果不满足条件，则不参与分组。`having`在分组之后进行限定，如果不满足结果，则不会被查询出来
		2. `where` 后不可以跟聚合函数，`having`可以进行聚合函数的判断。
----
~~~sql
分组查询举例：表 student  列：性别 sex
	-- 按照性别分组。分别查询男、女同学的平均分
	SELECT sex , AVG(math) FROM student GROUP BY sex;
			
	-- 按照性别分组。分别查询男、女同学的平均分,人数
			
	SELECT sex , AVG(math),COUNT(id) FROM student GROUP BY sex;
			
	--  按照性别分组。分别查询男、女同学的平均分,人数 要求：分数低于70分的人，不参与分组
	SELECT sex , AVG(math),COUNT(id) FROM student WHERE math > 70 GROUP BY sex;
			
	--  按照性别分组。分别查询男、女同学的平均分,人数 要求：分数低于70分的人，不参与分组,分组之后。人数要大于2个人
	SELECT sex , AVG(math),COUNT(id) FROM student WHERE math > 70 GROUP BY sex HAVING COUNT(id) > 2;
			
	SELECT sex , AVG(math),COUNT(id) 人数 FROM student WHERE math > 70 GROUP BY sex HAVING 人数 > 2;
~~~
### **4. 7 分页查询：**
1. 语法：`limit` 开始的索引,每页查询的条数;
2. 公式：`开始的索引 = （当前的页码 - 1） * 每页显示的条数`
~~~sql
分页查询举例：表 student
	-- 每页显示3条记录 
	SELECT * FROM student LIMIT 0,3; -- 第1页
			
	SELECT * FROM student LIMIT 3,3; -- 第2页
			
	SELECT * FROM student LIMIT 6,3; -- 第3页
~~~
3. `limit` 是一个MySQL"方言"
---

## **第五章 DCL：管理用户，授权**
- DBA：数据库管理员
- DCL：管理用户，授权
### **5 .1 管理用户**
1. 添加用户：
    - 语法：`CREATE USER '用户名'@'主机名' IDENTIFIED BY '密码';`
2. 删除用户：
    - 语法：`DROP USER '用户名'@'主机名';`
3. 修改用户密码：
    ```sql
    UPDATE USER SET PASSWORD = PASSWORD('新密码') WHERE USER = '用户名';
    UPDATE USER SET PASSWORD = PASSWORD('abc') WHERE USER = 'lisi';
    
    SET PASSWORD FOR '用户名'@'主机名' = PASSWORD('新密码');
    SET PASSWORD FOR 'root'@'localhost' = PASSWORD('123');
    ```
    * mysql 中忘记了root用户的密码？
        1. cmd -- > `net stop mysql` 停止mysql服务
            * 需要管理员运行该cmd

        2. 使用无验证方式启动mysql服务：` mysqld --skip-grant-tables`
        3. 打开新的cmd窗口,直接输入`mysql`命令，敲回车。就可以登录成功
        4. `use mysql;`
        5. `update user set password = password('你的新密码') where user = 'root';`
        6. 关闭两个窗口
        7. 打开任务管理器，手动结束mysqld.exe 的进程
        8. 启动mysql服务
        9. 使用新密码登录。
4. 查询用户：
```sql
-- 1. 切换到mysql数据库
USE mysql;
-- 2. 查询user表
SELECT * FROM USER;
``` 
> 通配符： `% ` 表示可以在任意主机使用用户登录数据库

### **5 .2 权限管理：**
1. 查询权限：
```sql
-- 查询权限
SHOW GRANTS FOR '用户名'@'主机名';
SHOW GRANTS FOR 'lisi'@'%';
```
2. 授予权限：
```sql
-- 授予权限
grant 权限列表 on 数据库名.表名 to '用户名'@'主机名';

-- 给张三用户授予所有权限，在任意数据库任意表上
GRANT ALL ON *.* TO 'zhangsan'@'localhost';
```
3. 撤销权限：
```sql
-- 撤销权限：
revoke 权限列表 on 数据库名.表名 from '用户名'@'主机名';
REVOKE UPDATE ON db3.`account` FROM 'lisi'@'%';
```

---

> ps：仅供本人学习使用