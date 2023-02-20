package com.data_structure_and_algorithm.array_;

/**
 * @author Jianda Wang
 * @version 1.0
 * 2022/12/28
 */
public class ArrayTest {
    public static void main(String[] args) {

        int[] a = new int[5];
        for (int i = 0; i < a.length; i++) {
            a[i] = i;
            System.out.print(a[i] + " ");
        }
        System.out.println();
        Array_ array = new Array_(a, 10);
        Array_ array1 = new Array_(a, 3);

        array1.arrayExtend(10);
        System.out.println("返回一个数组array的随机内容：" + array.randomAccess());

        array.insert(10, 2);
        array.insert(11, 2);
        array.insert(12, 2);
        array.insert(13, 2);
        array.insert(14, 2);
        array.insert(15, 2);
        array.remove(2);

        array.insert(15, 1414);

        System.out.println(array.find(14));
        System.out.println(array.find(15));

    }
}
