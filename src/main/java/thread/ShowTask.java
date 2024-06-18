package thread;

import data_object.Email;
import data_object.User;
import service.EmailService;
import service.UserService;
import utils.MyLog;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ShowTask {

    private static final MyLog LOG = MyLog.getInstance();

    public static void show(boolean isLogin, User user, Connection connection){
        if(!isLogin){
            LOG.info("请先登录");
            return;
        }
        UserService userService = new UserService(user, connection);
        try {
            List<Email> emails = userService.getEmails();
            for(int i = 0;i < emails.size();i++){
                Email email = emails.get(i);
                try {
                    System.out.println("第"+i+1+"封邮件如下");
                    EmailService.print(email,user,connection);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
