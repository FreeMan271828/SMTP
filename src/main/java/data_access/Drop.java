package data_access;

import data_object.Email;
import data_object.User;

import java.sql.Connection;

/**
 * 数据层删除操作
 */
public class Drop {

    /**
     * @return 返回删除的用户数目
     * @param user 传入用户属性（不一定全面），根据存在的用户属性删除用户
     * 当用户id存在时，直接判断id。当用户id不存在时，判断用户姓名和密码
     * @param coon 数据库连接信息
     */
    public int dropUser(User user, Connection coon){
        return 0;
    }

    /**
     * @return 返回删除的用户数目
     * @param email 传入用户属性（不一定全面），根据存在的用户属性删除用户
     *              当emailId存在时，直接判断emailId。否则判断subject和content
     * @param coon 数据库连接信息
     */
    public int dropEmail(Email email, Connection coon){
        return 0;
    }
}
