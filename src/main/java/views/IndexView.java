package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IndexView extends JFrame implements ActionListener{
    private JPanel contentPane;
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public IndexView() {
        super("SMTP简单邮箱发送-登录");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(750, 550);
        setLocationRelativeTo(null);

        contentPane = new JPanel(new BorderLayout());
        setContentPane(contentPane);

        // 左侧按钮面板
        JPanel buttonPanel = new JPanel(new GridLayout(4, 1, 10, 20));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JButton writeButton = new JButton("写邮件");
        JButton watchButton = new JButton("查看邮件");
        JButton exitButton = new JButton("退出");
        buttonPanel.add(writeButton);
        buttonPanel.add(watchButton);
        buttonPanel.add(exitButton);
        contentPane.add(buttonPanel, BorderLayout.WEST);
        Dimension buttonSize = new Dimension(100, 30);
        writeButton.setPreferredSize(buttonSize);
        watchButton.setPreferredSize(buttonSize);
        exitButton.setPreferredSize(buttonSize);

        // 主面板，使用 CardLayout
        mainPanel = new JPanel();
        cardLayout = new CardLayout();
        mainPanel.setLayout(cardLayout);
        contentPane.add(mainPanel, BorderLayout.CENTER);

        // 添加子面板
        WritePanel writePanel = new WritePanel();
        WatchPanel watchPanel = new WatchPanel();
        mainPanel.add(writePanel, "writePanel");
        mainPanel.add(watchPanel, "watchPanel");

        // 按钮事件处理
        writeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "writePanel");
            }
        });

        watchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "watchPanel");
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    IndexView frame = new IndexView();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
