package thread;

import data_object.*;
import service.LoginService;
import service.SendEmailService;

import javax.mail.MessagingException;
import java.sql.Connection;
import java.sql.SQLException;

public class UserTask implements Runnable{

    private Connection connection;

    @Override
    public void run() throws RuntimeException {

        User loginUser = new User();
        loginUser.setEmail("1114509193@qq.com");
        loginUser.setPassword("123456");
        //创建该用户的登录任务
        LoginService loginService = new LoginService(loginUser,connection);
        try {
            //该用户进行登录，此方法为同步
            User user = loginService.login();
            //创建该用户的发送邮件任务

            //发送邮件（传送Email）
            //根据SenderId获取Sender，根据receiverAddress获取receiver
            Email email = new Email();
            email.setSubject("testLink");
            email.setContent("testInfo");
            email.setSenderId(loginUser.getId());
            email.setReceiverAddress("1114509193@qq.com");
            try {
                SendEmailService.sendEmail(email,user);
            } catch (SQLException | MessagingException e) {
                throw new RuntimeException(e);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
