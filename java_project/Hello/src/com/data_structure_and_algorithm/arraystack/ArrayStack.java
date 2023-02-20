package com.data_structure_and_algorithm.arraystack;

import java.util.ArrayList;
import java.util.EmptyStackException;

/**
 * @author Jianda Wang
 * @version 1.0
 * 2022/12/28
 */

/* 基于数组实现的栈 */
public class ArrayStack {
    private ArrayList<Integer> stack;

    public ArrayStack() {
        // 初始化列表（动态数组）
        stack = new ArrayList<>();
    }

    /* 获取栈的长度 */
    public int size() {
        return stack.size();
    }

    /* 判断栈是否为空 */
    public boolean isEmpty() {
        return size() == 0;
    }

    /* 入栈 */
    public void push(int num) {
        stack.add(num);
    }

    /* 出栈 */
    public int pop() {
        if (isEmpty())
            throw new EmptyStackException();
        return stack.remove(size() - 1);
    }

    /* 访问栈顶元素 */
    public int peek() {
        if (isEmpty())
            throw new EmptyStackException();
        return stack.get(size() - 1);
    }
}
