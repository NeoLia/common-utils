package org.mjh.commonutils.test;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mjh.commonutils.thread.ThreadPoolUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.*;

/**
 * @author Neo Lia
 * @date 2023/6/9 15:42
 */
public class ThreadPoolUtilsTest {
    @ParameterizedTest
    @CsvSource({"16", "9", "1"})
    public void test(int cpuCoreSize, String ioTimeStr, String cpuTimeStr) {
        BigDecimal ioTime = new BigDecimal(ioTimeStr);
        BigDecimal cpuTime = new BigDecimal(cpuTimeStr);
        int corePoolSize = cpuCoreSize * BigDecimal.ONE.add(ioTime.divide(cpuTime, RoundingMode.HALF_UP).setScale(2))
                .intValue();
        int maximumPoolSize = corePoolSize * 2;
        int keepAliveTime = 30;
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(10);
        ThreadFactory threadFactory = ThreadPoolUtils.buildThreadFactory("test");
        ThreadPoolExecutor.AbortPolicy rejectedExecutionPolicy = new ThreadPoolExecutor.AbortPolicy();

        ExecutorService threadPool = ThreadPoolUtils.newThreadPool(
                corePoolSize, maximumPoolSize, keepAliveTime, workQueue, threadFactory, rejectedExecutionPolicy
        );
    }
}
