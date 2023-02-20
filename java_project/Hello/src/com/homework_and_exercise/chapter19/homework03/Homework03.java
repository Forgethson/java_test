package com.homework_and_exercise.chapter19.homework03;

import org.junit.Test;

import java.io.*;
import java.util.Properties;

/**
 * @author Jianda Wang
 * @version 1.0
 * 2022/12/18
 * 3.编程题Homework03.java5min
 * （1）要编写一个dog-properties name=tom age=5 color=red
 * （2）编写Dog类（name，age，color）创健一个dog对象，读取dog.properties用相应的内容完成属性初始化，并输出
 * （3）将创建的Dog对像，序列化到文件dog.dat文件
 */
public class Homework03 {
    public static void main(String[] args) throws Exception{

        String path = "src\\dog.properties";
        Properties properties = new Properties();

        // 这里只能用 Reader
        properties.load(new FileReader(path));
        properties.list(System.out);

        Dog dog = new Dog();
        dog.setAge(Integer.parseInt(properties.getProperty("age")));
        dog.setName(properties.getProperty("name"));
        dog.setColor(properties.getProperty("color"));

        // 用.get()也行，不过返回的是Object，需要先转为String

        System.out.println("=======Dog对象信息========");
        System.out.println(dog);

        String path1 = "src\\dog.dat";
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path1));
        oos.writeObject(dog);
        oos.close();
    }

    // 将创建的 dog.dat 文件反序列化
    @Test
    public void fun() throws Exception {
        String path = "src\\dog.dat";
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
        Dog dog = new Dog();
        dog = (Dog) ois.readObject();
        System.out.println("====反序列化后的===");
        System.out.println(dog);

    }
}

class Dog implements Serializable {
    private String name;
    private int age;
    private String color;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", color='" + color + '\'' +
                '}';
    }
}