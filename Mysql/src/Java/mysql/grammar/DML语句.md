DML 定义语言，即 Data Manipulation Language 数据操作语言。用于操作数据库中的数据，包括插入(INSERT)、更新(UPDATE)、删除(DELETE)和查询(SELECT)数据。

以下命令在Navicat查询中测试:

``` sql
#插入单行数据
INSERT INTO employees (id,name,age,email,salary)
VALUES(1,'小明',25,'123456@qq.com',6500);
```

``` sql
#插入多行数据
INSERT INTO employees (id,name,age,email,salary)
VALUES(2,'小红',22,'234567@qq.com',5500),
      (3,'小张',31,'345678@qq.com',9000);
```

``` sql
#更新单个字段数据
UPDATE employees
SET salary = 7000
WHERE name = '小明';
```

``` sql
#更新多个字段数据
UPDATE employees
SET salary = 6000,
    age = 24
WHERE name = '小红';
```

``` sql
#查询全部数据
SELECT id,name,age,email,salary
FROM employees;
```

``` sql
#查询指定字段数据
SELECT id,name,salary
FROM employees;
```

``` sql
#查询特定条件数据
SELECT id,name,age,salary
FROM employees
WHERE salary > 6000
ORDER BY age DESC;
```

``` sql
#删除数据
DELETE FROM employees
WHERE name = '小张';
```