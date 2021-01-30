package concurrency;

import concurrency.runner.RunnerExtend;
import concurrency.runner.RunnerImpl;

public class ThreadTest {
    public static void main(String[] args) {
        new RunnerExtend().start();
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + "执行" + i);
        }
        new Thread(new RunnerImpl()).start();
    }
}
