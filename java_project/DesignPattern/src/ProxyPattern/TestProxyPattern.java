package ProxyPattern;

public class TestProxyPattern {

    public static void main(String[] args) {
        Proxy proxy = new Proxy(new RealSubject());
        proxy.operation();
    }
}
