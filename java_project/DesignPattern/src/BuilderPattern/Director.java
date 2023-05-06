package BuilderPattern;

// Facade Class
public class Director {
    public Product constructProduct(Builder builder) {
        builder.buildPartA();
        builder.buildPartB();
        return builder.getProduct();
    }
}
