package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class JsonGet {
    /**
     * 获取到resources下的json文件
     * @param name
     * @return 返回文件字符串
     */
    public static String get(String name){
        ClassLoader classLoader = JsonGet.class.getClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream(name)){
            if(inputStream == null){
                throw new IllegalArgumentException(name+" not found");
            }
            Scanner scanner = new Scanner(inputStream).useDelimiter("\\A");
            return scanner.hasNext() ? scanner.next() : "";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
