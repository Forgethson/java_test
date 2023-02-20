package com.qqclient.view;

import com.qqclient.service.UserClientService;
import com.qqclient.utils.Utility;

import javax.rmi.CORBA.Util;

/**
 * @author Jianda Wang
 * @version 1.0
 * 2022/12/19
 */
public class QQView {
    public static void main(String[] args) {
        new QQView().mainMenu();
    }

    private boolean loop = true;
    private String key = "";  // 接收用户输入
    private UserClientService userClientService = new UserClientService(); // 用于登陆服务器/注册用户

    //  显示主菜单
    private void mainMenu() {

        while (loop) {
            System.out.println("==========欢迎登陆网络通信系统==========");
            System.out.println("\t\t 1 登录系统");
            System.out.println("\t\t 9 退出系统");
            System.out.print("请输入你的选择：");

            key = Utility.readString(1);

            // 根据用户的输入来处理不同的逻辑
            switch (key) {
                case "1":
                    System.out.print("请输入用户名：");
                    String userId = Utility.readString(50);
                    System.out.print("请输入密码：");
                    String pwd = Utility.readString(50);
                    // 编写一个类 UserClientService

                    // 需要到服务端验证用户名
                    if (userClientService.checkUser(userId, pwd)) {
                        System.out.println("==========欢迎用户" + userId + "登陆成功==========");

                        // 进入到二级菜单
                        while (loop) {
                            System.out.println("==========网络通信系统二级菜单（" + userId + "）==========");
                            System.out.println("\t\t 1 显示在线用户列表");
                            System.out.println("\t\t 2 群发消息");
                            System.out.println("\t\t 3 私聊消息");
                            System.out.println("\t\t 4 发送文件");
                            System.out.println("\t\t 9 退出系统");
                            System.out.print("请输入你的选择：");
                            key = Utility.readString(1);
                            switch (key) {
                                case "1":
                                    System.out.println("显示在线用户列表");
                                    userClientService.onlineFriendList();
                                    break;
                                case "2": {
                                    System.out.println("请输入想对大家说的话：");
                                    String content = Utility.readString(100);
                                    userClientService.sendMessageToAll(content);
                                    break;
                                }
                                case "3": {
                                    System.out.print("请输入想聊天的用户号：");
                                    String getterId = Utility.readString(50);
                                    System.out.print("请输入想说的话：");
                                    String content = Utility.readString(100);
                                    userClientService.sendMessageToOn(content, getterId);
                                    break;
                                }
                                case "4":
                                    System.out.print("请输入你想发给谁：");
                                    String getterId = Utility.readString(50);
                                    System.out.print("请输入你想发送的文件目录（形如 d:\\xx.jpg)：");
                                    String src = Utility.readString(100);
                                    System.out.print("请输入你想发送到的目的目录（形如 d:\\xx.jpg)：");
                                    String dest = Utility.readString(100);
                                    userClientService.sendFileToOne(src, dest, userId, getterId);
                                    break;
                                case "9":
                                    System.out.println("退出系统");
                                    userClientService.logout();
                                    loop = false;
                                    break;
                                default:
                                    System.out.println("输入错误，请重新输入");
                            }
                        }

                    } else {
                        System.out.println("登录服务器失败");
                    }
                    break;
                case "9":
                    System.out.println("退出系统");
                    loop = false;
                    break;

            }
        }
    }
}
