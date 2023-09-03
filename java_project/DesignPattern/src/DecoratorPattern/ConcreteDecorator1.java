package DecoratorPattern;

public class ConcreteDecorator1 extends Decorator {
    //定义被修饰者
    public ConcreteDecorator1(Component component) {
        super(component);
    }

    //定义自己的修饰方法
    private void method() {
        System.out.println("ConcreteDecorator1 修饰");
    }

    @Override
    public void operation() {
        this.method();
        super.operation();
    }
}
