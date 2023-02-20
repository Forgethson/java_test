package com.homework_and_exercise.chapter21.TCP01;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @author Jianda Wang
 * @version 1.0
 * 2022/12/19
 * P 910 Socket通信案例
 * 使用字节流
 * 1.编写一个服务器端，和一个客户端
 * 2.服务器端在 9999-端口监听
 * 3.客户端连接到服务器端，发送"hello，server"，然后退出
 * 4.服务器端接收到客户端发送的信息，输出，并退出
 * <p>
 * 客户端----------------------------------------------
 */
public class Client {
    public static void main(String[] args) throws IOException {

        Socket clientSocket = new Socket(InetAddress.getLocalHost(), 9999);
        System.out.println("客户端socket返回=" + clientSocket.getClass());
        System.out.println("Client：连接成功");

        // 连接成功后，才会生成 Socket 对象
        OutputStream outputStream = clientSocket.getOutputStream();

        // 写入数据
        outputStream.write("Hello, server".getBytes());

        // 关闭流
        outputStream.close();
        clientSocket.close();
    }
}
