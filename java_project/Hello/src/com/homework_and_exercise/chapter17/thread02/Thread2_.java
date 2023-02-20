package com.homework_and_exercise.chapter17.thread02;

/**
 * @author Jianda Wang
 * @version 1.0
 * 2022/12/16
 * 测试 join 功能，只是针对于主线程与其他子线程之间的
 */
public class Thread2_ {
    public static void main(String[] args) throws InterruptedException {
        Boy boy = new Boy();
        Boss boss = new Boss();
        Thread thread = new Thread(boy);

        boss.start();
//        thread.start();
        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000);
            System.out.println("Main 吃了" + i + "包子");
            if (i == 5) {
                thread.start();
                System.out.println("Main 让 " + "Boss" + " 先吃");
                boss.join();//这里相当于让t2线程先执行完毕
                System.out.println("Boss 吃完了 Main 接着吃..");
            }
        }
    }
}

class Boy implements Runnable {
    int times = 0;
    boolean loop = true;

    public void setLoop(boolean loop) {
        this.loop = loop;
    }

    public int getTimes() {
        return times;
    }

    @Override
    public void run() {
        while (loop) {
            System.out.println("Boy 吃了" + (times++) + "个包子");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Boy 暂停吃包子");
            }
            if (times == 10) {
                break;
            }
        }
    }
}

class Boss extends Thread {
    int times = 0;
    boolean loop = true;

    public void setLoop(boolean loop) {
        this.loop = loop;
    }

    public int getTimes() {
        return times;
    }

    @Override
    public void run() {
        while (loop) {
            System.out.println("Boss 吃了" + (times++) + "个包子");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Boss 暂停吃包子");
            }
            if (times == 10) {
                break;
            }
        }
    }
}
