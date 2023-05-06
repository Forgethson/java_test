package BuilderPattern;

public class ConcreteBuilder1 extends Builder {

    @Override
    public void buildPartA() {
        product.addPart("ConcreteBuilder1的partA");
    }

    @Override
    public void buildPartB() {
        product.addPart("ConcreteBuilder1的partB");
    }
}
