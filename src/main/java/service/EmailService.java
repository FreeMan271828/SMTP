package service;

import data_access.Add;
import data_access.Get;
import data_object.Email;
import data_object.User;
import utils.MyUuid;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class EmailService {

    private final User sender;
    private final Connection connection;
    private final Email email;

    public EmailService(User sender, Email email, Connection connection) {
        this.sender = sender;
        this.email = email;
        this.connection = connection;
    }

    public Email write() throws Exception {
        //如果邮件为空，则需要新建一封新邮件
        if(email.getId()==null||email.getId().isBlank()||email.getId().isEmpty()){
            Email tempEmail = new Email();
            tempEmail.setId(MyUuid.getUuid());
            tempEmail.setSenderId(sender.getId());
            tempEmail.setReceiverAddress(email.getReceiverAddress());
            tempEmail.setSubject(email.getSubject());
            tempEmail.setContent(email.getContent());
            tempEmail.setTip(email.getTip());
            Add.addEmail(tempEmail,connection);
            return Get.getEmailById(tempEmail.getId(),connection);
        }
        //进行文件修改
        else{
            //通过id获取邮件，因为id一定不会变
            Email tempMail = Get.getEmailById(email.getId(),connection);
            //email的tip不为空
            if(!email.getTip().isBlank()&&!email.getTip().isEmpty()){
                tempMail.setTip(email.getTip());
            }
            //email的receiver不为空
            if(!email.getReceiverAddress().isBlank()&&!email.getReceiverAddress().isEmpty()){
                tempMail.setReceiverAddress(email.getReceiverAddress());
            }
            //email的subject不为空
            if(!email.getSubject().isBlank()&&!email.getSubject().isEmpty()){
                tempMail.setSubject(email.getSubject());
            }
            //email的content不为空
            if(!email.getContent().isBlank()&&!email.getContent().isEmpty()){
                tempMail.setContent(email.getContent());
            }
            //TODO 进行修改操作
        }

        return email;
    }

}
