package com.homework_and_exercise.chapter17.homework02;

/**
 * @author Jianda Wang
 * @version 1.0
 * 2022/12/16
 * 2.编程题
 * Homework02.java
 * （1）有2个用户分别从同一个卡上取钱（总额：10000）
 * （2）每次都取1000，当余额不足时，就不能取款了
 * （3）不能出现超取现象=》线程同步问题
 */
public class homework02 {
    public static void main(String[] args) {
        Card card = new Card();
        Thread thread1 = new Thread(card);
        Thread thread2 = new Thread(card);
        thread1.start();
//        thread1.setName("T1");
        thread2.start();
//        thread2.setName("T2");

    }
}

class Card implements Runnable {
    private boolean loop = true;
    private int money = 100000;

    @Override
    public void run() {
        while (loop) {
            synchronized (this) {
                if (money < 1000) {
                    System.out.println("余额不足");
                    break;
                }
                System.out.println(Thread.currentThread().getName() + "已取出1000元，剩余" + (money -= 1000) + "元");
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}