package com.homework_and_exercise.chapter19.ChuLiLiuSheJiMoShi;

/**
 * @author Jianda Wang
 * @version 1.0
 * 2022/12/18
 * 定义一个父类 Reader_ 的属性，用来接收不同的子类
 * 将 FileReader 和 StringReader 做成包装流：BufferReader
 */
public class BufferReader_ extends Reader_ {

    private Reader_ reader_;

    public BufferReader_(Reader_ reader_) {
        this.reader_ = reader_;
    }

    // 功能扩展：多次读取文件
    public void readFiles(int num) {
        for (int i = 0; i < num; i++) {
            reader_.readFile();
        }
    }

    // 功能扩展：批量处理字符串数据
    public void readStrings (int num) {
        for (int i = 0; i < num; i++) {
            reader_.readString();
        }
    }
}
