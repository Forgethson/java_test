package com.homework_and_exercise.chapter17.thread02;

/**
 * @author Jianda Wang
 * @version 1.0
 * 2022/12/16
 * 测试 interrupt 功能，可以唤醒睡眠中的进程，同时该进程会进入 InterruptedException 异常
 */
public class Thread_ {
    public static void main(String[] args) {
        Cat cat = new Cat();
        Dog dog = new Dog();

        cat.start();
        Thread thread = new Thread(dog);
        thread.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        cat.interrupt();
        thread.interrupt();

    }
}

class Cat extends Thread {
    int times = 0;
    boolean loop = true;

    public void setLoop(boolean loop) {
        this.loop = loop;
    }

    @Override
    public void run() {
        while (loop) {
            System.out.println("喵喵，我是小羽猫" + (times++));
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.out.println("小羽猫被揍醒了");
            }
            if (times == 20) {
                break;
            }
        }

    }

}

class Dog implements Runnable {

    int times = 0;
    boolean loop = true;

    @Override
    public void run() {
        while (loop) {
            System.out.println("汪汪，我是小羽狗" + (times++));
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.out.println("小羽狗被揍醒了");
            }
            if (times == 20) {
                break;
            }
        }
    }

    public void setLoop(boolean loop) {
        this.loop = loop;
    }
}