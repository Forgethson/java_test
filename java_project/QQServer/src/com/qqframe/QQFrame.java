package com.qqframe;

import com.qqserver.service.QQServer;

/**
 * @author Jianda Wang
 * @version 1.0
 * 2022/12/20
 * 该类创建QQServer，启动后台服务
 */
public class QQFrame {
    public static void main(String[] args) {
        new QQServer();
    }
}
