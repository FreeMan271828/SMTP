package service;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import data_object.Email;
import data_object.User;
import utils.JsonGet;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.sql.SQLException;
import java.util.Properties;

public class SendEmailService {

    static String data = JsonGet.get("Const.json");
    static JSONObject jsonObject = JSON.parseObject(data);
    static JSONObject SmtpProps = jsonObject.getJSONObject("SmtpProps");

    /**
     * @param email  里面包含了receiverAddress
     * @param sender 为了service与dao的分离，在此传输sender
     * @throws SQLException
     * @throws MessagingException
     */
    public static void sendEmail(Email email, User sender) throws SQLException, MessagingException {


        if(email.getReceiverAddress().isBlank()||
            email.getSubject().isBlank()) {
            throw new MessagingException("邮件信息不全");
        }
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
        props.put("mail.smtp.host", SmtpProps.get("mail.smtp.host")); // 指定SMTP服务器
        props.put("mail.smtp.port", SmtpProps.get("mail.smtp.port")); // QQ邮箱的SMTP端口
        props.put("mail.smtp.auth", SmtpProps.get("mail.smtp.auth")); // 启用认证
        props.put("mail.smtp.ssl.enable", SmtpProps.get("mail.smtp.ssl.enable")); // 使用SSL加密连接
        props.put("mail.smtp.socketFactory.class", SmtpProps.get("mail.smtp.socketFactory.class"));
        props.put("mail.smtp.socketFactory.fallback", SmtpProps.get("mail.smtp.socketFactory.fallback"));
        props.put("mail.smtp.socketFactory.port", SmtpProps.get("mail.smtp.socketFactory.port"));
        return props;
    }
}
