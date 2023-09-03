package StrategyPattern;

// context class
public class PaymentService {
    private PaymentStrategy paymentStrategy;

    PaymentService(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void pay(double amount) {
        paymentStrategy.pay(amount);
    }
}
