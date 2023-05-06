package ObserverPattern;

public class TestObserverPattern {
    public static void main(String[] args) {
        Subject subject = new Subject();
        subject.attach(new ConcreteObserver1(subject));
        subject.attach(new ConcreteObserver2(subject));
        // 状态改变的时候通知所有订阅的observer
        subject.setState("发布消息：xxx...xxx...");
    }
}
