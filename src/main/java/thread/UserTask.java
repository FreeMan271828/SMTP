package thread;

import data_access.Get;
import data_object.*;
import service.EmailService;
import service.SendEmailService;
import service.UserService;
import utils.MyLog;

import javax.mail.MessagingException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class UserTask implements Runnable{

    private User user = null;

    //每次创建新的任务都会初始化为假
    private boolean isLogin = false;

    @Override
    public void run() throws RuntimeException {

        while(true){
            LOG.info("注册1 登录2 写邮件3 发邮件4 查看邮件5 退出6");
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
                    if (!isLogin) { LOG.info("请先登录"); break; }
                    LOG.info("请输入要修改的邮件Id");
                    try {
                        Email email = Get.getEmailById(scanner.next(),connection);
                        if (email == null) {
                            LOG.info("找不到此邮件,将为你创建新邮件");
                            email = new Email();
                            //填入邮件信息
                            EmailService.input(email, connection);
                            //写邮件
                            EmailService.write(email,user,connection);
                            break;
                        }else{
                            LOG.info("以下是该邮件的详细信息");
                            EmailService.print(email,user,connection);
                            //填入邮件信息
                            EmailService.input(email, connection);
                            //写邮件
                            EmailService.write(email,user,connection);
                        }

                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }
                //发邮件
                case 4: {
                    SendTask.send(isLogin,connection);
                    break;
                }
                //查看邮件
                case 5:{
                    ShowTask.show(isLogin,user,connection);
                    break;
                }
                case 6: {isLogin = false;user = null;break;}
                default:{LOG.error("输入错误");}
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
