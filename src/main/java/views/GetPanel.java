package views;

import data_access.Get;
import data_object.Email;
import data_object.User;
import service.EmailService;
import service.SendEmailService;
import service.UserService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class GetPanel extends JPanel {
    private User user;
    private Connection connection;
    private DefaultTableModel tableModel;
    private JTextArea contentArea;
    private Email email;

    public GetPanel(User user, Connection connection, IndexView indexView) {
        setLayout(new BorderLayout());
        this.user = user;
        this.connection = connection;

        // 邮件列表
        String[] columnNames = {"邮件id","主题", "发件人"};
        tableModel = new DefaultTableModel(new Object[0][0], columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable mailTable = new JTable(tableModel);
        JScrollPane mailScrollPane = new JScrollPane(mailTable);
        add(mailScrollPane, BorderLayout.CENTER);

        // 邮件详情面板
        JPanel detailPanel = new JPanel(new BorderLayout());
        JLabel detailLabel = new JLabel("邮件内容");
        detailPanel.add(detailLabel, BorderLayout.NORTH);

        contentArea = new JTextArea(10, 40);
        contentArea.setEditable(false);
        JScrollPane contentScrollPane = new JScrollPane(contentArea);
        detailPanel.add(contentScrollPane, BorderLayout.CENTER);

        add(detailPanel, BorderLayout.SOUTH);

        mailTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int selectedRow = mailTable.getSelectedRow();
                    if (selectedRow != -1) {
                        // 获取选中邮件的ID
                        String emailId = (String) tableModel.getValueAt(selectedRow, 0); // 假设第一列是邮件ID
                        try {
                            email = Get.getEmailById(emailId,connection);
                        } catch (SQLException ex) {
                            e.getButton();
                        }
                        showEmailContent(emailId);
                    }
                }
            }
        });

        refreshEmailTable();
    }

    // 刷新邮件列表
    public void refreshEmailTable() {
        try {
            tableModel.setRowCount(0);

            List<Email> emails = Get.GetReceiveEmails(user, connection); // 使用正确的Get方法

            for (Email email : emails) {
                tableModel.addRow(new Object[]{email.getId(),email.getSubject(), Get.getUserById(email.getSenderId(),connection).getName()}); // 显示邮件ID
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "获取邮件列表出错: " + ex.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
        }
    }

    // 根据邮件 ID 获取并显示邮件内容
    private void showEmailContent(String emailId) {
        try {
            contentArea.setText("");
            Email email = Get.getEmailById(emailId, connection);

            if (email != null) {
                // 使用 EmailService.print 方法显示邮件内容，并将输出重定向到 contentArea
                EmailService.print2(email, user, connection,contentArea);
            } else {
                contentArea.setText("找不到邮件内容。");
            }
        } catch (SQLException ex) {
            contentArea.setText("获取邮件内容出错：" + ex.getMessage());
        }
    }
}