package com.homework_and_exercise.chapter21.inetaddress;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author Jianda Wang
 * @version 1.0
 * 2022/12/19
 * InetAddress_类的使用 P 908
 * InetAddress 包含了 主机名/域名 + IP地址 的信息
 * 可以通过 域名 / IP地址来获取 InetAddress 对象
 * 获取主机名
 */
public class InetAddress_ {
    public static void main(String[] args) throws UnknownHostException {

        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println(localHost);//DESKTOP-D4G1NAI/192.168.3.33

        // 域名 + IP
        // 主机名 + IP
        InetAddress host = InetAddress.getByName("DESKTOP-D4G1NAI");
//        InetAddress host1 = InetAddress.getByName("www.pornhub.com");
        InetAddress host1 = InetAddress.getByName("www.baidu.com");

        System.out.println(host);
        System.out.println(host1);

        System.out.println(host1.getHostAddress());
        System.out.println(host1.getHostName());

    }
}
