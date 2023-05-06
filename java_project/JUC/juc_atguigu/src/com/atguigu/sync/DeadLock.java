package com.atguigu.sync;

import java.util.concurrent.TimeUnit;

/**
 * 演示死锁
 */
public class DeadLock {

    //创建两个对象（模拟资源类对象）
    static final Object a = new Object();
    static final Object b = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (a) {
                System.out.println(Thread.currentThread().getName() + " 持有锁a，试图获取锁b");
                try {
                    // 底层就是调用了Thread.sleep()
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (b) {
                    System.out.println(Thread.currentThread().getName() + " 获取了锁b");
                }
            }
        }, "A").start();

        new Thread(() -> {
            synchronized (b) {
                System.out.println(Thread.currentThread().getName() + " 持有锁b，试图获取锁a");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (a) {
                    System.out.println(Thread.currentThread().getName() + " 获取了锁a");
                }
            }
        }, "B").start();
    }
}
