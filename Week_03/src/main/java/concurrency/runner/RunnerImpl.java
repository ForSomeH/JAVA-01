package concurrency.runner;

/**
 * 接口的方法实现
 */
public class RunnerImpl implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + "执行" + i);
        }
    }
}
