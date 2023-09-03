package DecoratorPattern;

public class ConcreteComponent extends Component {
    @Override
    public void operation() {
        System.out.println("被修饰的具体组件：操作");
    }
}
