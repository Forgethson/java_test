package FactoryMethodPattern;

public class TestFactoryMethodPattern {
    public static void main(String[] args) {
        Product product1 = new Product1Factory().manufacture();
        Product product2 = new Product2Factory().manufacture();
        product1.show();
        product2.show();
    }
}
