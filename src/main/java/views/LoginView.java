package views;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import data_access.Conn;
import data_object.User;
import service.LoginService;
import utils.JsonGet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class LoginView extends JFrame {
    int type;
    LoginView jk;
    public User user;
    private Connection connection;
    public LoginView(Connection connection){
        this.connection = connection;
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
    static JTextField ytext;
    private void initMember(){
        JLabel ylabel = new JLabel("邮      箱:");
        JLabel mlabel = new JLabel("密      码:");
        ytext = new JTextField(15);
        mtext = new JTextField(15);
        JButton db = new JButton("登录");
        JButton zb = new JButton("注册");
        Container m = getContentPane();
        getContentPane().setBackground(Color.white);
        m.add(mlabel);
        m.add(ylabel);
        m.add(mtext);
        m.add(ytext);
        m.add(zb);
        m.add(db);
        Font font = new Font("仿宋", Font.PLAIN, 20);
        ylabel.setFont(font);
        mlabel.setFont(font);
        setBounds(480,100,503,570);
        ylabel.setBounds(40,135,180,35);
        ytext.setBounds(180,135,230,35);
        mlabel.setBounds(40,230,180,35);
        mtext.setBounds(180,230,230,35);
        db.setBounds(105,470,100,35);
        zb.setBounds(275,470,100,35);

        zb.addActionListener(new ActionListener() {     //为注册按钮提供监听事件
            @Override
            public void actionPerformed(ActionEvent e) {
                // 创建 RegisterView 对象，并将当前 LoginView 的 connection 对象传递给它
                RegisterView registerView = new RegisterView(connection);
                registerView.setVisible(true); // 显示注册界面

                // 隐藏当前登录界面
                dispose(); // 这会关闭当前窗口，并释放资源。如果你想之后再显示登录界面，可以使用 setVisible(false) 来隐藏窗口。
            }
        });
        // 在登录按钮的事件监听器中添加逻辑
        db.addActionListener(new ActionListener() {     //为登录按钮提供监听事件
            @Override
            public void actionPerformed(ActionEvent e) {
                //判断是否全部输入了
                if (areAllFieldsFilled()) {
                    String email = ytext.getText();
                    String password = mtext.getText();
                    // 创建 User 对象
                    User user = new User();
                    user.setEmail(email);
                    user.setPassword(password);
                    try {
                        // 使用 LoginService 进行登录验证
                        LoginService loginService = new LoginService(user, connection);
                        User loggedInUser = loginService.login();

                        if (loggedInUser != null) {
                            // 登录成功
                            JOptionPane.showMessageDialog(null, "登录成功！", "提示", JOptionPane.INFORMATION_MESSAGE);

                            // 跳转到主页面
                            IndexView indexView = new IndexView(loggedInUser, connection);
                            indexView.setVisible(true);

                            // 关闭当前登录界面
                            dispose();
                        } else {
                            // 登录失败
                            JOptionPane.showMessageDialog(null, "登录失败，请检查用户名或密码！", "错误", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (Exception ex) {
                        // 处理登录过程中发生的异常
                        JOptionPane.showMessageDialog(null, "登录出错: " + ex.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
                    }

                }

            }
        });
    }
    private boolean areAllFieldsFilled() {
        if (ytext.getText().isEmpty() &&
                mtext.getText().isEmpty()
           ) {
            JOptionPane.showMessageDialog(null, "请填写所有字段！", "错误", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            return true;
        }
    }




    public static void main(String[] args) {
        String jsonString = JsonGet.get("Const.json");
        JSONObject jsonObject = JSON.parseObject(jsonString);

        //连接到数据库
        JSONObject dbConnection = jsonObject.getJSONObject("DbConnection1");
        String url1=dbConnection.getString("url");
        String username1=dbConnection.getString("name");
        String password1=dbConnection.getString("password");
        Connection connection = Conn.getConnection(url1,username1,password1);
        LoginView a = new LoginView(connection );
    }
}
