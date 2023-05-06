package com.atguigu.sync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class InterruptSynMethodTest {
    // 没有在每部处理异常，不会执行剩余的代码
    public synchronized void foo() throws InterruptedException {
        System.out.println("foo begin");
        for (int i = 0; i < 100; ++i) {
            System.out.println("foo ...");
            Thread.sleep(1000);
        }
    }

    public static void main(String[] args) {
        InterruptSynMethodTest it = new InterruptSynMethodTest();
        ExecutorService es = Executors.newCachedThreadPool();
        es.execute(() -> {
            try {
                it.foo();
            } catch (InterruptedException e) {
                System.out.println("foo is interrupted, msg=" + e.getMessage());
            }
        });

        // 三秒后关闭es
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        es.shutdownNow();
        System.out.println("Main end");
    }
}
