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
    private static final MyLog LOG = MyLog.getInstance();

    /**
     * 获取所有的角色
     */
    public static List<User>getUsers(Connection coon) throws SQLException {
        Statement stmt = coon.createStatement();
        String sql = "select * from user";
        return getUserBySql(stmt,sql);
    }

    public static String GetNameByAddress(String address, Connection coon)throws SQLException{
        String sql = "select * from user where email = '" + address + "'";
        List<User> users = getUserBySql(coon.createStatement(),sql);
        if(users.isEmpty()){ return null;}
        else{
            return users.getFirst().getName();
        }
    }

    public static List<Email>GetReceiveEmails(User user, Connection coon) throws SQLException {
        if (user.getId().isEmpty()||user.getId().isBlank()){
            LOG.error("用户id为空");
            return null;
        }
        Statement statement = coon.createStatement();
        String sql = "select * from email where receiver_address= '" + user.getEmail()+"'";
        return getEmailBySql(statement,sql);
    }

    public static List<Email> GetSendEmails(User user, Connection coon) throws SQLException {
        if (user.getId().isEmpty()||user.getId().isBlank()){
            LOG.error("用户id为空");
            return null;
        }
        Statement statement = coon.createStatement();
        String sql = "select * from email where sender_id= '" + user.getId()+"'";
        return getEmailBySql(statement,sql);
    }

    /**
     * 根据 Id获取User
     */
    public static User getUserById(String id,Connection coon) throws SQLException {
        Statement stmt =  coon.createStatement();
        String sql = "select * from user where id=+'" +id+"'";
        return  getUserBySql(stmt,sql).getFirst();
    }

    /**
     * 根据email Id获取Email
     */
    public static Email getEmailById(String id,Connection coon) throws SQLException {
        Statement stmt =  coon.createStatement();
        String sql = "select * from email where id=+'" +id+"'";
        List<Email>emails = getEmailBySql(stmt,sql);
        if (emails.isEmpty()){
            return null;
        }
        return emails.getFirst();
    }

    /**
     * 根据邮箱密码获取User
     */
    public static User getUserByAddress(String address, String password, Connection coon) throws SQLException {
        Statement stmt =  coon.createStatement();
        String sql = "select * from user where email='" + address + "' and password='" + password + "';";
        List<User>users = getUserBySql(stmt,sql);
        if (users.isEmpty()){
            return null;
        }
        return users.getFirst();
    }

    /**
     * 基本方法
     * 根据sql查询语句返回对应的User
     */
    public static List<User> getUserBySql(Statement stmt, String sql){
        List<User> users = new ArrayList<>();
        try (ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
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
            while (rs.next()) {
                Email email = new Email();
                email.setId(rs.getString("id"));
                email.setSenderId(rs.getString("sender_id"));
                email.setReceiverAddress(rs.getString("receiver_address"));
                email.setSubject(rs.getString("subject"));
                email.setTip(rs.getString("tip"));
                email.setContent(rs.getString("content"));
                email.setGmtCreated(rs.getString("gmt_created"));
                email.setGmtModified(rs.getString("gmt_modified"));
                emails.add(email);
            }
        }
        return emails;
    }
}
