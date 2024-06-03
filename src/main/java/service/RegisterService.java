package service;

import data_access.Add;
import data_access.Get;
import data_object.User;
import utils.MyUuid;

import java.sql.Connection;
import java.util.concurrent.Callable;

public class RegisterService {

    //要求必须存在name,email,pwd,authenticateCode
    private User user;
    private Connection connection;

    public RegisterService(User user, Connection connection) {
        user.setId(MyUuid.getUuid());
        this.user = user;
        this.connection = connection;
    }

    /**
     * 用户注册
     * @return 返回完整User对象
     * @throws Exception 用户信息不全
     */
    public User register() throws Exception {
        if(user.getEmail().isBlank()||
                user.getPassword().isBlank()||
                user.getAuthorizationCode().isBlank()||
                user.getName().isBlank()) {
            throw new Exception("用户登录信息不全");
        }
        Add.addUser(user,connection);
        return Get.getUserByEmail(user.getEmail(),user.getPassword(),connection);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
