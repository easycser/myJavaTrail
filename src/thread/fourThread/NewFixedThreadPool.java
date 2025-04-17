package thread.fourThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NewFixedThreadPool {
    public static void main(String[] args) {
        /**
         * 定长线程池，可控制线程的最大并发数
         */
        ExecutorService pool = Executors.newFixedThreadPool(2);
        pool.execute(new MyThread());
        pool.execute(new MyThread());
        pool.execute(new MyThread());
        pool.execute(new MyThread());
        pool.shutdown();
    }
}
