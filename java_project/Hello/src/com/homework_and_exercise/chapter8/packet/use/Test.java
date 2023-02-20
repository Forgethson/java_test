package com.homework_and_exercise.chapter8.packet.use;

import com.homework_and_exercise.chapter8.packet.xiaoqiang.Dog;

public class Test {
    public static void main(String[] args) {
        Dog dog = new Dog();
        System.out.println(dog); // com.homework_and_exercise.chapter8.packet.xiaoqiang.Dog@1b6d3586

        com.homework_and_exercise.chapter8.packet.xiaoming.Dog dog1 = new com.homework_and_exercise.chapter8.packet.xiaoming.Dog();
        System.out.println(dog1); // com.homework_and_exercise.chapter8.packet.xiaoming.Dog@4554617c
    }
}
