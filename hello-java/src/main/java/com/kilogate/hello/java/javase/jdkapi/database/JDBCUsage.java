package com.kilogate.hello.java.javase.jdkapi.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * JDBC 用法
 *
 * @author fengquanwei
 * @create 2017/7/30 22:45
 **/
public class JDBCUsage {
    public static Logger logger = LoggerFactory.getLogger(JDBCUsage.class);

    public static void main(String[] args) {
        List<String> sqls = new ArrayList<>();
//        sqls.add("CREATE TABLE `t_blp_image_info` ( `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '照片信息表', `biz_line_id` int(11) NOT NULL COMMENT '业务线 ID', `account_id` int(11) NOT NULL COMMENT '账户 ID', `image_type` int(4) NOT NULL COMMENT '照片类型', `image_url` varchar(255) DEFAULT NULL COMMENT '照片 URL', `example_code` int(4) DEFAULT NULL COMMENT '示例图片编码', `create_time` datetime DEFAULT NULL COMMENT '创建时间', `update_time` datetime DEFAULT NULL COMMENT '更新时间', PRIMARY KEY (`id`));");
//        sqls.add("INSERT INTO `t_blp_image_info` VALUES ('32', '1', '20', '1001', 'http://pic1.58cdn.com.cn/nowater/blp/n_v2c0277bc69ae74d8098c77f31b1d632cc.jpg', '0', '2017-07-23 12:11:36', '2017-07-23 12:11:36');\n");
//        sqls.add("INSERT INTO `t_blp_image_info` VALUES ('33', '1', '20', '1002', 'http://pic1.58cdn.com.cn/nowater/blp/n_v2e2d7ded932cd4efa8f4fe08c17e370e6.jpg', '0', '2017-07-23 12:11:38', '2017-07-23 12:11:38');\n");
//        sqls.add("INSERT INTO `t_blp_image_info` VALUES ('34', '1', '20', '1003', 'http://pic1.58cdn.com.cn/nowater/blp/n_v24299188c1176445d991cea2517b4c87e.jpg', '0', '2017-07-23 12:11:40', '2017-07-23 12:11:40');\n");
//        sqls.add("INSERT INTO `t_blp_image_info` VALUES ('35', '1', '21', '1001', 'http://pic1.58cdn.com.cn/nowater/blp/n_v28f3c3b1f52df45229eadc8c77ae84747.jpg', '0', '2017-07-23 17:28:48', '2017-07-23 17:28:48');\n");
//        sqls.add("INSERT INTO `t_blp_image_info` VALUES ('36', '1', '21', '1002', 'http://pic1.58cdn.com.cn/nowater/blp/n_v2b5a8c4b44f424446bd63cf3f879f388c.jpg', '0', '2017-07-23 17:28:52', '2017-07-23 17:28:52');\n");
//        sqls.add("INSERT INTO `t_blp_image_info` VALUES ('50', '1', '26', '1002', 'http://pic1.58cdn.com.cn/nowater/blp/n_v2220d10662e894fb39a5e1999730d079c.jpg', '0', '2017-07-26 16:44:43', '2017-07-26 16:44:43');\n");
//        sqls.add("INSERT INTO `t_blp_image_info` VALUES ('53', '1', '19', '1002', 'http://pic1.58cdn.com.cn/nowater/blp/n_v230a4ef2e24534d94b6cb5441cf45b745.jpg', '0', '2017-07-26 20:02:42', '2017-07-26 20:02:42');\n");
//        sqls.add("INSERT INTO `t_blp_image_info` VALUES ('55', '1', '29', '1001', 'http://pic1.58cdn.com.cn/nowater/blp/n_v2d3bd49513dc44d47acaba93551acec86.jpg', '0', '2017-07-28 11:13:12', '2017-07-28 11:13:12');\n");
//        sqls.add("INSERT INTO `t_blp_image_info` VALUES ('57', '1', '29', '1003', 'http://pic1.58cdn.com.cn/nowater/blp/n_v212ac6b1eab8e44fd9b0c5b8f480187fb.jpg', '0', '2017-07-28 11:13:19', '2017-07-28 11:13:19');\n");
        sqls.add("SELECT * FROM t_blp_image_info;");

        try {
            // 获取连接
            try (Connection connection = getConnection()) {
                // 创建语句
                System.out.println("\n\n=================================== 测试执行 SQL ===================================");
                Statement statement = connection.createStatement();
                for (String sql : sqls) {
                    // 执行 SQL
                    boolean isResult = statement.execute(sql);
                    if (isResult) {
                        ResultSet resultSet = statement.getResultSet();
                        showResultSet(resultSet);
                    } else {
                        int updateCount = statement.getUpdateCount();
                        System.out.println(updateCount + " rows updated");
                    }
                }

                // 测试预备语句
                System.out.println("\n\n=================================== 测试预备语句 ===================================");
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM t_blp_image_info WHERE account_id = ?;");
                preparedStatement.setInt(1, 20);
                ResultSet resultSet = preparedStatement.executeQuery();
                showResultSet(resultSet);

                // 读写 LOB
                System.out.println("\n\n=================================== 测试读写 LOB ===================================");
                PreparedStatement blobInsertPreparedStatement = connection.prepareStatement("INSERT INTO tst VALUES (?, ?);");
                blobInsertPreparedStatement.setString(1, "picture");
                blobInsertPreparedStatement.setBytes(2, Files.readAllBytes(Paths.get("/Users/kilogate/Pictures/IdcardBack.jpg")));
                blobInsertPreparedStatement.executeUpdate();

                PreparedStatement blobSelectPreparedStatement = connection.prepareStatement("SELECT * FROM tst WHERE msg = ?");
                blobSelectPreparedStatement.setString(1, "picture");
                ResultSet picResultSet = blobSelectPreparedStatement.executeQuery();
                while (picResultSet.next()) {
                    byte[] picBytes = picResultSet.getBytes(2);
                    Files.write(Paths.get("/Users/kilogate/Pictures/new.jpg"), picBytes);
                }
            }
        } catch (IOException e) {
            logger.error("读写文件异常", e);
        } catch (SQLException e) {
            for (Throwable t : e) {
                logger.error("执行 SQL 命令异常", t);
            }
        }
    }

    /**
     * 获取连接
     */
    public static Connection getConnection() {
        Connection connection = null;

        // 读取配置
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
            connection = DriverManager.getConnection(url, username, password);
        } catch (IOException e) {
            logger.error("读取配置异常", e);
        } catch (ClassNotFoundException e) {
            logger.error("加载驱动异常", e);
        } catch (SQLException e) {
            for (Throwable t : e) {
                logger.error("执行 SQL 命令异常", t);
            }
        }

        return connection;
    }

    /**
     * 展示结果集
     */
    public static void showResultSet(ResultSet resultSet) {
        try {
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int columnCount = resultSetMetaData.getColumnCount();

            for (int i = 1; i <= columnCount; i++) {
                if (i > 1) {
                    System.out.print(", ");
                }
                System.out.print(resultSetMetaData.getColumnLabel(i));
            }
            System.out.println();

            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    if (i > 1) {
                        System.out.print(", ");
                    }
                    System.out.print(resultSet.getString(i));
                }
                System.out.println();
            }
        } catch (SQLException e) {
            for (Throwable t : e) {
                logger.error("执行 SQL 命令异常", t);
            }
        }
    }
}
