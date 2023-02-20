package com.homework_and_exercise.chapter14.homework03;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author Jianda Wang
 * @version 1.0
 * 2022/12/14
 * 按要求完成下列任务
 * 1）使用HashMap类实例化一个Map类型的对象m，键（String）和值（int）分别用于存储员工的姓名和工资，存入数据如下：jack一650元；tom一1200元；smith一2900元；
 * 2）将jack的工资更改为2600元
 * 3）为所有员工工资加薪100元：
 * 4）遍历集合中所有的员工5）遍历集合中所有的工资
 */
@SuppressWarnings({"all"})
public class homework03 {
    public static void main(String[] args) {
        Employee e1 = new Employee("jack", 650);
        Employee e2 = new Employee("tom", 1200);
        Employee e3 = new Employee("smith", 2900);

        HashMap m = new HashMap();
        m.put(e1.getName(), e1.getSal());
        m.put(e2.getName(), e2.getSal());
        m.put(e3.getName(), e3.getSal());
        System.out.println(m);

        e1.setName("Wang");
        System.out.println(m);

        m.put("jack", 2600.0);
        System.out.println(m);

        Set keyset = m.keySet();
        for (Object key : keyset) {
            m.put(key, (Double) m.get(key) + 100);
        }
        System.out.println(m);

        System.out.println("==================遍历================");
        Set entryset = m.entrySet();
        Iterator iterator = entryset.iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            System.out.println(entry.getKey() + "-" + entry.getValue());
        }

        System.out.println("==================遍历2================");
        for (Object key : keyset) {
            System.out.println(key + "-" + (Double) m.get(key));
        }

    }
}

class Employee {
    private String name;
    private double sal;

    public Employee(String name, double sal) {
        this.name = name;
        this.sal = sal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSal() {
        return sal;
    }

    public void setSal(double sal) {
        this.sal = sal;
    }
}
