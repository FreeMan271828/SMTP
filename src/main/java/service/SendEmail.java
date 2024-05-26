package service;

import data_object.Email;
import data_object.User;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.sql.SQLException;
import java.util.Properties;

public class SendEmail {

    /**
     * @param email  里面包含了receiverAddress
     * @param sender 为了service与dao的分离，在此传输sender
     * @throws SQLException
     * @throws MessagingException
     */
    public static void sendEmail(Email email, User sender) throws SQLException, MessagingException {

        // 设置邮件服务器的属性
        Properties props = getProperties();
        // 创建会话对象
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sender.getEmail(), sender.getAuthorizationCode());
            }
        });
        session.setDebug(true);
        // 创建邮件并发送
        MimeMessage message = createMail(session, email, sender);
        Transport.send(message);
        System.out.println("邮件已成功发送！");
    }

    /**
     *
     * @param session 与服务器交互的会话
     * @param sender  发送方信息
     * @return
     */
    public static MimeMessage createMail(Session session, Email email, User sender) throws MessagingException {
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(sender.getEmail())); // 设置发件人
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(email.getReceiverAddress())); // 设置收件人
        message.setSubject(email.getSubject()); // 设置邮件主题
        message.setText(email.getContent());
        return message;
    }

    private static Properties getProperties() {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.qq.com"); // 指定SMTP服务器
        props.put("mail.smtp.port", "465"); // QQ邮箱的SMTP端口
        props.put("mail.smtp.auth", "true"); // 启用认证
        props.put("mail.smtp.ssl.enable", "true"); // 使用SSL加密连接
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");
        props.put("mail.smtp.socketFactory.port", "465");
        return props;
    }
}
