package ProxyPattern;

public class Proxy extends Subject {

    Subject realSubject;

    Proxy(Subject realSubject) {
        this.realSubject = realSubject;
    }

    @Override
    public void operation() {
        if (realSubject != null) {
            try {
                System.out.println("proxy: 前置通知");
                realSubject.operation();
                System.out.println("proxy: 返回通知");
            } catch (Exception e) {
                System.out.println("proxy: 异常通知");
            } finally {
                System.out.println("proxy: 后置通知");
            }
        }
    }
}
