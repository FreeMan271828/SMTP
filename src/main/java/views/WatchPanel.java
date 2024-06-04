package views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class WatchPanel extends JPanel {
    public WatchPanel() {
        setLayout(new BorderLayout());

        // 邮件列表
        String[] columnNames = {"主题", "发件人"};
        String[][] data = {
                {"测试邮件1", "test1@example.com"},
                {"会议通知", "meeting@example.com"}
        };
        // 使用 DefaultTableModel 创建表格模型
        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // 设置所有单元格为不可编辑
            }
        };
        JTable mailTable = new JTable(model);
        JScrollPane mailScrollPane = new JScrollPane(mailTable);
        add(mailScrollPane, BorderLayout.CENTER);

        // 邮件详情面板
        JPanel detailPanel = new JPanel(new BorderLayout());
        JLabel detailLabel = new JLabel("邮件内容");
        detailPanel.add(detailLabel, BorderLayout.NORTH);
        add(detailPanel, BorderLayout.SOUTH);
    }
}
