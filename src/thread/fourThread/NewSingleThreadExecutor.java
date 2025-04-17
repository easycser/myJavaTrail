package thread.fourThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NewSingleThreadExecutor {
    public static void main(String[] args) {
        /**
         * 单线程的线程池（串行执行）
         */
        ExecutorService pool = Executors.newSingleThreadExecutor();
        pool.execute(new MyThread());
        pool.execute(new MyThread());
        pool.execute(new MyThread());
        pool.execute(new MyThread());
        pool.shutdown();
    }
}
