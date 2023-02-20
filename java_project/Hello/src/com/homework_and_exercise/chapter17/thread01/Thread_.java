package com.homework_and_exercise.chapter17.thread01;

/**
 * @author Jianda Wang
 * @version 1.0
 * 2022/12/16
 * 多线程基本测试，包括三种方式：
 * 继承 Thread 类、实现 Runnable 接口、模拟 Thread 类的代理
 * 测试终止进程（发现模拟代理方式会使得主线程停止）
 */
public class Thread_ {
    public static void main(String[] args) throws InterruptedException {
        Cat cat = new Cat();
        cat.start();

        Dog dog = new Dog();
        Thread thread = new Thread(dog);
        thread.start();

        ThreadProxy threadProxy = new ThreadProxy(new Tiger());
        threadProxy.start();

        System.out.println("main线程休眠 3 秒");
        Thread.sleep(3000);

        System.out.println("终止猫和狗线程");
        dog.setLoop(false);
        cat.setLoop(false);
    }
}

class ThreadProxy implements Runnable {

    private Runnable target = null;
    @Override
    public void run() {
        if (target != null) {
            target.run();
        }
    }
    public void start(){
        start0();
    }
    public void start0(){
        run();
    }

    public ThreadProxy(Runnable target) {
        this.target = target;
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
            System.out.println("喵喵，我是小羽妹" + (times++));
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
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
            System.out.println("汪汪，我是小羽妹" + (times++));
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
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

class Tiger implements Runnable {

    int times = 0;
    @Override
    public void run() {
        while (true) {
            System.out.println("吼吼，我是小羽妹" + (times++));
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (times == 20) {
                break;
            }
        }
    }
}