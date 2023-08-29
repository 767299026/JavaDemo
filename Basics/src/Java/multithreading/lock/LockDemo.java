package Java.multithreading.lock;

import java.util.concurrent.locks.ReentrantLock;

//可重入锁实现多线程安全买票
public class LockDemo {
    public static void main(String[] args) {
        Lock lock = new Lock();

        new Thread(lock, "up1").start();
        new Thread(lock, "up2").start();
        new Thread(lock, "up3").start();
    }
}

class Lock implements Runnable {

    int ticketNums = 10;

    private final ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true) {

            try {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.lock();
                if (ticketNums > 0) {
                    System.out.println(Thread.currentThread().getName() + "当前是:" + ticketNums--);
                } else
                    break;
            } finally {
                lock.unlock();
            }


        }
    }
}
