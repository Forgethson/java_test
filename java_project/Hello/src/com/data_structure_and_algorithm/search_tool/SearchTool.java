package com.data_structure_and_algorithm.search_tool;

import com.data_structure_and_algorithm.linkedlist.ListNode_;

/**
 * @author Jianda Wang
 * @version 1.0
 * 2022/12/31
 * 一些查找算法的工具类
 */
public class SearchTool {

    // 线性查找（数组）
    public static int linearSearch(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target)
                return i;
        }
        return -1;
    }

    // 线性查找（链表）
    public static ListNode_<Integer> linearSearch(ListNode_<Integer> head, int target) {
        while (head != null) {
            if (head.val == target) {
                return head;
            }
            head = head.next;
        }
        return null;
    }

    // 二分查找（双闭区间）
    public static int binarySearch(int[] nums, int target) {
        // 初始化双闭区间 [0, n-1] ，即 i, j 分别指向数组首元素、尾元素
        int i = 0, j = nums.length - 1;
        // 循环，当搜索区间为空时跳出（当 i > j 时为空）
        while (i <= j) {
            //int m = (i + j) / 2;       // 计算中点索引 m
            int m = i + (j - i) / 2;     // 防止大数越界
            if (nums[m] < target)      // 此情况说明 target 在区间 [m+1, j] 中
                i = m + 1;
            else if (nums[m] > target) // 此情况说明 target 在区间 [i, m-1] 中
                j = m - 1;
            else                       // 找到目标元素，返回其索引
                return m;
        }
        // 未找到目标元素，返回 -1
        return -1;
    }

}
