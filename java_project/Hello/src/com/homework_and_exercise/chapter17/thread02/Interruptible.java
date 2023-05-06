package com.homework_and_exercise.chapter17.thread02;

import java.util.concurrent.locks.ReentrantLock;

public class Interruptible {
    private static final Object o1 = new Object();

    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        Thread thread1 = new Thread(() -> {
            System.out.println("t1 尝试获取锁");
            try {
                lock.lock();
                System.out.println("t1 得到了锁");
                Thread.sleep(6000);
                System.out.println("end lock t1");
            } catch (InterruptedException e) {
                System.out.println("t1 被中断");
            } finally {
                lock.unlock();
            }
        });

        Thread thread2 = new Thread(() -> {
            System.out.println("t2 尝试获取锁");
            try {
                lock.lockInterruptibly(); // 可中断锁（需要try-catch环绕）
                System.out.println("t2 得到了锁");
                Thread.sleep(6000);
                System.out.println("end lock t2");
            } catch (InterruptedException e) {
                System.out.println("t2 被中断");
            } finally {
//                lock.unlock();
            }
        });

        thread1.start(); // thread1先启动，先拿到锁
        Thread.sleep(100);
        thread2.start();

        // 主线程休眠一下，让t1,t2线程百分百已经启动，避免线程交替导致测试结果混淆
        Thread.sleep(3000);

        // t1拿到了对象锁，中断t1线程的执行，将直接进入到catch并且释放锁
//        thread1.interrupt();

        // t2没拿到对象锁，但是支持可中断锁
        thread2.interrupt();
        System.out.println("主线程结束");

    }
}
