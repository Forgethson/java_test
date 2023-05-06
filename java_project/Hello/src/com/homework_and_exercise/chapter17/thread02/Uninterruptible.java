package com.homework_and_exercise.chapter17.thread02;

public class Uninterruptible {
    private static final Object o1 = new Object();

    public static void main(String[] args) throws InterruptedException {

        Thread thread1 = new Thread(() -> {
            System.out.println("t1 enter");
            synchronized (o1) {
                try {
                    System.out.println("t1 得到o1锁");
                    Thread.sleep(3000);
                    System.out.println("end lock t1");
                } catch (InterruptedException e) {
                    System.out.println("t1 interruptedException");
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            System.out.println("t2 enter");
            synchronized (o1) {
                try {
                    System.out.println("t2 得到o1锁");
                    Thread.sleep(3000);
                    System.out.println("end lock t2");
                } catch (InterruptedException e) {
                    System.out.println("t2 interruptedException");
                }
            }
        });

        thread1.start(); // thread1先启动，先拿到o1的锁
        Thread.sleep(100);
        thread2.start();

        // 主线程休眠一下，让t1,t2线程百分百已经启动，避免线程交替导致测试结果混淆
        Thread.sleep(500);

        // t1拿到了对象锁，中断t1线程的执行，将直接进入到catch并且释放锁
        thread1.interrupt();

        // t2没拿到对象锁，中断t2线程的执行，但是此时线程2没有获得锁，需要等到获得锁之后，才进入到catch
//        thread2.interrupt();
        System.out.println("主线程结束");

    }
}
