package com.homework_and_exercise.chapter12.exception_;

/**
 * @author Jianda Wang
 * @version 1.0
 * 2022/12/11
 */
public class exception {
    public static void main(String[] args) {
        int age = 180;
        try {
            tool.judge(age);
        } catch (AgeException e) {
            System.out.println("年龄不对，不过该异常已被捕获！");
            return;
        }
        finally {
            System.out.println("finally");
        }
//        tool.judge(age);
    }
}

class tool {
    public static void judge(int age) {
        if (age < 18 || age > 120) {
            throw new AgeException("年龄需要在 18 ~ 120 之间");
        }
    }
}

class AgeException extends RuntimeException {
    public AgeException(String message) {
        super(message);
    }
}
