package utils;

import java.util.concurrent.*;

//单例模式
public class MyThreadPool {

    //构造唯一实例
    public MyThreadPool getThreadPool(){
        return new MyThreadPool(4,8,1,TimeUnit.SECONDS);
    }

    // 私有化构造函数
    private MyThreadPool(int corePoolSize,
                         int maxPoolSize,
                         long keepAliveTime,
                         TimeUnit keepAliveTimeUnit) {
        // 检查参数是否合理
        if (corePoolSize < 0 || corePoolSize > maxPoolSize) {
            throw new IllegalArgumentException("核心线程不能大于最大线程数");
        }
        if (keepAliveTime < 0) {
            throw new IllegalArgumentException("");
        }
        //数据分配
        MyThreadPool.corePoolSize = corePoolSize;
        MyThreadPool.maxPoolSize = maxPoolSize;
        MyThreadPool.keepAliveTime = keepAliveTime;
        MyThreadPool.keepAliveTimeUnit = keepAliveTimeUnit;
        MyThreadPool.workQueue = new LinkedBlockingDeque<>();
        MyThreadPool.threadFactory = Executors.defaultThreadFactory();
        MyThreadPool.rejectedHandler = new ThreadPoolExecutor.DiscardPolicy();

    }

    private static int corePoolSize;//核心线程数
    private static int maxPoolSize;//最大线程数
    private static long keepAliveTime;//线程最长闲置时间
    private static TimeUnit keepAliveTimeUnit;//指定 keepAliveTime 参数的时间单位
    private static BlockingQueue<Runnable> workQueue;//任务队列。通过线程池的 execute() 方法提交的 Runnable 对象将存储在该参数中。
    private static ThreadFactory threadFactory;//线程工厂
    private static RejectedExecutionHandler rejectedHandler;//阻塞队列，当队列阻塞时的方法
}
