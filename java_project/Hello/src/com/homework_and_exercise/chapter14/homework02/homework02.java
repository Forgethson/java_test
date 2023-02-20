package com.homework_and_exercise.chapter14.homework02;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author Jianda Wang
 * @version 1.0
 * 2022/12/14
 * 使用ArrayList完成对对象Car{name，price}的各种操作
 * 1.add：添加单个元素
 * 2.remove：删除指定元素
 * Carcar=new Car（（"宝马"，400000）i
 * 3.contains：查找元素是否存在
 * Car car2=new Car（宾利"，5000000）；
 * 4.size：获取元素个数
 * 5.isEmpty：判断是否为空
 * 6.clear：清空
 * 7.addAll：添加多个元素
 * 8.containsAll：查找多个元素是否都存在
 * 9.removeAll：删除多个元素
 * 使用增强for和迭代器来遍历所有的car，需要重写Car的toString方法
 */
@SuppressWarnings({"all"})
public class homework02 {
    public static void main(String[] args) {
        ArrayList arrayList = new ArrayList();
        Car car1 = new Car("BMW", 400000);
        Car car2 = new Car("BinLi", 5000000);

        arrayList.add(car1);
        arrayList.add(car2);
        System.out.println(arrayList);
        arrayList.remove(car1);
        System.out.println(arrayList);
        System.out.println(arrayList.contains(car2));
        System.out.println(arrayList.size());
        arrayList.clear();
        arrayList.add(car1);
        arrayList.add(car2);
        arrayList.addAll(arrayList);
        System.out.println(arrayList);
        System.out.println(arrayList.containsAll(arrayList));

//        arrayList.removeAll(arrayList);
//        System.out.println(arrayList);
        System.out.println("增强for");
        for (Object o : arrayList) {
            System.out.println(o);
        }
        System.out.println("迭代器");
        Iterator iterator = arrayList.iterator();
        while (iterator.hasNext()) {
            Object next = iterator.next();
            System.out.println(next);
        }
    }
}

class Car {
    private String name;
    private double price;

    public Car(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}