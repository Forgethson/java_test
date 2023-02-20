package com.data_structure_and_algorithm.sort_tool;

/**
 * @author Jianda Wang
 * @version 1.0
 * 2022/12/31
 */
public class SortTest {
    public static void main(String[] args) {
        int[] a = {1, 7, 2, 14, 8, 4, 3, 41, 64, 10, 52, 12, 5, 35, 6, 73, 9, 33};
//        SortTool.bubbleSort(a);
//        SortTool.insertionSort(a);
//        SortTool.quickSort(a, 0, a.length - 1);
        SortTool.mergeSort(a, 0, a.length - 1);
        for (int j : a) {
            System.out.println(j);
        }
    }
}
