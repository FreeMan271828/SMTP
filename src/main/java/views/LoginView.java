package views;

import data_object.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class LoginView extends JFrame {
    int type;
    LoginView jk;
    public User user;
    public LoginView(){
        jk = this;
        initView();
        initMember();
        setVisible(true);
    }
    private void initView(){
        this.setSize(603,570);
        //设置界面的标题
        this.setTitle("SMTP简单邮箱发送-登录");
        //设置页面居中
        this.setLocationRelativeTo(null);
        //设置关闭模式,3是当前界面关闭就关掉整个项目
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //取消默认的居中放置，只有取消了才会按照XY轴的形式添加组件
        this.setLayout(null);
    }
    static JTextField mtext;
    static JTextField xtext;
    static JTextField ytext;
    static JTextField stext;
    private void initMember(){
        JLabel xlabel = new JLabel("姓      名:");
        JLabel ylabel = new JLabel("邮      箱:");
        JLabel mlabel = new JLabel("密      码:");
        JLabel slabel = new JLabel("授  权  码:");
        xtext = new JTextField(15);
        ytext = new JTextField(15);
        stext = new JTextField(15);
        mtext = new JTextField(15);
        JButton db = new JButton("登录");
        JButton zb = new JButton("注册");
        Container m = getContentPane();
        getContentPane().setBackground(Color.white);
        m.add(ylabel);
        m.add(xlabel);
        m.add(mlabel);
        m.add(slabel);
        m.add(xtext);
        m.add(ytext);
        m.add(stext);
        m.add(mtext);
        m.add(db);
        m.add(zb);
        Font font = new Font("仿宋", Font.PLAIN, 20);
        xlabel.setFont(font);
        ylabel.setFont(font);
        mlabel.setFont(font);
        slabel.setFont(font);
        setBounds(480,100,503,570);
        xlabel.setBounds(40,45,180,35);
        mlabel.setBounds(40,135,180,35);
        xtext.setBounds(180,45,230,35);
        mtext.setBounds(180,135,230,35);
        ylabel.setBounds(40,230,180,35);
        ytext.setBounds(180,230,230,35);
        slabel.setBounds(40,335,180,35);
        stext.setBounds(180,335,230,35);
        db.setBounds(105,470,100,35);
        zb.setBounds(275,470,100,35);

        zb.addActionListener(new ActionListener() {     //为注册按钮提供监听事件
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        // 在登录按钮的事件监听器中添加逻辑
        db.addActionListener(new ActionListener() {     //为登录按钮提供监听事件
            @Override
            public void actionPerformed(ActionEvent e) {
                //判断是否全部输入了
                if (areAllFieldsFilled()) {

                }

            }
        });


    }
    private boolean areAllFieldsFilled() {
        if (xtext.getText().isEmpty() ||
                ytext.getText().isEmpty() ||
                mtext.getText().isEmpty() ||
                stext.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "请填写所有字段！", "错误", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            return true;
        }
    }




    public static void main(String[] args) {
        LoginView a = new LoginView();
    }
}
