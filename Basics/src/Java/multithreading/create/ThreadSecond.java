package Java.multithreading.create;

//线程创建
//二：实现runnable接口创建线程
public class ThreadSecond implements Runnable {

    public ThreadSecond() {
        System.out.println("现在是 " + Thread.currentThread().getName() + " 线程");
    }

    //实现run方法
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " 线程启动");
        for (int i = 0; i < 5; i++) {
            System.out.println("我在编写代码----" + i + "----" + Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) {
        //创建runnable接口的实现类对象
        ThreadSecond threadSecond = new ThreadSecond();
        //创建线程对象，通过线程对象来开启我们的线程，代理
        new Thread(threadSecond).start();
    }
}
