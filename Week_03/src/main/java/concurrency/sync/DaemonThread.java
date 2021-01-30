package concurrency.sync;

import java.util.concurrent.TimeUnit;

/**
 * 守护线程的写法
 */
public class DaemonThread implements Runnable{

    @Override
    public void run() {
        while (true) {
            System.out.println("I'm daemon thread..");
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
