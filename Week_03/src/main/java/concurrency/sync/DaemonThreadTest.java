package concurrency.sync;

public class DaemonThreadTest {
    public static void main(String[] args) {
        Thread mainThread = new Thread(new Runnable() {
            @Override
            public void run() {
                //业务线程
                Thread childThread = new Thread(new ChildThread());
                childThread.setDaemon(false);
                childThread.start();
                System.out.println("I'm main thread...");
                //守护线程
                Thread daemonThread = new Thread(new DaemonThread());
                daemonThread.setDaemon(true);
                daemonThread.start();
            }
        });
        mainThread.start();
    }
}
