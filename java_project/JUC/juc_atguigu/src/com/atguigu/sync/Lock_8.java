package com.atguigu.sync;

import java.util.concurrent.TimeUnit;

class Phone {

    public synchronized void sendSMS() throws Exception {
        //停留3秒
        TimeUnit.SECONDS.sleep(3);
        System.out.println("------sendSMS");
        this.notify();
//        TimeUnit.SECONDS.sleep(3);
    }

    public synchronized void sendEmail() throws Exception {
        System.out.println("------sendEmail");
        sendEmail2();
    }

    public synchronized void sendEmail2() throws Exception {
        // 测试可重入锁
        this.wait();
        System.out.println("------sendEmail2");
    }


    public void getHello() {
        System.out.println("------getHello");
    }
}

/**
 * @Description: 8锁
 *
1 标准访问，先打印短信还是邮件
------sendSMS
------sendEmail

2 停4秒在短信方法内，先打印短信还是邮件
------sendSMS
------sendEmail

3 新增普通的hello方法，是先打短信还是hello
------getHello
------sendSMS

4 现在有两部手机，先打印短信还是邮件
------sendEmail
------sendSMS

5 两个静态同步方法，1部手机，先打印短信还是邮件
------sendSMS
------sendEmail

6 两个静态同步方法，2部手机，先打印短信还是邮件
------sendSMS
------sendEmail

7 1个静态同步方法,1个普通同步方法，1部手机，先打印短信还是邮件
------sendEmail
------sendSMS

8 1个静态同步方法,1个普通同步方法，2部手机，先打印短信还是邮件
------sendEmail
------sendSMS

 */

public class Lock_8 {
    public static void main(String[] args) throws Exception {

        Phone phone = new Phone();
//        Phone phone2 = phone;
//        Phone phone2 = new Phone();

        new Thread(() -> {
            try {
//                phone.sendSMS();
                phone.sendEmail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "AA").start();

        Thread.sleep(100);

        new Thread(() -> {
            try {
                phone.sendSMS();

//                phone.sendEmail();
//                phone.sendEmail2();
//                phone.getHello();
//                phone2.sendEmail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "BB").start();
    }
}
