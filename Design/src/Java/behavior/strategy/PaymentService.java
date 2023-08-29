package Java.behavior.strategy;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

//支付业务逻辑层
public class PaymentService {

    //维护一个map缓存策略(用于判断策略是否存在)
    private static final Map<PaymentType, Supplier<PaymentStrategy>> strategyMap = new HashMap<>();

    static {
        strategyMap.put(PaymentType.CREDIT, CreditPaymentStrategy::new);
        strategyMap.put(PaymentType.WECHAT, WechatPaymentStrategy::new);
        strategyMap.put(PaymentType.ALIPAY, AlipayPaymentStrategy::new);
        /*----------------------此处添加新的支付策略---------------------*/
    }

    //使用指定策略进行支付
    public static void pay(PaymentType paymentType, double amount) {
        if (paymentType == null || strategyMap.get(paymentType) == null) {
            throw new IllegalArgumentException("该支付方式无法使用");
        }
        PaymentStrategy paymentStrategy = strategyMap.get(paymentType).get();
        paymentStrategy.pay(amount);
    }
}
