package com.qqserver.service;

import com.qqcommon.Message;
import com.qqcommon.MessageType;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author Jianda Wang
 * @version 1.0
 * 2022/12/20
 */
public class OfflineMessage implements Runnable {

    private String userId;
    private ArrayList<Message> arrayList;

    public OfflineMessage(String userId, ArrayList<Message> arrayList) {
        this.userId = userId;
        this.arrayList = arrayList;
    }

    @Override
    public void run() {
        // arrayList 是表示未读消息的列表
        // 由于已经在用户登陆时加入到线程集合中了，因此直接用私聊消息的方式发送即可
        Message message1 = new Message();
        message1.setGetter(userId);
        message1.setMesType(MessageType.MESSAGE_COMM_MES);
        message1.setSender("服务器");
        message1.setContent("你有未接收的离线消息：");

        try {
            // 先发送给客户端 离线消息 提醒
            ServerConnectClientThread thread = ManageClientThreads.getServerConnectClientThread(message1.getGetter());
            ObjectOutputStream oos = new ObjectOutputStream(thread.getSocket().getOutputStream());
            oos.writeObject(message1);

            for (Message message : arrayList) {
                thread = ManageClientThreads.getServerConnectClientThread(message.getGetter());
                oos = new ObjectOutputStream(thread.getSocket().getOutputStream());
                oos.writeObject(message);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        QQServer.removeOfflineDb(userId);
    }
}
