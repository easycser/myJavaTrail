package thread.fourThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NewCachedThreadPool {
    public static void main(String[] args) {
        /**
         * 可缓存线程池
         */
        ExecutorService pool = Executors.newCachedThreadPool();
        pool.execute(new MyThread());
        pool.execute(new MyThread());
        pool.execute(new MyThread());
        pool.execute(new MyThread());
        pool.shutdown();
    }
}
