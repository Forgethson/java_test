package PrototypePattern;

public class Sheep implements Cloneable {
    String name;
    double weight;
    Wool wool;

    public Sheep(String name, double weight, Wool wool) {
        this.name = name;
        this.weight = weight;
        this.wool = wool;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Wool getWool() {
        return wool;
    }

    public void setWool(Wool wool) {
        this.wool = wool;
    }

    @Override
    public String toString() {
        return "Sheep{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                ", wool=" + wool +
                '}';
    }

    @Override
    protected Object clone() {
        Sheep sheep = null;
        try {
            sheep = (Sheep) super.clone();
            sheep.setWool((Wool) wool.clone());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sheep;
    }
}
