package Java.multithreading.status;

//观察线程生命周期的几个状态
public class TestThreadFist implements Runnable {

    public TestThreadFist() {
        System.out.println("现在是 " + Thread.currentThread().getName() + " 线程");
    }

    //实现run方法
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " 线程启动");
        for (int i = 0; i < 100; i++) {
            if (i == 25) {
                try {
                    Thread.sleep(2);//sleep导致线程进入阻塞状态
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("我在编写代码----" + i + "----" + Thread.currentThread().getName());
        }
    }

    //更新并打印线程状态
    static void print(Thread t0, Thread t1) {
        s0 = t0.getState();
        s1 = t1.getState();
        System.out.println("线程t0的状态是： " + s0);
        System.out.println("线程t1的状态是： " + s1);
    }

    static volatile Thread.State s0;
    static volatile Thread.State s1;

    public static void main(String[] args) {

        TestThreadFist thread = new TestThreadFist();
        //新建线程
        Thread t0 = new Thread(thread);
        Thread t1 = new Thread(thread);
        print(t0, t1);// 此时输出 NEW ,即初始状态
        //启动线程
        t0.start();
        t1.start();
        //更新状态
        print(t0, t1);//此时输出 RUNNABLE ,start()时为就绪状态,调用run()后为运行状态


        while (true) {//线程状态跳变时，打印线程状态
            Thread.State temp0 = t0.getState();
            Thread.State temp1 = t1.getState();
            if (temp0 != s0 || temp1 != s1)
                print(t0, t1);
            if (temp0 == Thread.State.TERMINATED && temp1 == Thread.State.TERMINATED)
                break;//线程结束，死亡
        }
    }
}