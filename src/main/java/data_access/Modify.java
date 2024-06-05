package data_access;

import data_object.Email;
import data_object.User;
import utils.MyDate;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 数据层删除操作
 */
public class Modify {
    /**
     * 修改用户名或密码
     * @param newUser 新的用户实例，必须有id属性
     * @param coon 数据库连接实例
     * @return 返回修改的数量
     */
    public int modifyUser(User newUser, Connection coon){
        //假如newUser的属性不为空并且与user不相等，则修改为新值

        return 0;
    }

    /**
     * 修改邮箱信息
     * 假如newEmail的属性不为空并且与email不相等，则修改为新值
     * 假设email的id不会变
     * @param newEmail 新的邮箱实例
     * @param coon 数据库连接实例
     * @return 返回修改的数量
     */
    public static boolean modifyEmail(Email newEmail,Connection coon) throws SQLException {
        Statement statement = coon.createStatement();
        String sql =
        "UPDATE email "+
        "SET subject = '"+newEmail.getSubject()+"',"+
        "receiver_address = '"+newEmail.getReceiverAddress()+"',"+
        "content = '"+newEmail.getContent()+"',"+
        "tip = '"+newEmail.getTip()+"',"+
        "gmt_modified = '"+ MyDate.getNowInDateTime() +"' "+
        "WHERE id = '"+newEmail.getId()+"';";
        System.out.println(sql);
        return statement.executeUpdate(sql)>0;
    }
}
