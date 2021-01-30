package concurrency.threadpool;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class MyThreadPool {


    public static void main(String[] args) {
        ThreadFactory threadFactory = new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return null;
            }
        };

        ExecutorService executorService = Executors.newFixedThreadPool(15);

        for (int i = 0; i < 100; i++) {
            final int no = i;
            Runnable runnable = () -> {

            };

            executorService.execute(threadFactory.newThread(runnable));
        }
    }




}
