package com.xxxx;

import java.sql.*;
import java.util.Scanner;

public class My {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/crm?useSSL=false&useUnicode=true&characterEncoding=UTF8&serverTimezone=GMT&allowPublicKeyRetrieval=true";
    private static final String USER = "root";
    private static final String PASSWORD = "WASDijkl15963";
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Q1();

    }

    private static void Q1() throws ClassNotFoundException, SQLException {
//        验证用户名唯一
        System.out.println("请输入您的用户名");
        String username = scanner.next();
        String sql = "select * from t_user where username = '"+username + "'";
        Class.forName(DRIVER);
        Connection connection = DriverManager.getConnection(URL,USER,PASSWORD);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        if (resultSet.next()){
            System.out.println("您的用户名【"+username+"】已经被占用!");
        }else{
            System.out.println("可以使用");
        }
        resultSet.close();
        statement.close();
        connection.close();
    }
}
