package com.qqserver.service;

import com.qqcommon.Message;
import com.qqcommon.MessageType;
import com.qqcommon.User;
import com.sun.xml.internal.ws.api.config.management.policy.ManagedServiceAssertion;
import sun.applet.resources.MsgAppletViewer;

import javax.jws.soap.SOAPBinding;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Jianda Wang
 * @version 1.0
 * 2022/12/20
 * 服务端，在监听 9999-端口，等待客服端连接，并保持通信
 */
public class QQServer {

    private ServerSocket ss = null;

    // 创建一个集合存放多个用户，如果这些用户登陆，就认为是合法的（即已注册用户）
    // HashMap 没有处理线程安全，因此在多线程状况下是不安全的
    // 这里可以用 ConcurrentHashMap，可以处理并发的集合，保证线程安全
    static ConcurrentHashMap<String, User> validUsers = new ConcurrentHashMap<>();
    static ConcurrentHashMap<String, ArrayList<Message>> offlineDb = new ConcurrentHashMap<>();

    //在静态代码块初始化 validUsers
    static {
        // 已注册用户 ↓ ↓ ↓ ↓ ↓
        validUsers.put("100", new User("100", "123456"));
        validUsers.put("200", new User("200", "123456"));
        validUsers.put("300", new User("300", "123456"));
        validUsers.put("至尊宝", new User("至尊宝", "123456"));
        validUsers.put("紫霞仙子", new User("紫霞仙子", "123456"));
        validUsers.put("菩提老祖", new User("菩提老祖", "123456"));
    }

    // 方法：验证用户是否有效
    private boolean checkUser(String userId, String passwd) {
        User user = validUsers.get(userId);
        if (user == null) {  // 说明该userId没有存在（未注册）
            return false;
        }
        if (!user.getPasswd().equals(passwd)) {  // userId正确，但是密码错误
            return false;
        }
        return true;
    }

    public QQServer() {
        // 注意：端口可以写在配置文件里面
        try {

            System.out.println("服务器端在9999端口监听...");
            // 启动推送新闻的线程
            new Thread(new SendNewsToAllService()).start();
            ss = new ServerSocket(9999);

            // 循环监听：当与某个客户端连接后，仍然需要继续监听！
            while (true) {
                Socket socket = ss.accept(); // 如果没有客户端，则阻塞在此

                // 得到socket关联的Message对象输入流
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                // 读取客户端发送的User对象
                User u = (User) ois.readObject();

                //创建一个Message对象，准备回复客户端
                Message message = new Message();
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

                // 应该操作数据库，来判断User对象是否合法
                // 这里暂时用固定的用户名和密码来登陆
                if (checkUser(u.getUserID(), u.getPasswd())) { // 用户合法，验证通过

                    message.setMesType(MessageType.MESSAGE_LOGIN_SUCCEED);
                    oos.writeObject(message);

                    // 创建一个线程，和客户端保持后续的通信，该线程需要持有socket对象
                    ServerConnectClientThread serverConnectClientThread = new ServerConnectClientThread(socket, u.getUserID());
                    // 启动线程
                    serverConnectClientThread.start();
                    // 把线程放入集合管理
                    ManageClientThreads.addClientThread(u.getUserID(), serverConnectClientThread);
                    // 检查有无离线信息
                    checkOfflineMessage(u.getUserID());

                } else {  // 登陆失败
                    System.out.println("用户" + u.getUserID() + "，pwd=" + u.getPasswd() + "验证失败");
                    message.setMesType(MessageType.MESSAGE_LOGIN_FAIL);
                    oos.writeObject(message);
                    socket.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            // 如果服务端退出了while循环，说明服务器已关闭，不再监听端口，因此关闭ServerSocket
            try {
                ss.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void addOfflineDb(Message message) {
        if (offlineDb.get(message.getGetter()) == null) {
            // 此用户的第一条离线消息，new ArrayList
            offlineDb.put(message.getGetter(), new ArrayList<>());
            offlineDb.get(message.getGetter()).add(message);
        } else {
            offlineDb.get(message.getGetter()).add(message);
        }
    }
    public static void removeOfflineDb(String userId) {
        offlineDb.remove(userId);
    }

    public static void checkOfflineMessage(String userId) {
        ArrayList<Message> arrayList = offlineDb.get(userId);
        if (arrayList != null) {
            // 如果该用户在 offlineDb 中有message（有离线信息）
            new Thread(new OfflineMessage(userId, arrayList)).start();
        }
    }
}
