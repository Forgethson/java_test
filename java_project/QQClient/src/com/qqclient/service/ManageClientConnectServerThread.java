package com.qqclient.service;

import com.sun.security.ntlm.Client;

import java.util.HashMap;

/**
 * @author Jianda Wang
 * @version 1.0
 * 2022/12/20
 * 管理 客户端连接到服务器端的线程 的类（所有用户连接线程的集合）
 * 方法和属性都是静态的，因此可以随时通过 userId得到对应的线程
 * 一般不会使用多个socket，因为一个进程一般也就登陆了一个账号，除非一个进程登陆多个账号
 */
public class ManageClientConnectServerThread {

    // 把多线程放入HashMap集合，key = userID, value = Thread
    private static HashMap<String, ClientConnectServerThread> hm = new HashMap<>();

    // 将线程加入到 hm
    public static void addClientConnectServerThread(String userId, ClientConnectServerThread clientConnectServerThread) {
        hm.put(userId, clientConnectServerThread);
    }

    // 通过userId可以得到对应线程
    public static ClientConnectServerThread getClientConnectServerThread(String userId) {
        return hm.get(userId);
    }

}
