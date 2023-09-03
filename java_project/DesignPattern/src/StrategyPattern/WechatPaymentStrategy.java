package StrategyPattern;

public class WechatPaymentStrategy implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        System.out.println("微信支付: " + amount);
    }
}
