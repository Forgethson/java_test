package com.homework_and_exercise.chapter19.homework02;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;

/**
 * @author Jianda Wang
 * @version 1.0
 * 2022/12/18
 * 2.编程题
 * Homework02.java要求：使用 BufferedReader 读取一个文本文件，为每行加上行号再连同内容一并输出到屏幕上。
 */
public class Homework02 {
    public static void main(String[] args) throws Exception {

        String path = "E:\\mytemp\\test.txt";
        FileReader fileReader = null;
        String data;
        int i = 1;

        BufferedReader br = new BufferedReader(new FileReader(path));
        while ((data = br.readLine()) != null) {
            System.out.print(i + " ");
            System.out.println(data);
            i++;
        }
        br.close();

        System.out.print("\n");
        System.out.println("========若以 GBK1213 编码，使用转换流======");
        String path1 = "E:\\mytemp\\test2.txt";
        FileReader fileReader1 = null;
        String data1;
        int j = 1;

        // 注意，转换流只能是字节形式，即：FileInputStream 和 FileOutputStream
        InputStreamReader isr = new InputStreamReader(new FileInputStream(path1), "gbk");
        BufferedReader br1 = new BufferedReader(isr);
        while ((data = br1.readLine()) != null) {
            System.out.print(j + " ");
            System.out.println(data);
            j++;
        }
        br1.close();

    }
}
