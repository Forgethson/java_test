package com.atguigu.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

//集齐7颗龙珠就可以召唤神龙
public class CyclicBarrierDemo {
    //创建固定值
    private static final int NUMBER = 7;

    public static void main(String[] args) {
        //创建CyclicBarrier，第一个参数是目标数，第二个参数是Runnable实现类，相当于达到目标后，启动一个新线程
        CyclicBarrier cyclicBarrier =
                new CyclicBarrier(NUMBER, () -> {
                    System.out.println("*****集齐7颗龙珠就可以召唤神龙");
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });
        //集齐七颗龙珠过程
        for (int i = 1; i <= 7; i++) {
            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + " 星龙珠被收集到了");
                    //等待
                    cyclicBarrier.await();
                    System.out.println("唤醒"+"线程" + Thread.currentThread().getName() + "-结束");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }
}
