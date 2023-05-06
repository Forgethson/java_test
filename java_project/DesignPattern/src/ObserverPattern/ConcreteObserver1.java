package ObserverPattern;

public class ConcreteObserver1 extends Observer {

    public ConcreteObserver1(Subject subject) {
        this.subject = subject;
//        subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println("ConcreteObserver1收到通知：" + subject.state);
    }
}
