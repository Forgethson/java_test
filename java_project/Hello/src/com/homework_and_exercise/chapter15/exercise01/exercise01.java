package com.homework_and_exercise.chapter15.exercise01;

import java.util.*;

/**
 * @author Jianda Wang
 * @version 1.0
 * 2022/12/15
 * P744 泛型-课堂练习
 * 1.创建3个学生对象
 * 2.放入到HashSet中学生对象，使用.
 * 3.放入到HashMap中，要求Key String name，Value就是学生对象
 * 4.
 * 使用两种方式遍历
 */

public class exercise01 {
    public static void main(String[] args) {
        Student s1 = new Student("jack");
        Student s2 = new Student("mary");
        Student s3 = new Student("smith");

//        HashSet
        HashSet<Student> hashSet = new HashSet<>();
        hashSet.add(s1);
        hashSet.add(s2);
        hashSet.add(s3);

        System.out.println("第一种方式遍历");
        for (Student obj : hashSet) {
            System.out.println(obj);
        }
        System.out.println("第二种方式遍历");
        Iterator<Student> iterator1 = hashSet.iterator();
        while (iterator1.hasNext()) {
            System.out.println(iterator1.next());
        }

//        HashMap
        HashMap<String, Student> hashMap = new HashMap<>();
        hashMap.put(s1.getName(), s1);
        hashMap.put(s2.getName(), s2);
        hashMap.put(s3.getName(), s3);

        Set<String> keyset = hashMap.keySet();
        System.out.println("第一种方式遍历");
        for (String obj : keyset) {
            System.out.println(obj + "-" + hashMap.get(obj));
        }

        
        System.out.println("第二种方式遍历");
        Set<Map.Entry<String, Student>> entries = hashMap.entrySet();
        Iterator<Map.Entry<String, Student>> iterator2 = entries.iterator();
        while (iterator2.hasNext()) {
            Map.Entry<String, Student> next = iterator2.next();
            System.out.println(next.getKey() + "-" + next.getValue());
        }
    }
}

class Student {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Student(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                '}';
    }
}
