package utils;

import java.sql.Time;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutor {

    private static int corePoolSize;
    private static int maxPoolSize;
    private static long keepAliveTime;
    private static TimeUnit keepAliveTimeUnit;
    private static BlockingQueue<Runnable> workQueue;
    private static ThreadFactory threadFactory;
    private static RejectedExecutionHandler rejectedHandler;

    // 构造函数
    public ThreadPoolExecutor(int corePoolSize,
                              int maxPoolSize,
                              long keepAliveTime,
                              TimeUnit keepAliveTimeUnit,
                              BlockingQueue<Runnable> workQueue,
                              ThreadFactory threadFactory,
                              RejectedExecutionHandler rejectedHandler) {
        // 检查参数是否合理
        if (corePoolSize < 0 || corePoolSize > maxPoolSize) {
            throw new IllegalArgumentException("核心线程不能大于最大线程数");
        }
        if (keepAliveTime < 0) {
            throw new IllegalArgumentException("");
        }
        corePoolSize = corePoolSize;
        maxPoolSize = maxPoolSize;
        keepAliveTime = keepAliveTime;
        keepAliveTimeUnit = keepAliveTimeUnit;
        workQueue = workQueue;
        threadFactory = threadFactory;
        rejectedHandler = rejectedHandler;
    }
}
