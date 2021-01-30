package concurrency.threadFactory;

import java.util.concurrent.Callable;

public class WorkRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("完成一个任务");
    }
}
