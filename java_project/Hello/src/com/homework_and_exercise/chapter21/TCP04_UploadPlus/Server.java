package com.homework_and_exercise.chapter21.TCP04_UploadPlus;

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
 * -------------------尝试边读取图片，边传输图片
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

        // 将文件流放入包装流，以输出内容
        String destFilePath = "src\\qie.jpg";
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(destFilePath));

        // 从传输流中读取字节流数据，并写入到文件
        byte[] buf = new byte[1024];
        while (bis.read(buf) != -1) {
            bos.write(buf);
        }

        // IO发送
        OutputStream outputStream = clientSocket.getOutputStream();
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        bufferedWriter.write("收到图片");

        // 设置结束标记
        // 插入换行符以结束readLine，并手动 flush 以写入数据通道
        bufferedWriter.newLine();
        bufferedWriter.flush();

        // 关闭流
        bis.close();
        bos.close();
        clientSocket.close();
        serverSocket.close();
        bufferedWriter.close();

    }
}
