package utils;

import java.util.concurrent.*;

//单例模式
public class MyThreadPool {

    //构造唯一实例
    public static ThreadPoolExecutor getThreadPool(){
        return new ThreadPoolExecutor(
                corePoolSize=8,
                maxPoolSize=16,
                keepAliveTime=1,
                keepAliveTimeUnit=TimeUnit.SECONDS,
                workQueue = new LinkedBlockingDeque<>(),
                threadFactory = Executors.defaultThreadFactory()
        );
    }

//    private MyThreadPool(int corePoolSize,
//                         int maxPoolSize,
//                         long keepAliveTime,
//                         TimeUnit keepAliveTimeUnit) {
//        // 检查参数是否合理
//        if (corePoolSize < 0 || corePoolSize > maxPoolSize) {
//            throw new IllegalArgumentException("核心线程不能大于最大线程数");
//        }
//        if (keepAliveTime < 0) {
//            throw new IllegalArgumentException("");
//        }
//        //数据分配
//        MyThreadPool.corePoolSize = corePoolSize;
//        MyThreadPool.maxPoolSize = maxPoolSize;
//        MyThreadPool.keepAliveTime = keepAliveTime;
//        MyThreadPool.keepAliveTimeUnit = keepAliveTimeUnit;
//        MyThreadPool.workQueue = new LinkedBlockingDeque<>();
//        MyThreadPool.threadFactory = Executors.defaultThreadFactory();
//        MyThreadPool.rejectedHandler = new java.util.concurrent.ThreadPoolExecutor.DiscardPolicy();
//    }    // 私有化构造函数


    private static int corePoolSize;//核心线程数
    private static int maxPoolSize;//最大线程数
    private static long keepAliveTime;//线程最长闲置时间
    private static TimeUnit keepAliveTimeUnit;//指定 keepAliveTime 参数的时间单位
    private static BlockingQueue<Runnable> workQueue;//任务队列。通过线程池的 execute() 方法提交的 Runnable 对象将存储在该参数中。
    private static ThreadFactory threadFactory;//线程工厂
    private static RejectedExecutionHandler rejectedHandler;//阻塞队列，当队列阻塞时的方法

    public static int getCorePoolSize() {
        return corePoolSize;
    }

    public static void setCorePoolSize(int corePoolSize) {
        MyThreadPool.corePoolSize = corePoolSize;
    }

    public static int getMaxPoolSize() {
        return maxPoolSize;
    }

    public static void setMaxPoolSize(int maxPoolSize) {
        MyThreadPool.maxPoolSize = maxPoolSize;
    }

    public static long getKeepAliveTime() {
        return keepAliveTime;
    }

    public static void setKeepAliveTime(long keepAliveTime) {
        MyThreadPool.keepAliveTime = keepAliveTime;
    }

    public static TimeUnit getKeepAliveTimeUnit() {
        return keepAliveTimeUnit;
    }

    public static void setKeepAliveTimeUnit(TimeUnit keepAliveTimeUnit) {
        MyThreadPool.keepAliveTimeUnit = keepAliveTimeUnit;
    }

    public static BlockingQueue<Runnable> getWorkQueue() {
        return workQueue;
    }

    public static void setWorkQueue(BlockingQueue<Runnable> workQueue) {
        MyThreadPool.workQueue = workQueue;
    }

    public static ThreadFactory getThreadFactory() {
        return threadFactory;
    }

    public static void setThreadFactory(ThreadFactory threadFactory) {
        MyThreadPool.threadFactory = threadFactory;
    }

    public static RejectedExecutionHandler getRejectedHandler() {
        return rejectedHandler;
    }

    public static void setRejectedHandler(RejectedExecutionHandler rejectedHandler) {
        MyThreadPool.rejectedHandler = rejectedHandler;
    }
}
