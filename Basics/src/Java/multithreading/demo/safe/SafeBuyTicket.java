package Java.multithreading.demo.safe;

//通过synchronized实现安全
public class SafeBuyTicket {

    public static void main(String[] args) {
        BuyTicket station = new BuyTicket();

        new Thread(station, "小明").start();
        new Thread(station, "小红").start();
    }
}

class BuyTicket implements Runnable {

    int ticketNums = 15;
    boolean flag = true;

    @Override
    public void run() {
        while (flag) {
            buy();
        }
    }

    //锁
    private synchronized void buy() {
        if (ticketNums <= 0) {
            flag = false;
            return;
        }
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + "拿到了" + ticketNums-- + "票");
    }
}
