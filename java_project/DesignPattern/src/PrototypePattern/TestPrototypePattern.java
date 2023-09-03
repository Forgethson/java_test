package PrototypePattern;

public class TestPrototypePattern {
    public static void main(String[] args) {
        Wool wool1 = new Wool("yellow", 200.0);
        Wool wool2 = (Wool) wool1.clone();

        System.out.println(wool1);
        System.out.println(wool2);

        Sheep sheep1 = new Sheep("Mary", 1200.0, wool1);
        Sheep sheep2 = (Sheep) sheep1.clone();

        wool1.setColor("blue");
        System.out.println(sheep1);
        System.out.println(sheep2);
    }
}
