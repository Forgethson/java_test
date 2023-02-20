package com.homework_and_exercise.chapter21.TCP04_Upload;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Jianda Wang
 * @version 1.0
 * 2022/12/19
 * P 922 Socket通信案例
 * 应用案例 4 TCPFileCopy.java
 * 1.编写一个服务端，和一个客户端
 * 2.服务器端在 8888-端口监听
 * 3.客户端连接到服务端，发送一张图片 qie.png
 * 4.服务器端接收到客户端发送的图片，保存到 src 下，发送 "收到图片" 再退出
 * 5.客户端接收到服务端发送的"收到图片”，再退出
 * 6.该程序要求使用 StreamUtils.java
 * <p>
 * 服务器端----------------------------------------------
 */
public class Server {
    public static void main(String[] args) throws Exception {

        ServerSocket serverSocket = new ServerSocket(8888); // host就是本机的host
        System.out.println("服务器端，在 8888 端口监听，等待连接...");

        // 如果有客户端连接，就会返回 Socket 对象
        Socket clientSocket = serverSocket.accept();
        System.out.println("Server：连接成功");

        // 得到 clientSocket 输入流，并放入字节包装流
        InputStream inputStream = clientSocket.getInputStream();
        BufferedInputStream bis = new BufferedInputStream(inputStream);

        // 利用 StreamUtils 工具，将 BufferedInputStream 转换为字节数组（byte[]）
        byte[] picData = StreamUtils.streamToByteArray(bis);

        // 利用得到的字节数组写入文件
        String destFilePath = "src\\qie.jpg";
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(destFilePath));
        bos.write(picData);
        bos.close();

        // IO发送
        OutputStream outputStream = clientSocket.getOutputStream();
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        bufferedWriter.write("收到图片");

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

    }
}
