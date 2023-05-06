package BuilderPattern;

public class TestBuilderPattern {
    public static void main(String[] args) {
        Director director = new Director();
        ConcreteBuilder1 concreteBuilder1 = new ConcreteBuilder1();
        ConcreteBuilder2 concreteBuilder2 = new ConcreteBuilder2();

        // 通过director创建产品（外观类解耦）
        Product product1 = director.constructProduct(concreteBuilder1);
        Product product2 = director.constructProduct(concreteBuilder2);

        product1.show();
        product2.show();
    }
}
