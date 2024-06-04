package thread;

import data_object.Email;
import data_object.User;
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
            System.out.println("id\tsender\tsubject\t");
            emails.forEach(email ->{
                try {
                    System.out.println(email.getId()+"\t"
                            +userService.getUserById(email.getSenderId())+"\t"
                            +email.getSubject());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
