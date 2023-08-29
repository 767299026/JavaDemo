package Java.multithreading.create;

//线程创建
//一：继承Thread类创建线程
public class ThreadFirst extends Thread {

    public ThreadFirst() {
        System.out.println("现在是 " + Thread.currentThread().getName() + " 线程");
    }

    //重写run方法
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " 线程启动");
        for (int i = 0; i < 5; i++) {
            System.out.println("我在编写代码----" + i + "----" + Thread.currentThread().getName());
        }
    }


    public static void main(String[] args) {
        ThreadFirst thread = new ThreadFirst();
        //启动线程
        thread.start();
    }
}
