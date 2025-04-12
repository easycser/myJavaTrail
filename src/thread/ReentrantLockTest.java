package thread;

import java.util.PriorityQueue;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Buffer {
    private final Lock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();
    private final int BUFFER_SIZE = 10;
    private PriorityQueue<Integer> queue = new PriorityQueue<>(BUFFER_SIZE);

    public void put() {
        Random random = new Random();
        lock.lock();
        try {
            while (queue.size() == BUFFER_SIZE) {
                System.out.println(Thread.currentThread().getName() + "队列已满，生产者被阻塞");
                notFull.await();
            }
            queue.add(random.nextInt());
            Thread.sleep(1000);
            notEmpty.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void take() {
        lock.lock();
        try {
            while (queue.size() == 0) {
                System.out.println(Thread.currentThread().getName() + "队列为空，消费者被阻塞");
                notEmpty.await();
            }
            int d = queue.poll();
            System.out.println(Thread.currentThread().getName() + "消费的数字为：" + d);
            Thread.sleep(1000);
            notFull.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

class Producer implements Runnable {
    private Buffer buffer;

    public Producer(Buffer b) {
        buffer = b;
    }

    @Override
    public void run() {
        while (true) {
            buffer.put();
        }
    }
}

class Consumer implements Runnable {
    private Buffer buffer;

    public Consumer(Buffer b) {
        buffer = b;
    }

    @Override
    public void run() {
        while (true) {
            buffer.take();
        }
    }
}

public class ReentrantLockTest {
    public static void main(String[] args) {
        Buffer buffer = new Buffer();
        Producer producer = new Producer(buffer);
        Consumer consumer = new Consumer(buffer);
        for (int i = 0; i < 2; i++) {
            new Thread(producer, "生产者-" + i).start();
        }
        for (int i = 0; i < 3; i++) {
            new Thread(consumer, "消费者-" + i).start();
        }
    }
}
