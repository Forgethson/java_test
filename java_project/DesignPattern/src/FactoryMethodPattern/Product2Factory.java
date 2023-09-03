package FactoryMethodPattern;

public class Product2Factory implements Factory {

    @Override
    public Product manufacture() {
        return new Product2();
    }
}
