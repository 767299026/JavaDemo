package Java.multithreading.create;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

//线程创建
//三：通过Callable创建线程
public class ThreadThird implements Callable<String> {

    String message;

    public ThreadThird(String message) {
        this.message = message;
        System.out.println("现在是 " + Thread.currentThread().getName() + " 线程");
    }

    //实现call方法
    @Override
    public String call() throws Exception {
        System.out.println(Thread.currentThread().getName() + " 线程进入了call方法");
        for (int i = 0; i < 5; i++) {
            System.out.println("我在编写代码----" + i + "----" + Thread.currentThread().getName());
        }
        return this.message;
    }

    public static void main(String[] args) {
        ThreadThird thread = new ThreadThird("有返回值的线程");
        //通过futureTask类使用其传递Callable接口作为参数的有参构造方法
        FutureTask<String> futureTask = new FutureTask<>(thread);
        //启动线程
        new Thread(futureTask).start();
        try {
            //futureTask.get()方法获取线程返回值
            System.out.println("线程返回值：" + futureTask.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
