package com.homework_and_exercise.chapter8;
/**
 *定义一个人类，初始化人对象数组，按照age从大到小冒泡排序
 */
public class homework1 {
    public static void main(String[] args) {
        Person[] personArr = new Person[3];
        tools tools = new tools();
        personArr[0] = new Person("Wang", 20, "driver");
        personArr[1] = new Person("Li", 13, "student");
        personArr[2] = new Person("Sun", 55, "teacher");
        tools.sort(personArr);
        for (int i = 0; i < personArr.length; i++) {
            System.out.println(personArr[i].age);
        }

    }
}

class Person {
    public String name;
    public int age;
    public String job;

    public Person(String name, int age, String job) {
        this.name = name;
        this.age = age;
        this.job = job;
    }
}

class tools {
    public void sort(Person[] P) {
        int temp;
        for (int i = 0; i < P.length - 1; i++) {
            for (int j = 0; j < P.length - i - 1; j++) {
                if (P[j].age <= P[j+1].age) {
                    temp = P[j].age;
                    P[j].age = P[j+1].age;
                    P[j+1].age = temp;
                }
            }
        }
    }
}