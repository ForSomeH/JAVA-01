package concurrency.operate;

public class WaitAndNotify {
    public static void main(String[] args) {
        MethodClass methodClass = new MethodClass();
        Thread t1 = new Thread(() -> {
            try {
                methodClass.product();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }, "生产者1");
        Thread t2 = new Thread(() -> {
            try {
                methodClass.customer();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }, "消费者1");
        Thread t3 = new Thread(() -> {
            try {
                methodClass.customer();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }, "消费者2");
        t1.start();
        t2.start();
        t3.start();

    }
}

class MethodClass {
    // 定义生产最大量
    private final int MAX_COUNT = 20;

    int productCount = 0;

    public synchronized void product() throws InterruptedException {
        while (true) {
            System.out.println(Thread.currentThread().getName() + ":::生产:::" + productCount);
            Thread.sleep(100);

            if (productCount >= MAX_COUNT) {
                System.out.println("货舱已满,,.不必再生产");

                wait();
            } else {
                productCount++;
            }
//notify();
            notifyAll();
        }
    }

    public synchronized void customer() throws InterruptedException {
        int goods=0;
        while (true) {
            System.out.println(Thread.currentThread().getName() + ":::消费:::" + productCount);
            Thread.sleep(50);
            if (goods>=5){
                System.out.println(Thread.currentThread().getName() +"已购5个，请下次再来");
                goods=0;
                wait();
            }
            if (productCount <= 0) {
                System.out.println("货舱已无货...无法消费");
                wait();
            } else {
                productCount--;
                goods++;
            }
notify();
//            notifyAll();
        }
    }
}