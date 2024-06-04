package thread;

import data_object.*;
import service.LoginService;
import service.RegisterService;
import utils.MyLog;

import java.sql.Connection;
import java.util.Scanner;
import java.util.logging.Logger;

public class UserTask implements Runnable{

    private Connection connection;

    private final Scanner scanner = new Scanner(System.in);

    private static final MyLog LOG = MyLog.getInstance();

    private User user = null;

    //每次创建新的任务都会初始化为假
    private boolean isLogin = false;

    @Override
    public void run() throws RuntimeException {

        while(true){
            System.out.println("注册1 登录2 写邮件3 发邮件4 退出5");
            switch (scanner.nextInt()) {
                //注册逻辑
                case 1: {
                    User user = new User();
                    System.out.println("请输入姓名");
                    user.setName(scanner.next());
                    System.out.println("请输入邮箱");
                    user.setEmail(scanner.next());
                    System.out.println("请输入密码");
                    user.setPassword(scanner.next());
                    System.out.println("请输授权码");
                    user.setAuthorizationCode(scanner.next());
                    RegisterService registerService = new RegisterService(user, connection);
                    try {
                        registerService.register();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }
                //登录逻辑
                case 2:
                    if(isLogin) {
                        LOG.info(user.getName()+"已登录");
                        break;
                    }
                    User tempUser = new User();
                    System.out.println("输入邮箱名");
                    System.out.println("输入密码");
                    tempUser.setEmail(scanner.next());
                    tempUser.setPassword(scanner.next());
                    LoginService loginService = new LoginService(tempUser, connection);
                    try {
                        user = loginService.login();
                        this.isLogin = true;
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                //写邮件
                case 3:
                    if(!isLogin){

                    }
                    break;
                case 4:
                    break;
                case 5:
                    break;
                default:
                    System.out.println("输入错误");
            }
        }
    }

    public UserTask(){
        this.isLogin = false;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
