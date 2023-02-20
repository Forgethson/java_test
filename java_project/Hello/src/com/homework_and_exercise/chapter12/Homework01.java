package com.homework_and_exercise.chapter12;

/**
 * @author Jianda Wang
 * @version 1.0
 * 2022/12/12
 */
public class Homework01 {
    public static void main(String[] args) {
        System.out.println("请输入两个整数n1 n2，以计算n1 / n2");
        try {
            if (args.length != 2) {
                throw new ArrayIndexOutOfBoundsException("参数个数不对");
            }

            int n1 = Integer.parseInt(args[0]);
            int n2 = Integer.parseInt(args[1]);
            double res = tool.cal(n1, n2);
            System.out.println("计算结果是：" + res);

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("输入的类型不对");
        } catch (ArithmeticException e) {
            System.out.println("除数不能为零");
        }
    }
}

class tool {
    public static double cal(int n1, int n2) {
        return n1 / n2;
    }
}
