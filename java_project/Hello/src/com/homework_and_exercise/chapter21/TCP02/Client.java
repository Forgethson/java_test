package com.homework_and_exercise.chapter21.TCP02;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @author Jianda Wang
 * @version 1.0
 * 2022/12/19
 * P 914 Socket通信案例
 * 应用案例2（使用字节流）SocketTCP02.java
 * 1.编写一个服务器端，和一个客户端
 * 2.服务器端在 9999-端口监听
 * 3.客户端连接到服务器端，发送"hello, server"，并接收服务器端回发的 "helle, client”，再退出
 * 4.服务器端接收到客户端发送的信息，输出，并发送"hello, client"，再退出
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
        clientSocket.shutdownOutput();

        // IO读取
        InputStream inputStream = clientSocket.getInputStream();
        System.out.println("开始读取Server数据");
        byte[] buf = new byte[1024];
        int readLen = 0;
        while ((readLen = inputStream.read(buf)) != -1) {
            System.out.println(new String(buf, 0, readLen));
        }
        System.out.println("读取完毕");

        // 关闭流
        inputStream.close();
        outputStream.close();
        clientSocket.close();
    }
}
