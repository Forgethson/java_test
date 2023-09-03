package SimpleFactoryPattern;

public class Factory {
    public static Product manufacture(String type) {
        switch (type) {
            case "1":
                return new Product1();
            case "2":
                return new Product2();
            default:
                return null;
        }
    }
}
