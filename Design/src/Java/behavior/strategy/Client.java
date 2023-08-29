package Java.behavior.strategy;

//策略模式使用类
public class Client {
    public static void main(String[] args) {

        PaymentService.pay(PaymentType.CREDIT, 100);

        PaymentService.pay(PaymentType.WECHAT, 200);

        PaymentService.pay(PaymentType.ALIPAY, 300);
    }
}
