package data_access;

import data_object.Email;
import data_object.User;
import utils.MyLog;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Get {

    //日志类注入
    private static MyLog LOG = MyLog.getInstance();

    /**
     * 获取所有的角色
     */
    public static List<User>getUsers(Connection coon) throws SQLException {
        Statement stmt = coon.createStatement();
        String sql = "select * from user";
        return getUserBySql(stmt,sql);
    }

    /**
     * 根据Id获取User
     */
    public static User getUserById(String id,Connection coon) throws SQLException {
        Statement stmt =  coon.createStatement();
        String sql = "select * from user where id=+'" +id+"'";
        return  getUserBySql(stmt,sql).getFirst();
    }

    /**
     * 根据Id获取Email
     */
    public static Email getEmailById(String id,Connection coon) throws SQLException {
        Statement stmt =  coon.createStatement();
        String sql = "select * from email where id=+'" +id+"'";
        System.out.println(sql);
        return  getEmailBySql(stmt,sql).getFirst();
    }

    /**
     * 根据其余信息获取User
     */
    public static User getUserByEmail(String email,String password,Connection coon) throws SQLException {
        Statement stmt =  coon.createStatement();
        String sql = "select * from user where email='" + email + "' and password='" + password + "';";
        return getUserBySql(stmt,sql).getFirst();
    }

    /**
     * 基本方法
     * 根据sql查询语句返回对应的User
     */
    public static List<User> getUserBySql(Statement stmt, String sql){
        List<User> users = new ArrayList<>();
        try (ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                User user = new User();
                user.setId(rs.getString("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setAuthorizationCode(rs.getString("authorization_code"));
                user.setPassword(rs.getString("password"));
                users.add(user);
            }
        }catch (Exception e){
            LOG.info("getUserBySql Exception: " + e.getMessage());
            e.fillInStackTrace();
        }
        return users;
    }

    /**
     * 基本方法
     * 根据sql查询语句返回对应的Email
     */
    public static List<Email> getEmailBySql(Statement stmt, String sql) throws SQLException {
        List<Email> emails;
        try (ResultSet rs = stmt.executeQuery(sql)) {
            emails = new ArrayList<>();
            if (rs.next()) {
                Email email = new Email();
                email.setId(rs.getString("id"));
                email.setReceiverAddress(rs.getString("receiver_address"));
                email.setSubject(rs.getString("subject"));
                email.setTip(rs.getString("tip"));
                email.setContent(rs.getString("content"));
                emails.add(email);
            }
        }
        return emails;
    }
}
