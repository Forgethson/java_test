package PrototypePattern;

public class Wool implements Cloneable {
    String color;
    double length;

    public Wool(String color, double length) {
        this.color = color;
        this.length = length;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "Wool{" +
                "color='" + color + '\'' +
                ", weight=" + length +
                '}';
    }

    @Override
    protected Object clone() {
        Wool wool = null;
        try {
            wool = (Wool) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wool;
    }
}
