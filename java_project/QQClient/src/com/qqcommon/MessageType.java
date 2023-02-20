package com.qqcommon;

/**
 * @author Jianda Wang
 * @version 1.0
 * 2022/12/19
 * 表示消息的类型
 * 定义了一些常量，表示不同的消息类型
 */
public interface MessageType {
    String MESSAGE_LOGIN_SUCCEED = "1";  // 表示：登陆成功
    String MESSAGE_LOGIN_FAIL = "2";  // 表示：登陆失败
    String MESSAGE_COMM_MES = "3";  // 表示：普通信息包
    String MESSAGE_GET_ONLINE_FRIEND = "4";  // 表示：要求获取在线用户列表
    String MESSAGE_RET_ONLINE_FRIEND = "5";  // 表示：返回在线用户列表
    String MESSAGE_CLIENT_EXIT = "6";  // 表示：客户端请求退出
    String MESSAGE_TO_ALL_MES = "7";  // 表示：客户端请求群发消息
    String MESSAGE_FILE_MES = "8";  // 表示：客户端请求发送文件
}
