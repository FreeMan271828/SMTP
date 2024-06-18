package views;

import data_object.Email;
import data_object.User;
import service.EmailService;
import service.SendEmailService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class WritePanel extends JPanel {
    private User user; // 当前登录的用户
    private Connection connection;
    public WritePanel(User user, Connection connection, IndexView indexView) {
        this.user = user;
        this.connection = connection;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // 增加组件间距

        // 主题 (加大字体，加宽输入框)
        JLabel subjectLabel = new JLabel("主题:");
        subjectLabel.setFont(subjectLabel.getFont().deriveFont(16f)); // 设置字体大小为 16
        JTextField subjectField = new JTextField(40); // 加宽输入框
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(subjectLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 3; // 跨 3 列
        add(subjectField, gbc);

        // 收件人 (加大字体，加宽输入框)
        JLabel recipientLabel = new JLabel("收件人邮箱:");
        recipientLabel.setFont(recipientLabel.getFont().deriveFont(16f)); // 设置字体大小为 16
        JTextField recipientField = new JTextField(40); // 加宽输入框
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(recipientLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        add(recipientField, gbc);

        // 邮件内容
        JLabel contentLabel = new JLabel("内容:");
        contentLabel.setFont(contentLabel.getFont().deriveFont(16f)); // 设置字体大小为 16
        JTextArea contentArea = new JTextArea(15, 40); // 加宽加高输入框
        contentArea.setLineWrap(true); // 设置自动换行
        contentArea.setWrapStyleWord(true); // 设置按单词换行
        JScrollPane contentScrollPane = new JScrollPane(contentArea);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        add(contentLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        gbc.gridheight = 2; // 跨 2 行
        add(contentScrollPane, gbc);

        // 按钮
        JButton saveButton = new JButton("保存");
        JButton sendButton = new JButton("发送");
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        add(saveButton, gbc);
        gbc.gridx = 3;
        gbc.gridy = 4;
        add(sendButton, gbc);

        // 保存按钮事件处理
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String subject = subjectField.getText();
                String recipient = recipientField.getText();
                String content = contentArea.getText();
                String tip = ""; // 你需要添加获取备注信息的代码

                // 创建 Email 对象
                Email email = new Email();
                email.setReceiverAddress(recipient);
                email.setSubject(subject);
                email.setContent(content);
                email.setTip(tip);

                try {
                    // 使用 EmailService 保存邮件
                    Email savedEmail = EmailService.write(email, user, connection);

                    if (savedEmail != null) {
                        // 保存成功
                        JOptionPane.showMessageDialog(null, "邮件保存成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        // 保存失败
                        JOptionPane.showMessageDialog(null, "邮件保存失败！", "错误", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    // 处理保存邮件过程中发生的异常
                    JOptionPane.showMessageDialog(null, "保存邮件出错: " + ex.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // 发送按钮事件处理
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String subject = subjectField.getText();
                String recipient = recipientField.getText();
                String content = contentArea.getText();


                // 创建 Email 对象
                Email email = new Email();
                email.setReceiverAddress(recipient);
                email.setSubject(subject);
                email.setContent(content);

                try {
                    // 使用 SendEmailService 发送邮件
                    SendEmailService.sendEmail(email, user);

                    // 发送成功
                    JOptionPane.showMessageDialog(null, "邮件发送成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    // 处理发送邮件过程中发生的异常
                    JOptionPane.showMessageDialog(null, "发送邮件出错: " + ex.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
