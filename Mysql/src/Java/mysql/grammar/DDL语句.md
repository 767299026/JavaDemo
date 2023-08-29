DDL 定义语言，即 Data Definition Language 数据定义语言。用于定义和管理数据库对象，如表(Table)、索引(Index)、约束(Constraint) 等。

DDL语句主要包括创建、 修改和删除数据库对象的命令。

以下命令在Navicat查询中测试:

``` sql
#创建数据库
CREATE DATABASE DDLTest;
```

``` sql
#删除数据库
DROP DATABASE DDLTest;
```

``` sql
#在数据库新建表
CREATE TABLE employees (
    `id` INT,
    `name` VARCHAR(50),
    `age` INT,
    `email` VARCHAR(50),
    PRIMARY KEY (id)
);
```

``` sql
#给表添加字段
ALTER TABLE employees
ADD `salary` DECIMAL(10,2);
```

``` sql
#修改字段的数据类型
ALTER TABLE employees
MODIFY COLUMN `age` INT;
```

``` sql
#删除字段
ALTER TABLE employees
DROP COLUMN `salary`;
```

``` sql
#修改表名
ALTER TABLE employees
RENAME TO staff;
```

``` sql
#删除表
DROP TABLE employees;
```

``` sql
#给某个字段创建索引
CREATE INDEX idx_name ON employees (name);
```

``` sql
#删除索引
DROP INDEX idx_name;
```

``` sql
#创建视图
CREATE VIEW age_view AS
SELECT * FROM employees WHERE age > 25;
```

``` sql
#删除视图
DROP VIEW age_view;
```

``` sql
#创建约束(此处为唯一约束)
ALTER TABLE employees
ADD CONSTRAINT uc_employee_email UNIQUE (email);
```

``` sql
#删除约束
ALTER TABLE employees
DROP CONSTRAINT uc_employee_email;
```