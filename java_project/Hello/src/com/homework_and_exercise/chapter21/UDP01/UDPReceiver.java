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
 * ---------------Receiver--------------
 */
public class UDPReceiver {
    public static void main(String[] args) throws IOException {

        // 创建 DatagramSocket 对象
        DatagramSocket socket = new DatagramSocket(9999);

        // 创建 DatagramPacket 对象
        byte[] buf = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);

        // 等待接收数据，存放至 packet
        System.out.println("接收端A等待接收数据");
        socket.receive(packet);

        // 显示接收数据
        System.out.println("接收到的数据：");
        int length = packet.getLength();
        byte[] data = packet.getData();
        System.out.println(new String(data, 0, length));

        // 发送数据，回复"好的，明天见”（用了另一种 DatagramPacket 的构造函数）
        byte[] data1 = "好的，明天见".getBytes();
        DatagramPacket packet1 = new DatagramPacket(data1, data1.length);
        packet1.setAddress(InetAddress.getLocalHost());
        packet1.setPort(9998);
        socket.send(packet1);

        socket.close();
        System.out.println("接收端A退出");

    }
}
