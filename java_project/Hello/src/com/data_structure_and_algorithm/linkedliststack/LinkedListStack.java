package com.data_structure_and_algorithm.linkedliststack;

import com.data_structure_and_algorithm.linkedlist.ListNode_;

import java.util.EmptyStackException;

/**
 * @author Jianda Wang
 * @version 1.0
 * 2022/12/28
 */
/* 基于链表实现的栈 */
public class LinkedListStack {
    private ListNode_<Integer> stackPeek;  // 将头结点作为栈顶
    private int stkSize = 0;   // 栈的长度

    public LinkedListStack() {
        stackPeek = null;
    }

    /* 获取栈的长度 */
    public int size() {
        return stkSize;
    }

    /* 判断栈是否为空 */
    public boolean isEmpty() {
        return size() == 0;
    }

    /* 入栈 */
    public void push(int num) {
        /*
        等价写法：
        ListNode node = new ListNode(num);
        node.next = stackPeek;
        stackPeek = node;
        */
        stackPeek = new ListNode_<Integer>(num, stackPeek);
        stkSize++;
    }

    /* 出栈 */
    public int pop() {
        int num = peek();
        stackPeek = stackPeek.next;
        stkSize--;
        return num;
    }

    /* 访问栈顶元素 */
    public int peek() {
        if (size() == 0)
            throw new EmptyStackException();
        return stackPeek.val;
    }
}
