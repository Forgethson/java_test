package com.homework_and_exercise.chapter19.homework01;

import java.io.*;

/**
 * @author Jianda Wang
 * @version 1.0
 * 2022/12/18
 * 程题Homework01.java 5min
 * 1）1在判断e盘下是否有文件夹mytemp，如果没有就创建mytemp
 * 2）在 e:\\mytemp 目录下，创建文件 hello.txt
 * 3）如果hello.txt已经存在，提示该文件已经存在，就不要再重复创建了
 * 4）并且在hello.txt文件中，写入hello，world~
 */
public class Homework01 {
    public static void main(String[] args) {

        String dir = "E:\\mytemp";
        File file = new File(dir);

        if (file.exists()) {
            System.out.println("已经存在");
        } else {
            // 创建目录，多个目录用 mkdirs()
            if (file.mkdir()) {
                System.out.println("创建成功");
            } else {
                System.out.println("创建失败");
            }
        }

        File file1 = new File(dir, "hello.txt");
//        File file1 = new File(file, "hello.txt");  // 也可以

        // 创建文件
        if (file1.exists()) {
            System.out.println("该文件已经存在");
        } else {
            try {
                file1.createNewFile();
                System.out.println("创建成功");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String data = "hello, world~";
        String Path = file1.getAbsolutePath();
//        String Path = dir +"\\hello.txt";  // 也可以
        File file2 = new File(Path);

        // 以字节流的形式写入
        FileOutputStream fis = null;

        try {
            fis = new FileOutputStream(file2);

            // 将字符串转变为字符数组
            fis.write(data.getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {

                // 需要判断是否为空指针！
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
