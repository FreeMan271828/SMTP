package thread;

import data_object.User;
import service.LoginService;
import utils.MyLog;

import java.sql.Connection;
import java.util.Scanner;

public class LoginTask {

    private static final Scanner scanner = new Scanner(System.in);

    private static final MyLog LOG = MyLog.getInstance();

    public static User login(boolean isLogin, User user, Connection connection){
        if (isLogin) {
            LOG.info(user.getName() + "已登录");
            return null;
        }
        User tempUser = new User();
        System.out.println("输入邮箱名");
        System.out.println("输入密码");
        tempUser.setEmail(scanner.next());
        tempUser.setPassword(scanner.next());
        LoginService loginService = new LoginService(tempUser, connection);
        try {
            user = loginService.login();
            if (user == null) {
                LOG.info("登录信息有误");
                return null;
            }
            return user;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
