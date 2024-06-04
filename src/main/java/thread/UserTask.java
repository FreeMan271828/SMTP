package thread;

import data_object.*;
import utils.MyLog;

import java.sql.Connection;
import java.util.Scanner;

public class UserTask implements Runnable{

    private User user = null;

    //每次创建新的任务都会初始化为假
    private boolean isLogin = false;

    @Override
    public void run() throws RuntimeException {

        while(true){
            System.out.println("注册1 登录2 写邮件3 发邮件4 查看邮件5 退出6");
            switch (scanner.nextInt()) {
                //注册逻辑
                case 1: {
                    RegisterTask.register(connection);
                    break;
                }
                //登录逻辑
                case 2: {
                    this.user=LoginTask.login(isLogin,user,connection);
                    if(user!=null) { isLogin = true; }
                    break;
                }
                //写邮件
                case 3: {
                    if (!isLogin) {
                        LOG.info("请先登录");
                        break;
                    }
                    break;
                }
                //发邮件
                case 4: {
                    if (!isLogin) {
                        LOG.info("请先登录");
                        break;
                    }
                    break;
                }
                //查看邮件
                case 5:{
                    ShowTask.show(isLogin,user,connection);
                    break;
                }
                case 6: {
                    isLogin = false;
                    user = null;
                    break;
                }
                default:
                    System.out.println("输入错误");
            }
        }
    }

    private Connection connection;

    private final Scanner scanner = new Scanner(System.in);

    private static final MyLog LOG = MyLog.getInstance();

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
