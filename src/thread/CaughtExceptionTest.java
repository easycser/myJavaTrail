package thread;

class ThreadException extends Thread {
    public void run() {
        int i = 1 / 0;
    }
    public void simulateCleanup() {
        System.out.println("simulate Clean up");
    }

}

class ErrHandler implements Thread.UncaughtExceptionHandler {
    public void uncaughtException(Thread a, Throwable e) {
        System.out.println("This is:" + a.getName() + ",Message:" + e.getMessage());
        e.printStackTrace();
        ((ThreadException) a).simulateCleanup();
    }
}

public class CaughtExceptionTest {
    public static void main(String[] args) {
        Thread thread = new ThreadException();
        try {
            Thread.UncaughtExceptionHandler handler = new ErrHandler();
            thread.setUncaughtExceptionHandler(handler);
            thread.start();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


//        // 运行时异常无法被try/catch捕获
//        Thread thread = new ThreadException();
//        try {
//            thread.start();
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
    }
}
