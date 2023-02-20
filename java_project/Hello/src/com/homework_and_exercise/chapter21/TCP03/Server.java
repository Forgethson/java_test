package com.homework_and_exercise.chapter21.TCP03;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Jianda Wang
 * @version 1.0
 * 2022/12/19
 * P 917 Socket通信案例
 * 应用案例3（使用字符流）SocketTCP03.java
 * 1.编写一个服务端，和一个客户端
 * 2.服务端在 9999-端口监听
 * 3.客户端连接到服务端，发送"helo, server'"，并接收服务端回发的"hello, client”，再退出
 * 4.服务端接收到客户端发送的信息，输出，并发送"hello ,client'"，再退出
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

        // 转换为字符流
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        // IO读取
        System.out.println("开始读取client数据");
        String data = bufferedReader.readLine();
        System.out.println(data);
        System.out.println("读取完毕");

        // IO发送
        OutputStream outputStream = clientSocket.getOutputStream();
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        bufferedWriter.write("Hello, client");

        // 设置结束标记
        // 插入换行符以结束readLine，并手动 flush 以写入数据通道
        bufferedWriter.newLine();
        bufferedWriter.flush();

        // 关闭流
        inputStream.close();
        outputStream.close();
        clientSocket.close();
        serverSocket.close();
        bufferedWriter.close();
        bufferedReader.close();

    }
}
