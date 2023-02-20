package com.homework_and_exercise.chapter19.ChuLiLiuSheJiMoShi;

/**
 * @author Jianda Wang
 * @version 1.0
 * 2022/12/18
 */
public class Test_ {
    public static void main(String[] args) {

        BufferReader_ bufferReader = new BufferReader_(new FileReader_());
        bufferReader.readFiles(10);

        BufferReader_ bufferReader2 = new BufferReader_(new StringReader_());
        bufferReader2.readStrings(10);
    }
}
