package com.homework_and_exercise.chapter15.exercise02;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;

/**
 * @author Jianda Wang
 * @version 1.0
 * 2022/12/15
 * P752 泛型课堂练习题2
 * 定义Employee类
 * ）该类包含：privatel成员变量name，sal，birthday，其中birthday为MyDate类的对象；
 * 2）为每一个属性定义getter，，setter方法；
 * 3）重写toString方法输出name，sal，birthday
 * 4）MyDate类包含：private成员变量month，day，year；并为每一个属性定义getter，，setter方法：
 * 5）创建该类的3个对象，并把这些对象放入ArrayList集合中（ArrayList需使用泛型来定义），对集合中的元素进行排序，并遍历输出：
 * 排序方式：调用ArrayList的sort方法，传入Comparator对象[使用泛型]，先按照name排序，如果name相同，则按生日日期的先后排序。【即：定制排序】
 * 有一定难度，比较经典泛型使用案例
 */
public class exercise02 {
    public static void main(String[] args) {
        Employee employee1 = new Employee("Wang", 100, new MyDate(98, 10, 27));
        Employee employee2 = new Employee("Liu", 80, new MyDate(99, 10, 3));
        Employee employee3 = new Employee("Liu", 95, new MyDate(99, 10, 2));

        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(employee1);
        employees.add(employee2);
        employees.add(employee3);

        employees.sort(new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                int res = o1.getName().compareTo(o2.getName());
                if (res != 0) {  // 若名字不一样
                    return res;
                }
                // 名字一样的话
                res = o1.getBirthday().toCompare(o2.getBirthday());
                return res;
            }
        });
        System.out.println(employees);
    }
}

class Employee {
    private String name;
    private int sal;
    private MyDate birthday;

    public Employee(String name, int sal, MyDate birthday) {
        this.name = name;
        this.sal = sal;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSal() {
        return sal;
    }

    public void setSal(int sal) {
        this.sal = sal;
    }

    public MyDate getBirthday() {
        return birthday;
    }

    public void setBirthday(MyDate birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", sal=" + sal +
                ", birthday=" + birthday +
                '}' + '\n';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return this.name.equals(employee.name) &&
                this.birthday.getDay() == employee.birthday.getDay() &&
                this.birthday.getMonth() == employee.birthday.getMonth() &&
                this.birthday.getYear() == employee.birthday.getYear();
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, birthday.getDay(), birthday.getMonth(), birthday.getYear());
    }
}

class MyDate {
    private int year;
    private int month;
    private int day;

    public int toCompare(MyDate date) {
        return this.getYear() * 10000 + this.getMonth() * 100 + this.getDay() - date.getYear() * 10000 -
                date.getMonth() * 100 - date.getDay();
    }

    public MyDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return "MyDate{" +
                "year=" + year +
                ", month=" + month +
                ", day=" + day +
                '}';
    }
}