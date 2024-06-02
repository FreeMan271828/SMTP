package service;

import data_access.Get;
import data_object.User;

import java.sql.Connection;
import java.util.concurrent.Callable;

public class LoginService implements Callable<User> {

    //要求必须存在email,password
    private final User user;
    private final Connection conn;

    public LoginService(User user, Connection conn) {
        this.user = user;
        this.conn = conn;
    }

    /**
     * 用户登录多线程接口
     * @return 返回完整User对象
     */
    @Override
    public User call() throws Exception{
        // 执行登录逻辑
        System.out.println(user.getEmail()+ " is logging in.");
        if(user.getEmail().isBlank()||user.getPassword().isBlank()) {
            throw new Exception("用户登录信息不全");
        }
        // 登录代码...
        return Get.getUserByEmail(user.getEmail(),user.getPassword(),conn);
    }
}
