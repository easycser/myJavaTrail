package thread.fourThread;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

class ScheduledThread extends MyThread {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getId() + " timestamp:" + System.currentTimeMillis());
    }
}

public class NewScheduledThreadPool {
    public static void main(String[] args) {
        /**
         * 定长线程池，支持定时以及周期性执行任务的需求
         */
        ScheduledExecutorService exec = new ScheduledThreadPoolExecutor(2);
        // 每隔一段时间执行一次
        exec.scheduleAtFixedRate(new ScheduledThread(), 0, 3000, TimeUnit.MILLISECONDS);
        exec.scheduleAtFixedRate(new ScheduledThread(), 0, 2000, TimeUnit.MILLISECONDS);
    }
}
