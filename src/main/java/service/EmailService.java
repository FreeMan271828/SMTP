package service;

import data_access.Add;
import data_access.Get;
import data_access.Modify;
import data_object.Email;
import data_object.User;
import utils.MyUuid;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class EmailService {

    private final static Scanner scanner = new Scanner(System.in);

    public static void print(Email email, User user, Connection connection, JTextArea contentArea) throws SQLException {
        UserService userService = new UserService(user, connection);

        // 将邮件信息添加到 contentArea
        contentArea.append("邮件id:     " + email.getId() + "\n");
        contentArea.append("发送人Id:   " + email.getSenderId() + "\n");
        contentArea.append("发送人姓名:  " + userService.getUserById() + "\n");
        contentArea.append("接收方地址:  " + email.getReceiverAddress() + "\n");
        contentArea.append("主题:       " + email.getSubject() + "\n");
        contentArea.append("内容:       " + email.getContent() + "\n");
        contentArea.append("备注:       " + email.getTip() + "\n");
        contentArea.append("最后修改时间: " + email.getGmtModified() + "\n\n");
    }

    public static Email input(Email email,Connection connection){
        //发件方和最后修改时间自动填入
        System.out.println("请输入邮件的接受地址");
        email.setReceiverAddress(scanner.next());
        System.out.println("请输入邮件主题");
        email.setSubject(scanner.next());
        System.out.println("请输入邮件内容");
        email.setContent(scanner.next());
        System.out.println("请输入备注");
        email.setTip(scanner.next());
        return email;
    }


    public static Email write(Email email,User sender,Connection connection) throws Exception {
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
            if(Modify.modifyEmail(email,connection)){ return Get.getEmailById(email.getId(),connection); }
            else{
                System.out.println("修改失败");
                return null;
            }
        }
    }

}
