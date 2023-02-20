package com.homework_and_exercise.chapter21.TCP03;

import java.io.*;
import java.net.InetAddress;
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
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        bufferedWriter.write("Hello, server");

        // 设置结束标记
        bufferedWriter.newLine();
        bufferedWriter.flush();


        // IO读取
        InputStream inputStream = clientSocket.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        System.out.println("开始读取Server数据");
        String data = bufferedReader.readLine();
        System.out.println(data);
        System.out.println("读取完毕");

        // 关闭流（最好是先打开的后关闭）
        bufferedReader.close();
        inputStream.close();
        bufferedWriter.close();
        outputStream.close();
        clientSocket.close();
    }
}
