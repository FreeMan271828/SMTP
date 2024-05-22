package data_access;

import java.sql.*;

public class TableOper {
    /**
     * 创建用户表
     */
    public static void createUserTable(Connection connection) throws SQLException {
        Statement stmt = connection.createStatement();
        String sql = "CREATE TABLE IF NOT EXISTS user (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "name VARCHAR(255) NOT NULL," +
                "phone VARCHAR(20)," +
                "password VARCHAR(255)," +
                "email VARCHAR(255));";
        stmt.executeUpdate(sql);
        DatabaseMetaData dbMetaData = connection.getMetaData();
        ResultSet rs = dbMetaData.getTables(null, null, "user", null);
        if (rs.next()) {
            System.out.println("user表创建成功");
        } else {
            System.out.println("user表不存在");
        }
    }

    /**
     * 创建电子邮件表
     * @param connection 数据库连接实例
     * @throws SQLException sql异常
     */
    public static void createEmailTable(Connection connection) throws SQLException {
        Statement stmt = connection.createStatement();
        String sql = "CREATE TABLE IF NOT EXISTS email("+
                "id INT AUTO_INCREMENT PRIMARY KEY,"+
                "senderId INT NOT NULL,"+
                "receiverId INT,"+
                "subject VARCHAR(255),"+
                "content TEXT,"+
                " tip VARCHAR(255));";
        stmt.executeUpdate(sql);
        DatabaseMetaData dbMetaData = connection.getMetaData();
        ResultSet rs = dbMetaData.getTables(null, null, "email", null);
        if (rs.next()) {
            System.out.println("email表创建成功");
        } else {
            System.out.println("email表不存在");
        }
    }
}
