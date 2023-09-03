package StrategyPattern;

public class TestStrategyPattern {
    public static void main(String[] args) {
        PaymentService paymentService1 = new PaymentService(new WechatPaymentStrategy());
        PaymentService paymentService2 = new PaymentService(new AlipayPaymentStrategy());

        // 和简单工厂模式结合：在PaymentService构造函数中输入支付方式，注入具体的PaymentStrategy实现类对象。对于if-else，可以通过反射机制来解决

        paymentService1.pay(100.d);
        paymentService2.pay(123.d);
    }
}
