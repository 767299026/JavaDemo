package Java.multithreading.status;

//观测线程优先级
public class TestThreadSecond implements Runnable {

    //实现run方法，获取当前线程名称和优先级
    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println(Thread.currentThread().getName() + "---" + i + "-->" + Thread.currentThread().getPriority());
        }
    }

    public static void main(String[] args) {

        //main线程信息,默认优先级5
        System.out.println(Thread.currentThread().getName() + "-->" + Thread.currentThread().getPriority());

        //创建几个线程
        TestThreadSecond thread = new TestThreadSecond();
        Thread t1 = new Thread(thread, "t1");
        Thread t2 = new Thread(thread, "t2");
        Thread t3 = new Thread(thread, "t3");
        Thread t4 = new Thread(thread, "t4");

        //设置优先级，再启动
        t1.start();//默认优先级5
        t2.setPriority(1);
        t2.start();
        t3.setPriority(4);
        t3.start();
        t4.setPriority(Thread.MAX_PRIORITY);//最大优先级10
        t4.start();
    }
}
