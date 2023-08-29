package Java.multithreading.demo.safe;

//通过synchronized实现安全
public class SafeBank {
    public static void main(String[] args) {
        Account account = new Account(100, "零钱");

        Drawing First = new Drawing(account, 50, "小明");
        Drawing Two = new Drawing(account, 100, "小红");

        First.start();
        Two.start();

    }
}

class Account {
    int money;
    String name;

    public Account(int money, String name) {
        this.money = money;
        this.name = name;
    }
}

class Drawing extends Thread {

    Account account;//账户
    //取了多少钱
    int drawingMoney;
    //手中的钱
    int nowMoney;

    public Drawing(Account account, int drawingMoney, String name) {
        super(name);
        this.account = account;
        this.drawingMoney = drawingMoney;
    }

    @Override
    public void run() {

        synchronized (account) {
            if (account.money - drawingMoney < 0) {
                System.out.println(Thread.currentThread().getName() + "余额不足！");
                return;
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            account.money = account.money - drawingMoney;

            nowMoney = nowMoney + drawingMoney;

            System.out.println(account.name + "余额为:" + account.money);
            System.out.println(this.getName() + "手中的钱为" + nowMoney);
        }

    }
}