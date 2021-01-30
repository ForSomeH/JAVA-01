package Task;

import runner.Method;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 本周作业：（必做）思考有多少种方式，在main函数启动一个新线程或线程池，
 * 异步运行一个方法，拿到这个方法的返回值后，退出主线程？
 * <p>
 * <p>
 * 思路7，建立锁lock，通过信号通知
 */
public class Homework07 {
    private static Lock lock;
    private static Condition condition1;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        lock = new ReentrantLock();
        condition1 = lock.newCondition();
        final int[] sum = {0};
        long start = System.currentTimeMillis();
        int answer = Method.sum();
        System.out.println("标准计算结果为：" + answer);
        new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                sum[0] = Method.sum();
                condition1.signal();
                lock.unlock();
            }
        }).start();
        lock.lock();
        condition1.await();
        // 确保拿到result 并输出
        System.out.println("异步计算结果为：" + sum[0]);
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
        // 然后退出main线程
        return;
    }
}
