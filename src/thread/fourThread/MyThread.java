package thread.fourThread;

class MyThread extends Thread {
    public void run() {
        System.out.println(Thread.currentThread().getId() + "run");
    }
}
