package data_access;

import data_object.Email;
import data_object.User;
import utils.MyDate;

import java.sql.Connection;
import java.sql.Statement;

/**
 * 数据层添加类
 * 在构造函数时指定连接信息
 */
public class Add {

    /**
     * 完成单用户添加
     * @param user 输入一个user的实例
     * @param coon  输入一个数据库连接实例
     * @return 添加成功返回true，否则返回false
     */
    public static boolean addUser(User user, Connection coon) throws Exception {
        if(user.getName().isBlank()||
            user.getEmail().isBlank()||
            user.getPassword().isBlank()||
            user.getAuthorizationCode().isBlank()){
            throw new Exception("用户信息不全");
        }
        Statement stmt = coon.createStatement();
        String sql = "INSERT INTO user(id,name, email, password, authorization_code,gmt_created,gmt_modified)" +
                "VALUES('" +
                user.getId()+"','" +
                user.getName() + "', '" +
                user.getEmail() + "', '" +
                user.getPassword() + "', '" +
                user.getAuthorizationCode() + "','" +
                MyDate.getNowInDateTime().toString()+ "', '" +
                MyDate.getNowInDateTime().toString()+
                "')";
        System.out.println(sql);
        int rowsAffected = stmt.executeUpdate(sql);
        return (rowsAffected > 0);
    }

    /**
     * @param email 输入一个email的实例
     *              初始senderId为创建者的id,receiverId为空
     *              要求email的senderId不为空，否则返回false
     * @param coon  输入一个数据库连接实例
     * @return 添加成功返回true，否则返回false
     * 完成单信件添加
     */
    public static boolean addEmail(Email email, Connection coon) throws Exception {
        if(email.getSenderId()==null||email.getSubject().isBlank()){
            throw new Exception("邮件信息不全");
        }
        Statement stmt = coon.createStatement();
        String sql = "INSERT INTO email(id,sender_Id,receiver_address,subject,content,tip,gmt_created,gmt_modified)" +
                "VALUES('" +
                email.getId() + "','" +
                email.getSenderId() + "', '" +
                email.getReceiverAddress() + "', '" +
                email.getSubject() + "', '" +
                email.getContent() + "', '" +
                email.getTip() + "', '" +
                MyDate.getNowInDateTime()+ "', '" +
                MyDate.getNowInDateTime()+
                "')";

        System.out.println(sql);
        int rowsAffected;
        rowsAffected = stmt.executeUpdate(sql);
        return (rowsAffected > 0);
    }
}
