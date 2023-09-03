package SimpleFactoryPattern;

public class TestSimpleFactoryPattern {
    public static void main(String[] args) {
        Product product1 = Factory.manufacture("1");
        Product product2 = Factory.manufacture("2");
        Product product3 = Factory.manufacture("3");

        try {
            product1.show();
        } catch (NullPointerException e) {
            System.out.println("没有此类型的产品");
        }
        try {
            product2.show();
        } catch (NullPointerException e) {
            System.out.println("没有此类型的产品");
        }
        try {
            product3.show();
        } catch (NullPointerException e) {
            System.out.println("没有此类型的产品");
        }
    }
}
