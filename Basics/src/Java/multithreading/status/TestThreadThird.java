package Java.multithreading.status;

//观测线程礼让和线程插入
public class TestThreadThird implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i == 20) {
                System.out.println("尝试让 " + Thread.currentThread().getName() + "进行礼让");
                Thread.yield();//礼让不一定成功
            }
            System.out.println(Thread.currentThread().getName() + "---" + i);
        }
    }

    public static void main(String[] args) {
        TestThreadThird thread = new TestThreadThird();
        Thread t = new Thread(thread);
        t.setPriority(Thread.MAX_PRIORITY);
        t.start();
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + "---" + i);
            if (i == 40) {
                try {
                    System.out.println("开始等待其余线程执行完毕");
                    t.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
