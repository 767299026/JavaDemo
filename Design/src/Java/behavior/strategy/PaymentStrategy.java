package Java.behavior.strategy;

//抽象策略类
public abstract class PaymentStrategy {

    public abstract void pay(double amount);
}

//银行卡支付策略
class CreditPaymentStrategy extends PaymentStrategy {
    @Override
    public void pay(double amount) {
        System.out.println("使用银行卡支付了:" + amount + "元");
    }
}

//微信支付策略
class WechatPaymentStrategy extends PaymentStrategy {
    @Override
    public void pay(double amount) {
        System.out.println("使用微信支付了:" + amount + "元");
    }
}

//支付宝支付策略
class AlipayPaymentStrategy extends PaymentStrategy {
    @Override
    public void pay(double amount) {
        System.out.println("使用支付宝支付了:" + amount + "元");
    }
}