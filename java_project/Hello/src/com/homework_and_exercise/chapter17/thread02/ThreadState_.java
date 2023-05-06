package com.homework_and_exercise.chapter17.thread02;

/**
 * @author 韩顺平
 * @version 1.0
 * 测试线程的不同状态
 */
public class ThreadState_ {
    public static void main(String[] args) throws InterruptedException {
        // T extends Thread
        T t = new T();
        System.out.println(t.getName() + " 状态 " + t.getState());
        t.start();

        while (Thread.State.TERMINATED != t.getState()) {
            System.out.println(t.getName() + " 状态 " + t.getState());
            Thread.sleep(100);
        }

        System.out.println(t.getName() + " 状态 " + t.getState());

    }
}

class T extends Thread {
    @Override
    public void run() {
        while (true) {
            for (int i = 0; i < 10; i++) {
                System.out.println("hi " + i);
                try {
                    System.out.println(this.getName() + " 状态 " + this.getState());
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            break;
        }
    }
}
