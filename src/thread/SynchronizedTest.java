package thread;

class Test {
    public static void synchronizedMethod() {   // 对象锁
        System.out.println("begin calling synchronizedMethod");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("finish calling synchronizedMethod");
    }

    public synchronized static void generalMethod() {   // 类锁
        System.out.println("call generalMethod ...");
    }
}

public class SynchronizedTest {

    static final Test t = new Test();

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> t.synchronizedMethod());
        Thread thread2 = new Thread(() -> t.generalMethod());
        thread1.start();
        thread2.start();
    }
}
