package BuilderPattern;

public abstract class Builder {

    protected Product product = new Product();

    // 对于Product产品，固定有PartA和PartB两个部件，生成这两个部件的方法通过实现类具体指定
    public abstract void buildPartA();

    public abstract void buildPartB();


    public Product getProduct() {
        return product;
    }
}
