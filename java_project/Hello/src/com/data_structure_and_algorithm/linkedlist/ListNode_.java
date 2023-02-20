package com.data_structure_and_algorithm.linkedlist;

/**
 * @author Jianda Wang
 * @version 1.0
 * 2022/12/28
 */

/* 链表结点类 */
public class ListNode_<T> {
    public int val;        // 结点值
    public ListNode_<T> next;  // 指向下一结点的指针（引用）

    public ListNode_() {
        this.val = 0;
        this.next = null;
    }

    public ListNode_(int val) {
        this.val = val;
    }

    public ListNode_(int val, ListNode_<T> next) {
        this.val = val;
        this.next = next;
    }
}