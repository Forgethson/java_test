package ObserverPattern;

public class ConcreteObserver2 extends Observer {

    public ConcreteObserver2(Subject subject) {
        this.subject = subject;
//        subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println("ConcreteObserver2收到通知：" + subject.state);
    }
}
