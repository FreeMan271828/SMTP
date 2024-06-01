import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import thread.UserThread;
import utils.JsonGet;
import data_access.Conn;
import data_access.TableOpera;
import utils.MyThreadPool;

import javax.mail.MessagingException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws SQLException, InterruptedException {
        String jsonString = JsonGet.get("Const.json");
        JSONObject jsonObject = JSON.parseObject(jsonString);

        //连接到数据库
        JSONObject dbConnection = jsonObject.getJSONObject("DbConnection1");
        String url1=dbConnection.getString("url");
        String username1=dbConnection.getString("name");
        String password1=dbConnection.getString("password");
        Connection connection = Conn.getConnection(url1,username1,password1);
        //表操作
        TableOpera.createUserTable(connection);
        TableOpera.createEmailTable(connection);

        //获取线程池
        ExecutorService executor =  MyThreadPool.getThreadPool();

        //多线程测试时间
        long startTime = System.currentTimeMillis();

        //进行多线程
        for(int i=0;i<4;i++){
            UserThread userThread = new UserThread();
            userThread.setConnection(connection);
            executor.execute(userThread);
        }
        executor.shutdown();

        //多线程测试时间
        if (!executor.awaitTermination(1, TimeUnit.HOURS)) {
            System.err.println("Not all tasks completed in time.");
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Multi-threaded execution time: " + (endTime - startTime) + " ms");

    }
}