package com.homework_and_exercise.chapter8.encap;

public class Encapsulation01 {
    public static void main(String[] args) {
    Person person1 = new Person();
    person1.setName("王謇达");
    person1.setAge(24);
    person1.setSalary(30000.0);
    person1.setPwd("1231313131");
    System.out.println(person1.info());

    Person person2 = new Person("王羽", 23, 20000.0, "123345");
    System.out.println(person2.info());
    }
}

class Person {
    public String name;
    private int age;
    private double salary;
    private String pwd;
//    构造函数
    public Person(String name, int age, double salary, String pwd) {
        setName(name);
        setAge(age);
        setSalary(salary);
        setPwd(pwd);
    }
//    默认构无参造函数（写上以防万一直接new一个对象）
    public Person() {
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        if (pwd.length() == 6) {
            this.pwd = pwd;
        }
        else {
            System.out.println("密码必须为6位，设置为默认密码：000000");
            this.pwd = "000000";
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.length() >= 2 && name.length() <= 10){
            this.name = name;
        }
        else {
            System.out.println("你设置的名字长度不对，需要在 2-10，给默认名字：无名");
            this.name = "无名";
        }
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age >= 1 && age <= 120){
            this.age = age;
        }
        else {
            System.out.println("你设置的年龄不对，需要在 1-120，给默认年龄18");
            this.age = 18;
        }
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
    public String info() {
        return "信息为：姓名=" + name + " 年龄=" + age + " 薪水=" + salary + "密码=" + pwd;
    }
}
