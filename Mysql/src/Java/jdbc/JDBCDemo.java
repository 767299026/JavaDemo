package Java.jdbc;

import java.sql.*;

//通过JDBC连接数据库
public class JDBCDemo {
    //这里写主方法抛出异常，以免try-catch代码块太多影响读码
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //这里已经设置好了项目依赖 根据本机mysql环境 mysql:mysql-connector-java:8.0.32
        //加载驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        //建立链接
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf8&useSSL=false", "root", "root");

        //创建sql查询语句
        String sql1 = "select Host, user from user;";
        //创建Statement对象(存在sql注入问题)
        Statement statement = connection.createStatement();
        //执行查询语句
        ResultSet resultSet1 = statement.executeQuery(sql1);
        //遍历查询结果
        System.out.println("查询当前数据库全部用户: ");
        int i = 1;
        while (resultSet1.next()) {
            Object host = resultSet1.getObject("Host");
            Object name = resultSet1.getObject("User");
            System.out.println("第 " + i + " 位用户: " + name + "@" + host);
            i++;
        }

        //创建sql查询语句
        String sql2 = "select Host, User from user where User = ?;";
        //创建PreparedStatement对象(预编译解决sql注入问题)
        PreparedStatement preparedStatement = connection.prepareStatement(sql2);
        //设置占位符参数
        preparedStatement.setObject(1, "root");
        //执行查询语句
        ResultSet resultSet2 = preparedStatement.executeQuery();
        //遍历查询结果
        System.out.println("查询当前数据库用户名为'root'的用户: ");
        int j = 1;
        while (resultSet2.next()) {
            Object host = resultSet2.getObject("Host");
            Object name = resultSet2.getObject("User");
            System.out.println("第 " + j + " 位 'root' 用户: " + name + "@" + host);
            j++;
        }

        //关闭资源
        resultSet1.close();
        statement.close();
        resultSet2.close();
        preparedStatement.close();
        connection.close();
    }
}