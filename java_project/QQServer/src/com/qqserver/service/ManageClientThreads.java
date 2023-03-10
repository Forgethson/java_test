package com.qqserver.service;

import com.qqcommon.User;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * @author Jianda Wang
 * @version 1.0
 * 2022/12/20
 * 该类用于管理和客户端通信的线程
 */
public class ManageClientThreads {
    private static HashMap<String, ServerConnectClientThread> hm = new HashMap<>();

    public static HashMap<String, ServerConnectClientThread> getHm() {
        return hm;
    }

    // 添加线程对象
    public static void addClientThread(String userId, ServerConnectClientThread serverConnectClientThread) {
        hm.put(userId, serverConnectClientThread);
    }

    // 根据userId获取线程对象
    public static ServerConnectClientThread getServerConnectClientThread(String userId) {
        return hm.get(userId);
    }

    // 根据userId删除线程对象
    public static void removeServerConnectClientThread(String userId) {
        hm.remove(userId);
    }

    // 返回在线用户列表
    public static String getOnlineUser() {
        Set<String> keySet = hm.keySet();
        Iterator<String> iterator = keySet.iterator();
        String onlineUserList = "";
        while (iterator.hasNext()) {
            onlineUserList += iterator.next() + " ";  // 以空格作为分隔符
        }
        return onlineUserList;
    }
}
