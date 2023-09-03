package DecoratorPattern;

public class ConcreteDecorator2 extends Decorator {
    //定义被修饰者
    public ConcreteDecorator2(Component component) {
        super(component);
    }

    //定义自己的修饰方法
    private void method() {
        System.out.println("ConcreteDecorator2 修饰");
    }

    @Override
    public void operation() {
        this.method();
        super.operation();
    }
}
