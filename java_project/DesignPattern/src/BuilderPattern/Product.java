package BuilderPattern;

import java.util.ArrayList;
import java.util.List;

public class Product {
    List<String> parts = new ArrayList<>();

    public void addPart(String part) {
        parts.add(part);
    }

    public void show() {
        System.out.println("创建产品：");
        for (String part : parts) {
            System.out.println(part);
        }
    }
}
