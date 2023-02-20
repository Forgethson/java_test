package com.homework_and_exercise.chapter17.homework01;

import java.util.Scanner;

/**
 * @author Jianda Wang
 * @version 1.0
 * 2022/12/16
 * 1.编程题Homework01.java5min
 * （1）在main方法中启动两个线程
 * （2）第1个线程循环随机打印100以内的整数（3）直到第2个线程从键盘读取了“Q”命令。
 */
public class homework01 {
    public static void main(String[] args) {
        A a = new A();
        B b = new B(a);
        Thread threadA = new Thread(a);
        Thread threadB = new Thread(b);
        threadA.start();
        threadB.start();
    }
}

class A implements Runnable {
    private boolean loop = true;

    public void setLoop(boolean loop) {
        this.loop = loop;
    }

    @Override
    public void run() {
        while (loop) {
            System.out.println((int)(Math.random() * 100 + 1));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("a线程退出");
    }
}

class B implements Runnable {
    private A a;
    private Scanner scanner = new Scanner(System.in);

    public B(A a) {
        this.a = a;
    }

    public void setThreadA(A a) {
        this.a = a;
    }

    @Override
    public void run() {
        while (true) {
            // 接收到用户收入
            System.out.println("请输入你的指令（Q）表示退出");
            char key = scanner.next().toUpperCase().charAt(0);
            if (key == 'Q') {
                a.setLoop(false);
                System.out.println("b线程退出");
                break;
            }
        }
    }
}