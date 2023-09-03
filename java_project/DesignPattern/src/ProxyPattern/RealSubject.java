package ProxyPattern;

public class RealSubject extends Subject{
    @Override
    public void operation() {
        System.out.println("RealSubject: operation方法");
    }
}
