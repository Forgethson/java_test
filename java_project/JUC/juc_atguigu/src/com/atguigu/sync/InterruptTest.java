package com.atguigu.sync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//synchronized不可以被中断，指的是synchronized等待不可中断，比如：
// foo2的synchronized在等待foo1时不可被中断，只有在foo2拿到锁之后才可被中断
//根据Java文档，判断方法很简单：只要调用的方法抛出InterruptedException异常，那么它就可以被中断；不抛出InterruptedException的方法是不可中断的。
// 比如foo1方法就不可被中断，foo2方法可被中断。

public class InterruptTest {

    public synchronized void foo1() {
        System.out.println("foo1 begin");
        for (int i =0; i < 5; ++i) {
            System.out.println("foo1 ...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("foo1 sleep is interrupted, msg=" + e.getMessage());
            }
        }
    }

    public synchronized void foo2() throws InterruptedException {
        System.out.println("foo2 begin");
        for (int i =0; i < 100; ++i) {
            System.out.println("foo2 ...");
            Thread.sleep(1000);
        }
    }

    public static void main(String[] args) {
        InterruptTest it = new InterruptTest();
        ExecutorService es = Executors.newCachedThreadPool();
        // 下面两个foo1和foo2方法是同一个资源类对象的方法，共享同一个对象锁
        es.execute(() -> it.foo1());
        es.execute(() -> {
            try {
                it.foo2();
            } catch (InterruptedException e) {
                System.out.println("foo2 is interrupted, msg=" + e.getMessage());
            }
        });
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 关闭线程池
        es.shutdownNow();
        System.out.println("Main end");
    }
}