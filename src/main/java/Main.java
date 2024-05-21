import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import control.JsonGet;
import data_access.Conn;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        String jsonString = JsonGet.get("Const.json");
        JSONObject jsonObject = JSON.parseObject(jsonString);

        JSONObject dbConnection1 = jsonObject.getJSONObject("DbConnection1");
        String url=dbConnection1.getString("url");
        String username=dbConnection1.getString("name");
        String password=dbConnection1.getString("");
        Connection connection = Conn.getConnection(url,username,password);
        System.out.println(url);
        System.out.println(username);
        System.out.println(password);
    }
}