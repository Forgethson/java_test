package com.qqcommon;

import java.io.Serializable;

/**
 * @author Jianda Wang
 * @version 1.0
 * 2022/12/19
 * 客户端和服务器端通信时的消息对象
 * 需要序列化 Serializable 以串行流传输
 */
public class Message implements Serializable {

    // 增强兼容性的语句（可不加）
    private static final long serialVersionUID = 1L;

    private String sender;  // 发送者
    private String getter;  // 接收者
    private String content;  // 发送内容
    private String sendTime;  // 发送时间
    private String mesType;  // 消息类型

    // 文件相关
    private byte[] fileByte;
    private int fileLen = 0;
    private String dest;
    private String src;

    public byte[] getFileByte() {
        return fileByte;
    }

    public void setFileByte(byte[] fileByte) {
        this.fileByte = fileByte;
    }

    public int getFileLen() {
        return fileLen;
    }

    public void setFileLen(int fileLen) {
        this.fileLen = fileLen;
    }

    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getMesType() {
        return mesType;
    }

    public void setMesType(String mesType) {
        this.mesType = mesType;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getGetter() {
        return getter;
    }

    public void setGetter(String getter) {
        this.getter = getter;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }
}
