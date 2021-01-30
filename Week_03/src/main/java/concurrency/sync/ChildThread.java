package concurrency.sync;

import java.util.concurrent.TimeUnit;

public class ChildThread implements Runnable {
    @Override
    public void run() {
        while (true) {
            System.out.println("I'm child thread..");
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}