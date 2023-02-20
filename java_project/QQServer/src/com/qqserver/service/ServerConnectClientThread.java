package com.qqserver.service;

import com.qqcommon.Message;
import com.qqcommon.MessageType;
import com.qqcommon.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * @author Jianda Wang
 * @version 1.0
 * 2022/12/20
 * 该类是用户的线程类，每一个对象管理一个用户及其线程
 * （因为开始socket通信之后，线程就会进入阻塞阶段，一定要使用多线程）
 * 使用该类的一个对象中的socket属性和某个客户端保持通信
 * 需要有额外的userId属性来判断该线程是和哪个客户端通信
 */
public class ServerConnectClientThread extends Thread {

    private Socket socket;  // 对应于此线程的 Socket 对象
    private String userId;  // 对应于此线程的用户 id

    public Socket getSocket() {
        return socket;
    }

    public ServerConnectClientThread(Socket socket, String userId) {
        this.socket = socket;
        this.userId = userId;
    }

    @Override
    public void run() {  // 线程处于run状态，可以接收/发送消息
        while (true) {

            System.out.println("服务器端和客户端" + userId + "保持通信，读取数据...");
            try {

                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Message message = (Message) ois.readObject();

                // 根据 message 的类型，做相应的业务处理
                if (message.getMesType().equals(MessageType.MESSAGE_GET_ONLINE_FRIEND)) {
                    // 用户请求获取在线用户列表
                    // 在线用户列表使用 100 200 ... 表示
                    System.out.println("客户端" + message.getSender() + "请求获取在线用户列表");
                    String onlineUser = ManageClientThreads.getOnlineUser();

                    // 构建一个 Message 对象返回给客户端
                    Message message1 = new Message();
                    message1.setMesType(MessageType.MESSAGE_RET_ONLINE_FRIEND);
                    message1.setContent(onlineUser);
                    message1.setGetter(message.getSender());

                    // 返回给客户端
                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                    oos.writeObject(message1);

                } else if (message.getMesType().equals(MessageType.MESSAGE_COMM_MES)) {
                    // sender 用户请求私聊（服务器实现转发消息的功能）
                    // 从线程列表中获得相应目标用户的线程，再得到其中 Socket 属性，再返回给 getter 客户端

                    if (QQServer.validUsers.get(message.getGetter()) == null) {
                        // 说明该userId没有存在（未注册）
                        message.setContent("该用户没有注册");
                        message.setGetter(message.getSender());
                        message.setSender("服务器");
                        ServerConnectClientThread thread = ManageClientThreads.getServerConnectClientThread(message.getGetter());
                        ObjectOutputStream oos = new ObjectOutputStream(thread.socket.getOutputStream());
                        oos.writeObject(message);
                    } else {
                        if (ManageClientThreads.getServerConnectClientThread(message.getGetter()) == null) {
                            // 说明该id的用户不在线，则将该消息加入到 offlineDb
                            QQServer.addOfflineDb(message);

                        } else {
                            ServerConnectClientThread thread = ManageClientThreads.getServerConnectClientThread(message.getGetter());
                            ObjectOutputStream oos = new ObjectOutputStream(thread.socket.getOutputStream());

                            // 转发相同的message对象即可（如果客户不在线，可以保存到数据库，这样就可以实现离线留言）
                            oos.writeObject(message);
                        }
                    }

                } else if (message.getMesType().equals(MessageType.MESSAGE_TO_ALL_MES)) {
                    // 用户请求群发消息
                    HashMap<String, ServerConnectClientThread> hm = ManageClientThreads.getHm();
                    Iterator<String> iterator = hm.keySet().iterator();

                    // 遍历线程集合
                    while (iterator.hasNext()) {
                        String OnlineUserId = iterator.next();

                        // 排除群发消息的那个用户
                        if (!OnlineUserId.equals(message.getSender())) {

                            // hm.get(OnlineUserId) 得到线程
                            ObjectOutputStream oos = new ObjectOutputStream(hm.get(OnlineUserId).socket.getOutputStream());
                            oos.writeObject(message);
                        }
                    }

                } else if (message.getMesType().equals(MessageType.MESSAGE_FILE_MES)) {
                    // 用户请求传输文件

                    if (QQServer.validUsers.get(message.getGetter()) == null) {
                        // 说明该userId没有存在（未注册）
                        message.setContent("该用户没有注册");
                        message.setGetter(message.getSender());
                        message.setSender("服务器");
                        ServerConnectClientThread thread = ManageClientThreads.getServerConnectClientThread(message.getGetter());
                        ObjectOutputStream oos = new ObjectOutputStream(thread.socket.getOutputStream());
                        oos.writeObject(message);
                    } else {
                        if (ManageClientThreads.getServerConnectClientThread(message.getGetter()) == null) {
                            // 说明该id的用户不在线，则将该消息加入到 offlineDb
                            QQServer.addOfflineDb(message);

                        } else {
                            ServerConnectClientThread thread = ManageClientThreads.getServerConnectClientThread(message.getGetter());
                            ObjectOutputStream oos = new ObjectOutputStream(thread.socket.getOutputStream());
                            // 转发
                            oos.writeObject(message);
                        }
                    }

                } else if (message.getMesType().equals(MessageType.MESSAGE_CLIENT_EXIT)) {
                    // 用户请求退出系统
                    System.out.println("用户" + userId + "退出系统");
                    ManageClientThreads.removeServerConnectClientThread(userId);
                    // 关闭连接
                    socket.close();
                    // 退出循环
                    break;
                } else {
                    System.out.println("错误信息");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
