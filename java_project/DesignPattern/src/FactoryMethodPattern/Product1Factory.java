package FactoryMethodPattern;

public class Product1Factory implements Factory{

    @Override
    public Product manufacture() {
        return new Product1();
    }
}
