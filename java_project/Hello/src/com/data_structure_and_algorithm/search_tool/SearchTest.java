package com.data_structure_and_algorithm.search_tool;

import com.data_structure_and_algorithm.linkedlist.LinkedList_;

/**
 * @author Jianda Wang
 * @version 1.0
 * 2022/12/31
 * 测试写的一些查找算法
 */
public class SearchTest {
    public static void main(String[] args) {
        int[] a = new int[20];
        LinkedList_ linkedList = new LinkedList_();
        for (int i = 0; i < a.length; i++) {
            a[i] = i;
            linkedList.insert(i);
        }
        System.out.println(SearchTool.binarySearch(a, 15));
        System.out.println(SearchTool.linearSearch(a, 15));
        System.out.println(SearchTool.linearSearch(linkedList.first, 15));
    }
}
