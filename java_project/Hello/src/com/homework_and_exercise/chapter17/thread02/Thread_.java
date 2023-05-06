package com.homework_and_exercise.chapter17.thread02;

/**
 * @author Jianda Wang
 * @version 1.0
 * 2022/12/16
 * 测试 interrupt 功能，可以唤醒睡眠中的进程，同时该进程会进入 InterruptedException 异常
 */
public class Thread_ {
    // 主线程的对象；获取当前线程，如果赋值给属性，则属性需要是静态的
    static Thread main = Thread.currentThread();

    public static void main(String[] args) {
//        // 测试1
//        Cat cat = new Cat();
//        Dog dog = new Dog();
//
//        cat.start();
//        Thread thread = new Thread(dog);
//        thread.start();

        // 测试2
        Thread_ thread = new Thread_();
        new Thread(() -> {
            try {
                Thread.sleep(1000);
                for (int i = 0; i < 4; i++) {
                    main.interrupt();
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        thread.test1();
        thread.test2();
        try {
            thread.test3();
        } catch (InterruptedException e) {
            System.out.println("-----test3-主线程(wait)被中断唤醒了");
        }
        try {
            thread.test4();
        } catch (InterruptedException e) {
            System.out.println("-----test4-主线程(sleep)被中断唤醒了");
        }

//        cat.interrupt();
//        thread.interrupt();

    }

    public synchronized void test1() {
        try {
            System.out.println("test1 ...");
            this.wait(3000);
        } catch (InterruptedException e) {
            System.out.println("-----test1-主线程(wait)被中断唤醒了");
        }
        System.out.println("test1结束");
    }

    public void test2() {
        try {
            System.out.println("test2 ...");
            // 主线程休眠1秒
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            System.out.println("-----test2-主线程(sleep)被中断唤醒了");
        }
        System.out.println("test2结束");
    }

    public synchronized void test3() throws InterruptedException {
        System.out.println("test3 ...");
        this.wait(3000);
        System.out.println("test3结束");
    }

    public void test4() throws InterruptedException {
        System.out.println("test4 ...");
        // 主线程休眠1秒
        Thread.sleep(3000);
        System.out.println("test4结束");
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