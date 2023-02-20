package com.homework_and_exercise.chapter8.extend;

public class ExtendsDetail {
    public static void main(String[] args) {
//        System.out.println("===第1个对象====");
//        Sub sub = new Sub(); //创建了子类对象 sub
//        System.out.println("===第2个对象====");
//        Sub sub2 = new Sub("jack", 23); //创建了子类对象 sub2

//        int[] arr = {1, 2, 5, 9, 24, 21, 3, 4, 45, 12};
//        Arrays.sort(arr);


        "hello".equals("eee");
        Sub sub3 = new Sub();
        Base base = new Sub();
        System.out.println(sub3.count);
        System.out.println(base.count);
        System.out.println(sub3.getCount());
        System.out.println(base.getCount());
        System.out.println("=====================");
        System.out.println(base.fun2());
        // sub没有fun2，从base里面找，然后再调用getCount()，
        // 优先从sub里面找，绑定了sub的方法

//        System.out.println("===第3对象====");
//        com.homework_and_exercise.chapter8.extend.Sub sub3 = new com.homework_and_exercise.chapter8.extend.Sub("king", 10); //创建了子类对象 sub2
//        sub.sayOk();
    }
}
