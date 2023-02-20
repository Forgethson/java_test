package com.homework_and_exercise.chapter17.thread02;

/**
 * @author Jianda Wang
 * @version 1.0
 * 2022/12/16
 * 使用 synchronized 来防止超卖现象
 */
public class ThreadSyn {
    public static void main(String[] args) {
        SaleFood saleFood = new SaleFood();
        Thread thread1 = new Thread(saleFood);
        Thread thread2 = new Thread(saleFood);
        Thread thread3 = new Thread(saleFood);
        thread1.start();
        thread2.start();
        thread3.start();

    }
}

class SaleFood implements Runnable {

    private boolean loop = true;
    private int FoodNum = 100;

    public synchronized void SaleOver() {
        if (FoodNum <= 0) {
            System.out.println("包子卖完啦");
            setLoop(false);
            return;
        }
        System.out.println("从" + Thread.currentThread().getName() + "卖出一个包子，还剩下：" + (FoodNum--));
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            System.out.println("餐车被唤醒了");
        }
    }

    @Override
    public void run() {
        while (loop) {
            SaleOver();
        }
    }

    public void setLoop(boolean loop) {
        this.loop = loop;
    }
}