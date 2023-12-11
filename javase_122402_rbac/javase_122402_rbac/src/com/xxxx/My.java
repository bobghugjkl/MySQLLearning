package com.xxxx;

import java.sql.*;
import java.util.Scanner;
import java.util.UUID;

public class My {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/crm?useSSL=false&useUnicode=true&characterEncoding=UTF8&serverTimezone=GMT&allowPublicKeyRetrieval=true";
    private static final String USER = "root";
    private static final String PASSWORD = "WASDijkl15963";
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        boolean loginFlag = false;
        System.out.println("请输入您的用户名");
        String username = scanner.next();
        System.out.println("请输入您的密码");
        String password = scanner.next();
        String sql = "select * from t_user u join t_role r using(rid) join t_rolepower using(rid) join t_power p using(pid) where username = '" + username + "' and password = '" + password + "'";
        System.out.println(sql);
        //JDBC
        ResultSet resultSet = jdbcDQL(sql);
        //打印
        while (resultSet.next()) {
            loginFlag = true;
            System.out.println("用户【" + username + "】的角色为【" + resultSet.getString("rolename") + "】，权限为【" + resultSet.getString("powername") + "】");
        }

        if (!loginFlag) {
            System.out.println("用户【" + username + "】【" + password + "】登录失败");
        }
    }

    private static void Q7() throws ClassNotFoundException, SQLException {
        System.out.println("请输入您的用户名");
        String username = scanner.next();
        String sql = "select u.username,r.rolename from t_user u join t_role r using(rid) where username = '"+username + "'";
        ResultSet resultSet = jdbcDQL(sql);
        if(resultSet.next()){
            System.out.println("用户【" + resultSet.getString("u.username") + "】的角色为【" + resultSet.getString("r.rolename") + "】");
            String sqlRole = "select * from t_role";
            ResultSet resultSetRole = jdbcDQL(sqlRole);
            System.out.println("可选角色为");
            while(resultSetRole.next()){
                System.out.println(resultSetRole.getString("rolename")+"\t");

            }
            System.out.println();
            System.out.println("================");
            System.out.println("输入要修改的角色名称");
            String rolename = scanner.next();
            String sqlUpdateRole = "update t_user set rid = (select rid from t_role where rolename = '"+rolename+"')where username = '"+username+"'";
            int rows = jdbcDML(sqlUpdateRole);
            //显示
            System.out.println(rows == 1 ? "修改用户[" + username + "]的角色为[" + rolename + "]成功" : "修改用户[" + username + "]的角色为[" + rolename + "]失败");
        } else {
            System.out.println("用户名【" + username + "】查无此人");
        }
    }

    private static void Q6() throws ClassNotFoundException, SQLException {
        //循环插入
        for (int i = 0; i < 50; i++) {
            //收集数据
            String uid = UUID.randomUUID().toString();
            String username = UUID.randomUUID().toString().substring(0, 8);
            String password = (int) (Math.random() * 900000 + 100000) + "";
            String phoneNumber = (int) (Math.random() * 90000000 + 10000000) + "";
            String rid = "111";

            //SQL
            String sql = "insert into t_user values('" + uid + "','" + rid + "','" + username + "','" + password + "'," + phoneNumber + ",now());";
            System.out.println(sql);

            //使用JDBC将数据更新到数据库
            int rows = jdbcDML(sql);

            //打印结果
            System.out.println(rows == 1 ? "数据[" + username + "]添加成功" : "数据[" + username + "]添加失败");
        }
    }

    private static void Q5() throws ClassNotFoundException, SQLException {
        System.out.println("请输入您的用户名");
        String username = scanner.next();
        System.out.println("请输入您的密码");
        String password = scanner.next();
        String sql = "select * from t_user where username = '"+username+"' and password = '"+password+"'";
        System.out.println(sql);
        ResultSet resultSet = jdbcDQL(sql);
        if(resultSet.next()){
            System.out.println("success");

        }else{
            System.out.println("lost");
        }
    }

    private static void Q4() throws ClassNotFoundException, SQLException {
        System.out.println("输入用户名");
        String username = scanner.next();
        System.out.println("请输入您的旧密码");
        String oldPassword = scanner.next();
        System.out.println("请输入您的新密码");
        String newPassword = scanner.next();
        String sql = "update t_user set password = '"+newPassword+"'where username='"+username+"'and password = '"+ oldPassword + "'";
        System.out.println(sql);
        int rows = jdbcDML(sql);
        System.out.println(rows==1?"用户"+username+"密码修改成果":"用户"+username+"失败");
    }

    private static void Q3() throws ClassNotFoundException, SQLException {
        System.out.println("输入您的用户名");
        String username = scanner.next();
        String sql = "delete from t_user where username = '"+username +"'";
        System.out.println(sql);
        int rows = jdbcDML(sql);
        System.out.println(rows == 1? "删除"+username+"成功":"删除"+username+"失败");
    }

    private static void Q2() throws ClassNotFoundException, SQLException {
        System.out.println("请输入您的用户名");
        String username = scanner.next();
        System.out.println("请输入您的密码");
        String password = scanner.next();
        System.out.println("请输入您的电话号码");
        String phoneNumber = scanner.next();
        String uid = UUID.randomUUID().toString();
        String rid = "111";

        String sql = "insert into t_user values('" + uid + "','" + rid + "','" + username + "','" + password + "'," + phoneNumber + ",now());";
        System.out.println(sql);
        int rows = jdbcDML(sql);
        System.out.println(rows == 1 ? "数据添加成功":"数据添加失败");
    }

    private static ResultSet jdbcDQL(String sql) throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);
        Connection connection = DriverManager.getConnection(URL,USER,PASSWORD);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        return resultSet;
    }

    private static int jdbcDML(String sql) throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);
        Connection connection = DriverManager.getConnection(URL,USER,PASSWORD);
        Statement statement = connection.createStatement();
        return statement.executeUpdate(sql);
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
