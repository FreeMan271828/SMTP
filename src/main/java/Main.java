import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import data_access.Get;
import thread.UserTask;
import utils.JsonGet;
import data_access.Conn;
import data_access.TableOpera;
import utils.MyThreadPool;
import views.LoginView;

import java.sql.Connection;
import java.util.concurrent.ExecutorService;

public class Main {

    public static void main(String[] args) throws Exception {
        //获取线程池
        ExecutorService executor =  MyThreadPool.getThreadPool();

        //进行多线程
        for(int i=0;i<1;i++){
            String jsonString = JsonGet.get("Const.json");
            JSONObject jsonObject = JSON.parseObject(jsonString);

            //连接到数据库
            JSONObject dbConnection = jsonObject.getJSONObject("DbConnection1");
            String url1=dbConnection.getString("url");
            String username1=dbConnection.getString("name");
            String password1=dbConnection.getString("password");
            Connection connection = Conn.getConnection(url1,username1,password1);
            TableOpera.createUserTable(connection);
            TableOpera.createEmailTable(connection);
            LoginView a = new LoginView(connection );
        }
        executor.shutdown();

        while (!executor.isTerminated()) {
            // 主线程在这里可以执行其他任务，或者只是简单地等待
        }
    }

}