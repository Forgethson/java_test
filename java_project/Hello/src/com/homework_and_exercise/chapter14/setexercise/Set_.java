package com.homework_and_exercise.chapter14.setexercise;

import java.util.HashSet;
import java.util.Objects;

/**
 * @author Jianda Wang
 * @version 1.0
 * 2022/12/13
 * P692 HashSet 课后练习2
 */
@SuppressWarnings({"all"})
public class Set_ {
    public static void main(String[] args) {
        Employee employee1 = new Employee("Wang", 100, new MyDate(98, 10, 27));
        Employee employee2 = new Employee("Liu", 80, new MyDate(99, 10, 1));
        Employee employee3 = new Employee("Liu", 95, new MyDate(99, 10, 1));

        HashSet hashSet = new HashSet();
        hashSet.add(employee1);
        hashSet.add(employee2);
        hashSet.add(employee3);

        System.out.println(hashSet);

        employee2.setName("Wang");
        System.out.println(hashSet);
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