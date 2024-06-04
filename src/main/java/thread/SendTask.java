package thread;

import data_access.Get;
import data_object.Email;
import service.SendEmailService;
import utils.MyLog;

import javax.mail.MessagingException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class SendTask {

    private static final MyLog LOG = MyLog.getInstance();

    private static final Scanner scanner = new Scanner(System.in);

    public static void send(boolean isLogin, Connection connection){
        if (!isLogin) {LOG.info("请先登录");return;}
        System.out.println("请输入要发送的邮件id");
        try {
            Email email = Get.getEmailById(scanner.next(),connection);
            if (email == null) { LOG.error("没有找到此邮件"); return;}
            if(email.getReceiverAddress().isEmpty()||email.getReceiverAddress().isBlank()){
                LOG.error("发送方邮箱未指定，请修改邮件");
                return;
            }
            SendEmailService.sendEmail(email,Get.getUserById(email.getSenderId(),connection));
        } catch (SQLException | MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
