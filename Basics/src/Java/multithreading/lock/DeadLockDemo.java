package Java.multithreading.lock;

//死锁：多个线程互相抱着对方需要的资源，然后形成僵持
public class DeadLockDemo {
    public static void main(String[] args) {
        Makeup up1 = new Makeup(0, "灰姑娘");
        Makeup up2 = new Makeup(1, "红姑娘");

        up1.start();
        up2.start();
    }
}

//口红
class Lipstick {
}

//镜子
class Mirror {
}

class Makeup extends Thread {

    //需要的资源只有一份，用static来保证只有一份
    static Lipstick lipstick = new Lipstick();
    static Mirror mirror = new Mirror();

    int choice;//选择
    String girlName;//使用化妆品的人

    public Makeup(int choice, String girlName) {
        this.choice = choice;
        this.girlName = girlName;
    }

    @Override
    public void run() {
        //化妆
        try {
            makeup();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //化妆
    private void makeup() throws InterruptedException {
        if (choice == 0) {
            synchronized (lipstick) {//获得口红的锁
                System.out.println(this.girlName + "获得口红的锁");
                Thread.sleep(1000);

                synchronized (mirror) {//申请一秒钟后获得镜子的锁
                    System.out.println(this.girlName + "获得镜子的锁");
                }
            }

            synchronized (mirror) {//申请一秒钟后获得镜子的锁
                System.out.println(this.girlName + "获得镜子的锁");
            }

        } else {
            synchronized (mirror) {//获得口红的锁
                System.out.println(this.girlName + "获得镜子的锁");
                Thread.sleep(2000);

                synchronized (lipstick) {//申请一秒钟后获得口红的锁
                    System.out.println(this.girlName + "获得口红的锁");
                }
            }

            synchronized (lipstick) {//申请一秒钟后获得口红的锁
                System.out.println(this.girlName + "获得口红的锁");
            }
        }
    }


}