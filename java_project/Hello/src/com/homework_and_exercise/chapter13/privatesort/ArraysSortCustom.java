package com.homework_and_exercise.chapter13.privatesort;

import java.util.Arrays;

/**
 * @author Jianda Wang
 * @version 1.0
 * 2022/12/12
 */
public class ArraysSortCustom {
    public static void bubbleSortCustom(int[] arr, ComparatorRules c) {
        int temp = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (c.compare(arr[j], arr[j + 1])) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, -1, 8, 0, 20};

//        class ComparatorRules1 implements ComparatorRules {
//            @Override
//            public boolean compare(int a, int b) {
//                return a > b;
//            }
//        }
//        bubbleSortCustom(arr, new ComparatorRules1());
        bubbleSortCustom(arr, new ComparatorRules() {
            @Override
            public boolean compare(int a, int b) {
                return a > b;
            }
        });
//        bubbleSort(arr);
        System.out.println("==排序后的情况==");
        System.out.println(Arrays.toString(arr));
    }

}

interface ComparatorRules {
    boolean compare(int a, int b);
}

