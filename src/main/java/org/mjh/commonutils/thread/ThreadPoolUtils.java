package org.mjh.commonutils.thread;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Neo Lia
 * @date 2023/6/9 15:15
 */
public class ThreadPoolUtils {
    public static class NamedThreadFactory implements ThreadFactory {
        private final String namePrefix;
        private final ThreadGroup group;
        private final AtomicLong count;
        private final boolean daemon;

        public NamedThreadFactory(String namePrefix, boolean daemon) {
            this.namePrefix = namePrefix + "-thread-";
            this.daemon = daemon;
            this.count = new AtomicLong(0);
            SecurityManager securityManager = System.getSecurityManager();
            if (securityManager == null) {
                this.group = Thread.currentThread().getThreadGroup();
            }
            else {
                this.group = securityManager.getThreadGroup();
            }
        }

        public NamedThreadFactory(String namePrefix) {
            this(namePrefix, false);
        }

        @Override
        public Thread newThread(Runnable target) {
            final String name = this.namePrefix + this.count.incrementAndGet();
            Thread thread = new Thread(this.group, target, name);
            thread.setDaemon(this.daemon);
            return thread;
        }
    }

    /**
     * 构造自定义线程名称的ThreadFactory对象
     * @param threadName - 自定义线程名称
     * @return - NamedThreadFactory
     */
    public static NamedThreadFactory buildThreadFactory(String threadName) {
        return new NamedThreadFactory(threadName);
    }

    /**
     * 实例化新的线程池，线程数量需要结合实际应用场景设置。
     * - 理论线程数量 = CPU核数 * （1 + （IO等待时间 / CPU等待时间））
     * @param corePoolSize - 核心线程数
     * @param maximumPoolSize - 最大线程数
     * @param keepAliveTime - 空闲线程最大存活时间，单位：秒
     * @param workQueue - 工作队列
     * @param threadFactory - 线程工程，可用于定义线程名称
     * @param rejectedExecutionHandler - 拒绝执行任务策略
     * @return ExecutorService - 返回新的线程池对象实例
     * @author Neo Lia
     */
    public static ExecutorService newThreadPool(
            int corePoolSize, int maximumPoolSize, int keepAliveTime, BlockingQueue<Runnable> workQueue,
            ThreadFactory threadFactory, RejectedExecutionHandler rejectedExecutionHandler
    ) {
        return new ThreadPoolExecutor(
                corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, workQueue, threadFactory,
                rejectedExecutionHandler
        );
    }
}
