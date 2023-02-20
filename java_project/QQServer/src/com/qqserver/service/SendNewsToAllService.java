package com.qqserver.service;

import com.qqcommon.Message;
import com.qqcommon.MessageType;
import com.sun.xml.internal.ws.api.config.management.policy.ManagedServiceAssertion;
import com.utils.Utility;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @author Jianda Wang
 * @version 1.0
 * 2022/12/20
 */
public class SendNewsToAllService implements Runnable {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void run() {
        while (true) {
            System.out.println("请输入服务器要推送的新闻[输入exit表示退出推送服务]");
            String news = Utility.readString(100);
            if (news.equals("exit")){
                break;
            }

            // 构建群发消息
            Message message = new Message();
            message.setMesType(MessageType.MESSAGE_TO_ALL_MES);
            message.setSender("服务器");
            message.setContent(news);
            message.setSendTime(new Date().toString());
            System.out.println("服务器推送消息给所有人：" + news);

            // 遍历所有通信线程，得到socket，并发送
            HashMap<String, ServerConnectClientThread> hm = ManageClientThreads.getHm();
            for (String onlineUserId : hm.keySet()) {
                try {
                    ObjectOutputStream oos = new ObjectOutputStream(hm.get(onlineUserId).getSocket().getOutputStream());
                    oos.writeObject(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
