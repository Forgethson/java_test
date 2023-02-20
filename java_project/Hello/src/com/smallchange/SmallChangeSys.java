package com.smallchange;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class SmallChangeSys {
    public static void main(String[] args) {

//        1. 显示菜单
//        2. 零钱通明细
//        3. 收益入账

        boolean loop = true;
        Scanner scanner = new Scanner(System.in);
        String key = " ";
        double money;
        double balance = 0.0;
        Date date = null;  // 用于日期对象
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");  // 用于日期格式化
        String note = "";  // 消费明细
        String details = "-----------零钱通明细-----------";

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
                    System.out.println(details);
                    break;
                case "2":
                    System.out.println("收益入账金额：");
                    money = scanner.nextDouble();
//                    校验money
                    if (money <= 0) {
                        System.out.println("收益入账金额 需要 大于0");
                        break;
                    }
                    balance += money;
                    date = new Date();  // 获取当前的日期
                    details += "\n收益入账\t+" + money + "\t" + sdf.format(date) + "\t" + "余额：" + balance;
                    break;
                case "3":
                    System.out.println("消费金额：");
                    money = scanner.nextDouble();
                    if (money <= 0 || money > balance) {
                        System.out.println("您的消费金额 应该在 0.0-" + balance);
                        break;
                    }
                    System.out.println("消费说明：");
                    note = scanner.next();
                    balance -= money;
                    date = new Date();  // 获取当前的日期
                    details += "\n" + note + "\t-" + money + "\t" + sdf.format(date) + "\t" + "余额：" + balance;

                    break;
                case "4":
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
                    break;
                default:
                    System.out.println("选择有误，请重新选择");
            }
        }while (loop);
        System.out.println("=====退出了零钱通项目=====");
    }
}
