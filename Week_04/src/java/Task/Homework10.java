package Task;

import runner.Method;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

/**
 * 本周作业：（必做）思考有多少种方式，在main函数启动一个新线程或线程池，
 * 异步运行一个方法，拿到这个方法的返回值后，退出主线程？
 * <p>
 * <p>
 * 思路9，用集合收集主线程，借助工具类 LockSupport.park和unpark
 */
public class Homework10 {

    static Map<Integer, Thread> temp = new ConcurrentHashMap<>();

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        temp.put(1,Thread.currentThread());
        final int[] sum = {0};
        long start = System.currentTimeMillis();
        int answer = Method.sum();
        System.out.println("标准计算结果为：" + answer);
        new Thread(new Runnable() {
            @Override
            public void run() {
                sum[0] = Method.sum();
                LockSupport.unpark(temp.get(1));
            }
        }).start();
        LockSupport.park(temp.get(1));
        // 确保拿到result 并输出
        System.out.println("异步计算结果为：" + sum[0]);
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
        // 然后退出main线程
        return;
    }
}
