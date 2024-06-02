import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import data_access.Add;
import data_object.User;
import thread.UserTask;
import utils.JsonGet;
import data_access.Conn;
import data_access.TableOpera;
import utils.MyThreadPool;

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

        long startTime = System.currentTimeMillis();
        //进行多线程
        for(int i=0;i<4;i++){
            UserTask userTask = new UserTask();
            userTask.setConnection(connection);
            executor.execute(userTask);
        }
        executor.shutdown();

        while (!executor.isTerminated()) {
            // 主线程在这里可以执行其他任务，或者只是简单地等待
        }

        // 记录结束时间
        long endTime = System.currentTimeMillis();

        // 计算执行时间
        long executionTime = endTime - startTime;

        // 打印执行时间
        System.out.println("代码执行时间：" + executionTime + "毫秒");
    }

}