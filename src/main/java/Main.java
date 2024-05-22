import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import control.JsonGet;
import data_access.Conn;
import data_access.TableOper;

import javax.swing.text.TabExpander;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        String jsonString = JsonGet.get("Const.json");
        JSONObject jsonObject = JSON.parseObject(jsonString);

        //一个线程的demo
        JSONObject dbConnection1 = jsonObject.getJSONObject("DbConnection1");
        String url1=dbConnection1.getString("url");
        String username1=dbConnection1.getString("name");
        String password1=dbConnection1.getString("password");
        Connection connection1 = Conn.getConnection(url1,username1,password1);
        //表操作
        TableOper.createUserTable(connection1);
        TableOper.createEmailTable(connection1);
    }
}