package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyDate {

    public static String getNowInDateTime(){
        // 获取当前时间的Date对象
        java.util.Date currentDate = new Date();

        // 创建SimpleDateFormat对象，并设置日期时间格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // 使用SimpleDateFormat格式化日期时间Dhe

        return sdf.format(currentDate);
    }
}
