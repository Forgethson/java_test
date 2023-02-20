package com.homework_and_exercise.chapter21.TCP04_UploadPlus;

import com.homework_and_exercise.chapter21.TCP04_Upload.StreamUtils;

import java.io.*;
import java.net.InetAddress;
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
 * 客户端----------------------------------------------
 * -------------------尝试边读取图片，边传输图片
 */
public class Client {
    public static void main(String[] args) throws Exception {

        Socket clientSocket = new Socket(InetAddress.getLocalHost(), 8888);
        System.out.println("Client：连接成功");

        // 先读取要传输的文件
        String filePath = "E:\\mytemp\\qie.jpg";
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(filePath));

        // 写入数据相关流定义
        OutputStream outputStream = clientSocket.getOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(outputStream);

        // 读取文件流并放到传输流上
        byte[] buf = new byte[1024];
        while (bis.read(buf) != -1) {
            bos.write(buf);
        }
        // 设置结束标记
        clientSocket.shutdownOutput();
        // 相当于已经把bos给关闭了！！！
        // 即 bos.close();

        // IO读取（服务端的回复信息）
        InputStream inputStream = clientSocket.getInputStream();
        System.out.println("开始读取Server数据：");
        String s = StreamUtils.streamToString(inputStream);
        System.out.println(s);
        System.out.println("读取完毕");

        // 关闭流（最好是先打开的后关闭）
        outputStream.close();
        bis.close();
        inputStream.close();
        clientSocket.close();
    }
}
