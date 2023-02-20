package com.homework_and_exercise.chapter21.UDP01;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @author Jianda Wang
 * @version 1.0
 * 2022/12/19
 * P 929
 * UDP协议 应用案例
 * 1.编写一个接收端 A，和一个发送端 B
 * 2.接收端 A在  9999-端口等待接收数据（receive）
 * 3.发送端 9998 B 向接收端 A 发送数据 "hello，明天吃火锅~”
 * <p>
 * 4.接收端 A 接收到发送端 B 发送的数据，回复"好的，明天见”，再退出
 * 5.发送端接收回复的数据，再退出
 * -------------------Sender-------------------
 */
public class UDPSender {
    public static void main(String[] args) throws IOException {

        // 创建 DatagramSocket 对象
        DatagramSocket socket = new DatagramSocket(9998);

        // 创建 DatagramPacket 对象
        byte[] data = "Hello, 明天吃火锅~".getBytes();
        DatagramPacket packet = new DatagramPacket(data, data.length, InetAddress.getLocalHost(), 9999); // 发送往9999

        // 发送数据
        socket.send(packet);

        // 接收回复（接收的 Packet 不需要加上地址和端口！）
        byte[] buf = new byte[1024];
        DatagramPacket packet1 = new DatagramPacket(buf, buf.length);
        socket.receive(packet1);
        System.out.println("接收到的回复数据：");
        System.out.println(new String(packet1.getData(), 0, packet1.getLength()));

        socket.close();
        System.out.println("发射端B退出");
    }
}
