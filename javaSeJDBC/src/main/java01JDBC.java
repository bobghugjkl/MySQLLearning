package main;

import com.mysql.cj.protocol.Resultset;

import java.sql.*;

public class java01JDBC {

    private static String driver="com.mysql.cj.jdbc.Driver";
    private static String url="jdbc:mysql://localhost:3306/scott?useSSL=false&useUnicode=true&characterEncoding=UTF8&serverTimezone=GMT&allowPublicKeyRetrieval=true";
    private static final String user = "root";
    private static final String password = "WASDijkl15963";
    public static void main(String[] args) throws ClassNotFoundException, SQLException {


    }

    private static void DQL() throws ClassNotFoundException, SQLException {
//        查询
        //加载驱动
        Class.forName(driver);
        //创建链接
        Connection connection = DriverManager.getConnection(url,user,password);
        //创建SQL语句
        Statement statement = connection.createStatement();
        //声明sql
        String sql = "select * from emp";
        //获取结果
        ResultSet resultSet = statement.executeQuery(sql);
        //打印result
        System.out.println(resultSet);//是对象哈希
        //打印数据
        while (resultSet.next()){
            //获取数据
            int empno = resultSet.getInt("empno");
            String ename = resultSet.getString("ename");
            String job = resultSet.getString("job");
            int mgr = resultSet.getInt("mgr");
            Date hiredate = resultSet.getDate("hiredate");
            int sal = resultSet.getInt("sal");
            double comm = resultSet.getDouble("comm");
            int deptno = resultSet.getInt("deptno");
            System.out.println(empno+"\t"+ename+"\t"+job+"\t"+mgr+"\t"+hiredate+"\t"+sal+"\t"+comm+"\t"+deptno);
        }
    }

    private static void DML() throws ClassNotFoundException, SQLException {
//        数据库的增删改
        //加载驱动
        Class.forName(driver);
        //创建链接
        Connection connection = DriverManager.getConnection(url,user,password);
        //创建sql可以发送sql语句
        Statement statement = connection.createStatement();
        //声明一个sql语句
        String sql = "update emp set sal = 8888 where empno = 7788";
        //执行sql获取结果集合
        int rows = statement.executeUpdate(sql);
        System.out.println("受影响的行数："+ rows);
    }
}
