import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import utils.JsonGet;
import data_access.Conn;
import data_access.TableOpera;
import data_object.Email;
import data_object.User;
import service.LogIn;
import service.SendEmail;

import javax.mail.MessagingException;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, MessagingException {
        String jsonString = JsonGet.get("Const.json");
        JSONObject jsonObject = JSON.parseObject(jsonString);

        //连接到数据库
        JSONObject dbConnection = jsonObject.getJSONObject("DbConnection1");
        String url1=dbConnection.getString("url");
        String username1=dbConnection.getString("name");
        String password1=dbConnection.getString("password");
        Connection connection = Conn.getConnection(url1,username1,password1);
        //表操作
        TableOpera.createUserTable(connection);
        TableOpera.createEmailTable(connection);

        //一个线程
        //登录（获取身份信息User）
        User user = LogIn.LoginByEmail("1114509193@qq.com","271828",connection);
        //注册（添加身份信息）
        //发送邮件（传送Email）
        //根据SenderId获取Sender，根据receiverAddress获取receiver
        Email email = new Email();
        email.setSubject("testLink");
        email.setContent("testInfo");
        email.setSenderId(user.getId());
        email.setReceiverAddress("1114509193@qq.com");
        SendEmail.sendEmail(email,user);
    }
}