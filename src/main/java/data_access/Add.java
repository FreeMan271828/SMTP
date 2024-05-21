package data_access;

import data_object.Email;
import data_object.User;

import java.sql.Connection;

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
    public boolean addUser(User user, Connection coon) {
        return false;
    }

    /**
     * @param email 输入一个email的实例
     *              初始senderId为创建者的id,receiverId为空
     *              要求email的senderId不为空，否则返回false
     * @param coon  输入一个数据库连接实例
     * @return 添加成功返回true，否则返回false
     * 完成单信件添加
     */
    public boolean addEmail(Email email, Connection coon) {
        return false;
    }
}
