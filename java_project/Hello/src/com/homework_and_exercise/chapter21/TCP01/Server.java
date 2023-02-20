package com.homework_and_exercise.chapter21.TCP01;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
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
 * 服务器端----------------------------------------------
 */
public class Server {
    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(9999); // host就是本机的host
        System.out.println("服务器端，在9999端口监听，等待连接...");

        // 如果有客户端连接，就会返回 Socket 对象
        Socket connectedSocket = serverSocket.accept();
        System.out.println("Server：连接成功");

        // 查看运行类型
        System.out.println("服务器端 socket=" + connectedSocket.getClass());

        // 得到 connectedSocket 输入流
        InputStream inputStream = connectedSocket.getInputStream();

        // IO读取
        byte[] buf = new byte[1024];
        int readLen = 0;
        while ((readLen = inputStream.read(buf)) != -1) {
            System.out.println(new String(buf, 0, readLen));
        }

        // 关闭流
        inputStream.close();
        connectedSocket.close();
        serverSocket.close();

    }
}
