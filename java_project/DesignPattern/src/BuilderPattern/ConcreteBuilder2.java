package BuilderPattern;

public class ConcreteBuilder2 extends Builder {
    @Override
    public void buildPartA() {
        product.addPart("ConcreteBuilder2的partA");
    }

    @Override
    public void buildPartB() {
        product.addPart("ConcreteBuilder2的partB");
    }
}

