package Java.multithreading.status;

//测试线程中止
public class TestThreadFourth implements Runnable {

    //1.设置标志位
    private boolean flag = true;

    @Override
    public void run() {
        int i = 0;
        while (flag) {
            System.out.println(Thread.currentThread().getName() + "---" + i++);
        }
    }

    //2.设置一个公开的方法停止线程，转换标志位
    public void end() {
        this.flag = false;
    }

    public static void main(String[] args) {
        TestThreadFourth thread = new TestThreadFourth();
        Thread t = new Thread(thread);
        t.setPriority(Thread.MAX_PRIORITY);
        t.start();

        for (int i = 0; i < 100; i++) {
            System.out.println("main...Thread" + i);
            if (i == 40) {//当主线程执行到一定条件时,调用end方法,切换标志位，令目标线程停止
                thread.end();
                System.out.println("目标线程该停止了");
                return;
            }
        }

    }
}
