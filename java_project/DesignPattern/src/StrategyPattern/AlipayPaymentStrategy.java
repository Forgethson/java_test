package StrategyPattern;

public class AlipayPaymentStrategy implements PaymentStrategy{
    @Override
    public void pay(double amount) {
        System.out.println("支付宝支付: " + amount);
    }
}
