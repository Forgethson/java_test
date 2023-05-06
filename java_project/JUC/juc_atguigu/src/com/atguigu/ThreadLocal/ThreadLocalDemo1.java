package com.atguigu.ThreadLocal;

public class ThreadLocalDemo1 {

    public static void main(String[] args) throws InterruptedException {
        ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

        new Thread(() -> {
            System.out.println("线程-" + Thread.currentThread().getName() + "：" + threadLocal.get());
            threadLocal.set(100);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程-" + Thread.currentThread().getName() + "：" + threadLocal.get());
        }).start();

        Thread.sleep(3000);

        new Thread(() -> {
            System.out.println("线程-" + Thread.currentThread().getName() + "：" + threadLocal.get());
            threadLocal.set(200);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程-" + Thread.currentThread().getName() + "：" + threadLocal.get());
        }).start();


    }
}
