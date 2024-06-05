package views;

import javax.swing.*;
import java.awt.*;

public class WritePanel extends JPanel {
    public WritePanel() {
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
        JLabel recipientLabel = new JLabel("收件人:");
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
    }
}
