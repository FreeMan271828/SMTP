package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyLog {

    private static MyLog instance;
    private final Logger logger;

    private MyLog() {
        logger = LoggerFactory.getLogger(MyLog.class);
    }

    public static MyLog getInstance() {
        if (instance == null) {
            instance = new MyLog();
        }
        return instance;
    }

    public void info(String message) {
        logger.info(message);
    }

    public void error(String message) {
        logger.error(message);
    }

    // 其他方法，例如 debug、warn 等
}