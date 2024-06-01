package thread;

import data_object.*;
import service.LogIn;
import service.SendEmail;

import javax.mail.MessagingException;
import java.sql.Connection;
import java.sql.SQLException;

public class UserThread implements Runnable{

    private Connection connection;
    @Override
    public void run() {
        //登录（获取身份信息User）
        User user = null;
        try {
            user = LogIn.LoginByEmail("1114509193@qq.com","271828",connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //注册（添加身份信息）
        //发送邮件（传送Email）
        //根据SenderId获取Sender，根据receiverAddress获取receiver
        Email email = new Email();
        email.setSubject("testLink");
        email.setContent("testInfo");
        email.setSenderId(user.getId());
        email.setReceiverAddress("1114509193@qq.com");
        try {
            SendEmail.sendEmail(email,user);
        } catch (SQLException | MessagingException e) {
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
