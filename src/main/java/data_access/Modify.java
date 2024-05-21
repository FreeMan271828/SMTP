package data_access;

import data_object.Email;
import data_object.User;

/**
 * 数据层删除操作
 */
public class Modify {
    /**
     * 修改用户名或密码
     * @param user 旧的用户实例
     * @param newUser 新的用户实例
     */
    public int modifyUser(User user,User newUser){

        return 0;
    }

    /**
     * 修改邮件信息
     */
    public int modifyEmail(Email email,Email newEmail){
        return 0;
    }
}
