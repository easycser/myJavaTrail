package thread.forkJoin;


import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

public class SumTask extends RecursiveTask<Integer> {

    private static final int THRESHOLD = 3; // fork任务的阈值

    private int start;
    private int end;

    public SumTask(int s, int e) {
        start = s;
        end = e;
    }

    @Override
    public Integer compute() {
        int sum = 0;
        boolean smallEnough = (end - start) <= THRESHOLD;
        if (smallEnough) {
            System.out.println("计算的加法区间为：" + start + "~" + end);
            for (int i = start; i <= end; i++) {
                sum += i;
            }
        } else {
            // 如果任务大于阈值，就分裂成多个子任务
            int mid = (start + end) / 2;
            SumTask task1 = new SumTask(start, mid);
            SumTask task2 = new SumTask(mid + 1, end);

            // 分别执行子任务
            task1.fork();   // fork-在当前任务正在运行的池中异步执行此任务，即在创建一个子任务
            task2.fork();

            if (task1.isCompletedAbnormally()) {
                System.out.println(task1.getException());
            }
            if (task2.isCompletedAbnormally()) {
                System.out.println(task2.getException());
            }

            // 等待子任务执行完并得到执行结果
            int task1Res = task1.join();    // join-当任务完成的时候返回计算结果
            int task2Res = task2.join();

            sum += task1Res + task2Res;
        }
        return sum;
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool(); // 用于执行调度分割的任务的一个线程池

        SumTask sumTask = new SumTask(1, 10);
        /**
         * execute-异步的方式执行任务，并且没有返回结果
         * submit-异步的方式执行，且返回结果，返回结果是封装后的Future对象，可以通过get方法获取结果
         * invoke/invokeAll-调用线程直到任务结束才会返回，即这是一个同步方法，并且返回结果
         */
        Future<Integer> res = forkJoinPool.submit(sumTask);
//        forkJoinPool.execute(sumTask);
//        Integer res2 = forkJoinPool.invoke(sumTask);
        try {
            System.out.println(res.get());
        } catch (Exception ignore) {}
    }
}
