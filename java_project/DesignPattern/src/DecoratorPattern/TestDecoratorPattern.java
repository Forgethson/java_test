package DecoratorPattern;

public class TestDecoratorPattern {
    public static void main(String[] args) {
        ConcreteComponent c = new ConcreteComponent();
        ConcreteDecorator2 d = new ConcreteDecorator2(new ConcreteDecorator1(c));
        d.operation();
    }
}

