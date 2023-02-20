package com.homework_and_exercise.chapter21.TCP02;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
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
 * 服务器端----------------------------------------------
 */
public class Server {
    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(9999); // host就是本机的host
        System.out.println("服务器端，在9999端口监听，等待连接...");

        // 如果有客户端连接，就会返回 Socket 对象
        Socket clientSocket = serverSocket.accept();
        System.out.println("Server：连接成功");

        // 查看运行类型
        System.out.println("服务器端 socket=" + clientSocket.getClass());

        // 得到 clientSocket 输入流
        InputStream inputStream = clientSocket.getInputStream();

        // IO读取
        System.out.println("开始读取client数据");
        byte[] buf = new byte[1024];
        int readLen = 0;
        while ((readLen = inputStream.read(buf)) != -1) {
            System.out.println(new String(buf, 0, readLen));
        }

        System.out.println("读取完毕");

        // IO发送
        OutputStream outputStream = clientSocket.getOutputStream();
        outputStream.write("Hello, client".getBytes());

        // 设置结束标记
        clientSocket.shutdownOutput();

        // 关闭流
        inputStream.close();
        outputStream.close();
        clientSocket.close();
        serverSocket.close();

    }
}
