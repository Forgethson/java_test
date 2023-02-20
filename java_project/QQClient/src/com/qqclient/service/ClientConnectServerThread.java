package com.qqclient.service;

import com.qqclient.utils.Utility;
import com.qqcommon.Message;
import com.qqcommon.MessageType;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.time.Year;

/**
 * @author Jianda Wang
 * @version 1.0
 * 2022/12/20
 */
public class ClientConnectServerThread extends Thread {

    private Socket socket;

    public Socket getSocket() {
        return socket;
    }

    public ClientConnectServerThread(Socket socket) {
        this.socket = socket;
    }

    // 通过线程实现客户端与服务器的通信过程
    @Override
    public void run() {
        while (true) {

            try {
//                System.out.println("客户端线程，等待从服务器端发送的消息...");
                ObjectInputStream osi = new ObjectInputStream(socket.getInputStream());

                // 如果服务器没有发送Message对象，线程会阻塞在这里
                Message message = (Message) osi.readObject();

                // 后面需要去使用message

                // 如果读取到的是服务器返回的在线用户列表
                if (message.getMesType().equals(MessageType.MESSAGE_RET_ONLINE_FRIEND)) {

                    // 取出在线列表信息并显示
                    String[] onlineUsers = message.getContent().split(" ");
                    System.out.println("\n==========当前在线用户列表==========");
                    for (int i = 0; i < onlineUsers.length; i++) {
                        System.out.println("用户：" + onlineUsers[i]);
                    }

                } else if (message.getMesType().equals(MessageType.MESSAGE_COMM_MES)) {
                    // 把从服务器转发的消息显示在控制台
                    System.out.println("\n" + message.getSender() + "对你说：" + message.getContent());

                } else if (message.getMesType().equals(MessageType.MESSAGE_TO_ALL_MES)) {
                    // 显示群聊消息的内容
                    System.out.println("\n" + message.getSender() + "对大家说：" + message.getContent());

                } else if (message.getMesType().equals(MessageType.MESSAGE_FILE_MES)) {
                    // 传输文件
                    System.out.println("\n" + message.getSender() + "向你传输文件：" + message.getSrc());

                    // 从message的字节数组中取出，写入到磁盘
                    FileOutputStream fileOutputStream = new FileOutputStream(message.getDest());
                    fileOutputStream.write(message.getFileByte());
                    fileOutputStream.close();
                    System.out.println("保存文件成功");

                } else {
                    System.out.println("是其他类型的message，暂时不处理...");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
