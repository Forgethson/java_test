package com.smallchange.oop;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * 该类是零钱通各个功能的类
 */
public class SmallChangeSysOOP {

    boolean loop = true;
    Scanner scanner = new Scanner(System.in);
    String key = " ";
    double money;
    double balance = 0.0;
    Date date = null;  // 用于日期对象
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");  // 用于日期格式化
    String note = "";  // 消费明细
    String details = "-----------零钱通明细-----------";  // 零钱通明细-字符串

    // 打印零钱通菜单
    public void mainMenu() {
        do{
            System.out.println("\n===========零钱通菜单===========");
            System.out.println("\t\t\t1 零钱通明细");
            System.out.println("\t\t\t2 收益入账");
            System.out.println("\t\t\t3 消费");
            System.out.println("\t\t\t4 退     出");
            System.out.println("请选择（1-4）：");
            key = scanner.next();  // 停在这里等待用户输入

            switch (key){
                case "1":
                    this.detail();
                    break;
                case "2":
                    this.income();
                    break;
                case "3":
                    this.pay();
                    break;
                case "4":
                    this.exit();
                    break;
                default:
                    System.out.println("选择有误，请重新选择");
            }
        }while (loop);
        System.out.println("=====退出了零钱通项目=====");
    }

    // 打印明细
    public void detail() {
        System.out.println(details);
    }

    // 收益
    public void income() { //
        System.out.println("收益入账金额：");
        money = scanner.nextDouble();
        if (money <= 0) {
            System.out.println("收益入账金额 需要 大于0");
            return;
        }
        balance += money;
        date = new Date();  // 获取当前的日期
        details += "\n收益入账\t+" + money + "\t" + sdf.format(date) + "\t" + "余额：" + balance;
    }

    // 消费
    public void pay() {
        System.out.println("消费金额：");
        money = scanner.nextDouble();
        if (money <= 0 || money > balance) {
            System.out.println("您的消费金额 应该在 0.0-" + balance);
            return;
        }
        System.out.println("消费说明：");
        note = scanner.next();
        balance -= money;
        date = new Date();  // 获取当前的日期
        details += "\n" + note + "\t-" + money + "\t" + sdf.format(date) + "\t" + "余额：" + balance;
    }

    // 退出
    public void exit() {
        String choice;  // 用户输入
        while (true){
            System.out.println("你确定要退出吗？y/n");
            choice = scanner.next();
            if (choice.equals("y") || choice.equals("n")) {
                break;
            }
        }
        if (choice.equals("y")) {
            loop = false;
        }
    }
}
