package DecoratorPattern;

public class Decorator extends Component {
    private Component component;

    //通过构造函数传递给被修饰者
    public Decorator(Component component) {
        this.component = component;
    }

    //委托给被修饰者执行
    @Override
    public void operation() {
        if (component != null) {
            this.component.operation();
        }
    }
}

