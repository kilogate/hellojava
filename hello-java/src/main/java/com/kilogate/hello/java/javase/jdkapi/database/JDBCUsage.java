package com.kilogate.hello.java.javase.jdkapi.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
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
        try {
            // SQL 命令执行
//            testSqlExecute();

            // 预备语句
//            testPrepare();

            // 读写 LOB
//            testLob();

            // 获取自动生成键
//            testGeneratedKeys();

            // 可滚动和可更新的结果集
//            testScrollUpdatable();

            // 被缓存的行集
//            testRowSet();

            // 元数据
//            testMetaData();

            // 事务
//            testTransaction();

            // 批量提交
            testBatchUpdate();
        } catch (IOException e) {
            logger.error("读写文件异常", e);
        } catch (ClassNotFoundException e) {
            logger.error("驱动程序不存在", e);
        } catch (SQLException e) {
            for (Throwable t : e) {
                logger.error("执行 SQL 命令异常", t);
            }
        }
    }

    /**
     * SQL 命令执行
     */
    public static void testSqlExecute() throws SQLException, IOException, ClassNotFoundException {
        System.out.println("\n\n=================================== 测试执行 SQL ===================================");

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

        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            for (String sql : sqls) {
                boolean isResult = statement.execute(sql);
                if (isResult) {
                    ResultSet resultSet = statement.getResultSet();
                    showResultSet(resultSet);
                } else {
                    int updateCount = statement.getUpdateCount();
                    System.out.println(updateCount + " rows updated");
                }
            }
        }
    }

    /**
     * 预备语句
     */
    public static void testPrepare() throws SQLException, IOException, ClassNotFoundException {
        System.out.println("\n\n=================================== 测试预备语句 ===================================");

        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM t_blp_image_info WHERE account_id = ?;");
            preparedStatement.setInt(1, 20);
            ResultSet resultSet = preparedStatement.executeQuery();
            showResultSet(resultSet);
        }
    }

    /**
     * 读写 LOB
     */
    public static void testLob() throws SQLException, IOException, ClassNotFoundException {
        System.out.println("\n\n=================================== 测试读写 LOB ===================================");

        try (Connection connection = getConnection()) {
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
    }

    /**
     * 获取自动生成键
     */
    public static void testGeneratedKeys() throws SQLException, IOException, ClassNotFoundException {
        System.out.println("\n\n=================================== 获取自动生成键 ===================================");

        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("insert into t_blp_image_info ( `biz_line_id`, `account_id`, `image_type`, `image_url`, `example_code`, `create_time`, `update_time`) values ( '1', '13', '1003', 'http://pic1.58cdn.com.cn/nowater/blp/n_v2806c200e5b8d4d6d84ef0e5792061e16.jpg', '0', '2017-07-31 11:33:04', '2017-07-31 11:33:04');", Statement.RETURN_GENERATED_KEYS);
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                int key = resultSet.getInt(1);
                System.out.println(key);
            }
        }
    }

    /**
     * 可滚动和可更新的结果集
     */
    public static void testScrollUpdatable() throws SQLException, IOException, ClassNotFoundException {
        System.out.println("\n\n=================================== 可滚动和可更新的结果集 ===================================");

        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet resultSet = statement.executeQuery("SELECT * FROM t_blp_image_info;");

            int type = resultSet.getType();
            int concurrency = resultSet.getConcurrency();

            resultSet.first();
            String first = resultSet.getString(1);
            resultSet.last();
            String last = resultSet.getString(1);
            resultSet.absolute(3);
            String third = resultSet.getString(1);
            resultSet.relative(-1);
            String second = resultSet.getString(1);
            resultSet.next();
            String next = resultSet.getString(1);
            resultSet.previous();
            String previous = resultSet.getString(1);

            resultSet.previous();
            resultSet.updateInt(1, 17);
            resultSet.updateRow();

            resultSet.moveToInsertRow(); // 移到插入行
            resultSet.updateInt(1, 100);
            resultSet.insertRow();
            resultSet.moveToCurrentRow(); // 移回当前行
        }
    }

    /**
     * 被缓存的行集
     */
    public static void testRowSet() throws SQLException, IOException, ClassNotFoundException {
        System.out.println("\n\n=================================== 被缓存的行集 ===================================");

        Properties properties = new Properties();
        try (InputStream inputStream = ClassLoader.getSystemResourceAsStream("jdbc.properties")) {
            properties.load(inputStream);
        }
        String url = properties.getProperty("jdbc.url");
        String username = properties.getProperty("jdbc.username");
        String password = properties.getProperty("jdbc.password");

        RowSetFactory rowSetFactory = RowSetProvider.newFactory();
        CachedRowSet cachedRowSet = rowSetFactory.createCachedRowSet();

        // 连接数据库
        cachedRowSet.setUrl(url);
        cachedRowSet.setUsername(username);
        cachedRowSet.setPassword(password);

        cachedRowSet.setCommand("SELECT * FROM t_blp_image_info WHERE id > ?;");
        cachedRowSet.setInt(1, 20);
        cachedRowSet.setPageSize(6);

        // 建立数据库连接，执行查询操作，填充行集，断开连接
        cachedRowSet.execute();

        // 展示行集
        showResultSet(cachedRowSet);

        // 展示下一页行集
        cachedRowSet.nextPage();
        showResultSet(cachedRowSet);

        // 修改行集中的数据
        cachedRowSet.first();
        cachedRowSet.updateInt(3, 2333);
        cachedRowSet.updateRow();

        // Can't call commit when autocommit=true
        // 解决方案一：url 后面加上 ?relaxAutoCommit=true
        // 解决方案二：connection.setAutoCommit(false);
        Connection connection = getConnection();
        connection.setAutoCommit(false);
        cachedRowSet.acceptChanges(connection);
    }

    /**
     * 元数据
     */
    public static void testMetaData() throws SQLException, IOException, ClassNotFoundException {
        System.out.println("\n\n=================================== 元数据 ===================================");

        try (Connection connection = getConnection()) {
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            ResultSet resultSet = databaseMetaData.getTables(null, null, null, new String[]{"TABLE"});
            while (resultSet.next()) {
                System.out.println(resultSet.getString(3));
            }
        }
    }

    /**
     * 事务
     */
    public static void testTransaction() throws SQLException, IOException, ClassNotFoundException {
        System.out.println("\n\n=================================== 事务 ===================================");

        Connection connection = getConnection();
        connection.setAutoCommit(false);
        Statement statement = connection.createStatement();

        statement.executeUpdate("UPDATE t_blp_image_info SET account_id = 110 WHERE id = 33"); // 执行第一条 SQL 命令
        Savepoint savepoint = connection.setSavepoint(); // 设置回滚保存点
        statement.executeUpdate("UPDATE t_blp_image_info SET account_id = 110 WHERE id = 34"); // 执行第二条 SQL 命令

        try {
            statement.executeUpdate("UPDATE t_blp_image_info SET account_sd = 110 WHERE id = 35"); //  执行第三条 SQL 命令（此 SQL 命令会报错）
        } catch (SQLException e) {
//                connection.rollback(); // 回滚
            connection.rollback(savepoint); // 回滚到保存点
            connection.releaseSavepoint(savepoint); // 释放掉保存点
        }

        statement.executeUpdate("UPDATE t_blp_image_info SET account_id = 110 WHERE id = 36");  // 执行第四条 SQL 命令

        connection.commit(); // 提交
    }

    /**
     * 批量提交
     */
    public static void testBatchUpdate() throws SQLException, IOException, ClassNotFoundException {
        System.out.println("\n\n=================================== 批量提交 ===================================");

        try (Connection connection = getConnection()) {
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();

            statement.addBatch("UPDATE t_blp_image_info SET account_id = 110 WHERE id = 62");
            statement.addBatch("UPDATE t_blp_image_info SET account_id = 110 WHERE id = 63");
            statement.addBatch("UPDATE t_blp_image_info SET account_id = 110 WHERE id = 64");
            statement.addBatch("UPDATE t_blp_image_info SET account_id = 110 WHERE id = 65");
            statement.addBatch("UPDATE t_blp_image_info SET account_id = 110 WHERE id = 66");

            int[] executeBatch = statement.executeBatch();
            System.out.println(Arrays.toString(executeBatch));

            connection.commit();
            connection.setAutoCommit(true);
        }
    }

    /**
     * 获取连接
     */
    public static Connection getConnection() throws IOException, ClassNotFoundException, SQLException {
        // 读取配置
        Properties properties = new Properties();
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
        return DriverManager.getConnection(url, username, password);
    }

    /**
     * 展示结果集
     */
    public static void showResultSet(ResultSet resultSet) throws SQLException {
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
    }
}