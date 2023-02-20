package com.qqclient.service;

import com.qqcommon.Message;
import com.qqcommon.MessageType;
import com.qqcommon.User;
import com.sun.xml.internal.ws.server.ServerRtException;

import javax.sound.midi.Soundbank;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Date;

/**
 * @author Jianda Wang
 * @version 1.0
 * 2022/12/20
 * 该类完成用户登录验证、用户注册等
 * 提供和消息相关的服务方法
 */
public class UserClientService {

    private User u = new User();
    private Socket socket = null;

    // 方法：根据 userId 和 pwd 创建对象，并发送到服务器验证该用户是否合法
    public boolean checkUser(String userId, String pwd) {

        boolean b = false;
        // 创建User对象
        u.setUserID(userId);
        u.setPasswd(pwd);

        // 连接服务器，发送User对象，并读取从服务端回复的Message对象
        try {
            socket = new Socket(InetAddress.getLocalHost(), 9999);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

            // 向服务器发送 User 对象
            oos.writeObject(u);

            // 读取从服务端回复的Message对象（如果未能回复则阻塞在此）
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            Message ms = (Message) ois.readObject();  // 向下转型

            if (ms.getMesType().equals(MessageType.MESSAGE_LOGIN_SUCCEED)) { // 登陆成功

                // 创建一个和服务器端保持通信的线程 -ClientConnectServerThread
                ClientConnectServerThread clientConnectServerThread = new ClientConnectServerThread(socket);
                // 启动线程
                clientConnectServerThread.start();
                // 为了客户端扩展（一台主机登陆多个账号），将线程放入集合管理
                ManageClientConnectServerThread.addClientConnectServerThread(userId, clientConnectServerThread);
                b = true;

            } else if (ms.getMesType().equals(MessageType.MESSAGE_LOGIN_FAIL)) { // 登陆失败
                // 不能启动和服务器通信的线程，但是socket仍然存在，需要关闭之
                socket.close();
            } else {
                System.out.println("收到未知的服务器消息");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return b;
    }

    // 方法：向服务器端，发送一个Message，请求在线用户列表
    public void onlineFriendList() {
        Message message = new Message();
        message.setMesType(MessageType.MESSAGE_GET_ONLINE_FRIEND);
        message.setSender(u.getUserID());

        try {
            // 得到当前线程的 Socket 的 ObjectOutputStream 对象
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(message);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 方法：退出客户端，并给服务端发送一个退出系统的message对象
    public void logout() {
        Message message = new Message();
        message.setMesType(MessageType.MESSAGE_CLIENT_EXIT);
        message.setSender(u.getUserID());

        try {
            // 得到当前线程的 Socket 的 ObjectOutputStream 对象
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(message);
            System.out.println("用户" + u.getUserID() + "退出系统");

            // 关闭所有线程，并正常退出
            System.exit(0);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 方法：私聊用户，将私聊信息和目标用户发送给客户端
    public void sendMessageToOn(String content, String getterId) {
        // 构建message
        Message message = new Message();
        message.setMesType(MessageType.MESSAGE_COMM_MES);
        message.setSender(u.getUserID());
        message.setGetter(getterId);
        message.setContent(content);
        message.setSendTime(new Date().toString());
//        System.out.println(u.getUserID() + "对你说:" + content);
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 方法：群聊用户，将信息内容送给客户端
    public void sendMessageToAll(String content) {
        // 构建message
        Message message = new Message();
        message.setMesType(MessageType.MESSAGE_TO_ALL_MES);
        message.setSender(u.getUserID());
        message.setContent(content);
        message.setSendTime(new Date().toString());
//        System.out.println(u.getUserID() + "对大家说:" + content);
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 方法：发送文件
    public void sendFileToOne(String src, String dest, String senderId, String getterId) {
        Message message = new Message();
        message.setMesType(MessageType.MESSAGE_FILE_MES);
        message.setSender(senderId);
        message.setGetter(getterId);
        message.setSrc(src);
        message.setDest(dest);

        FileInputStream fileInputStream = null;
        byte[] fileBytes = new byte[(int) new File(src).length()];

        try {
            fileInputStream = new FileInputStream(src);
            fileInputStream.read(fileBytes);

            // 将字节数组放到 message 对象里面，再发送massage
            message.setFileByte(fileBytes);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    // 关闭文件流
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("\n你" + "给" + getterId + "发送文件：" + src);
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(message);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
