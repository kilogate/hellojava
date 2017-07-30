package com.kilogate.hello.java.javase.jdkapi.database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * JDBC 用法
 *
 * @author fengquanwei
 * @create 2017/7/30 22:45
 **/
public class JDBCUsage {
    public static void main(String[] args) {
        Properties properties = new Properties();
        try {
            try (InputStream inputStream = ClassLoader.getSystemResourceAsStream("jdbc.properties")) {
                properties.load(inputStream);
            }

            String driver = properties.getProperty("jdbc.drivers");
            String url = properties.getProperty("jdbc.url");
            String username = properties.getProperty("jdbc.username");
            String password = properties.getProperty("jdbc.password");

            // 加载驱动
//            System.setProperty("jdbc.drivers", driver);
            Class.forName(driver);

            // 获取连接
            try (Connection connection = DriverManager.getConnection(url, username, password)) {
                Statement statement = connection.createStatement();
//                statement.executeUpdate("CREATE TABLE tst (msg CHAR(20))");
                statement.executeUpdate("INSERT INTO tst VALUES ('Hello, MySQL!')");

                try (ResultSet resultSet = statement.executeQuery("SELECT * FROM tst")) {
                    while (resultSet.next()) {
                        System.out.println(resultSet.getString(1));
                    }
                }

//                statement.executeUpdate("DROP TABLE tst");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
