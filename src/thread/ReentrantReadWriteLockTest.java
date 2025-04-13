package thread;

import java.util.concurrent.locks.ReentrantReadWriteLock;

class Demo {
    private ReentrantReadWriteLock rw1 = new ReentrantReadWriteLock();
    public void read() {
        rw1.readLock().lock();

        int i = 0;
        while (i++ < 100) {
            System.out.println(Thread.currentThread().getName() + "正在进行读操作");
        }
        System.out.println(Thread.currentThread().getName() + "读操作完毕");
        rw1.readLock().unlock();
    }
}

class DemoThread extends Thread {
    private Demo demo;
    public DemoThread(Demo d) {
        demo = d;
    }
    public void run() {
        demo.read();
    }
}

public class ReentrantReadWriteLockTest {
    public static void main(String[] args) {
        Demo demo = new Demo();
        DemoThread thread1 = new DemoThread(demo);
        DemoThread thread2 = new DemoThread(demo);
        thread1.start();
        thread2.start();
    }
}
