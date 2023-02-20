package com.data_structure_and_algorithm.deque;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author Jianda Wang
 * @version 1.0
 * 2022/12/29
 */
public class TestDeque {
    public static void main(String[] args) {
        /* 初始化双向队列 */
        Deque<Integer> deque = new LinkedList<>();

        /* 元素入队 */
        deque.offerLast(2);   // 添加至队尾
        deque.offerLast(5);
        deque.offerLast(4);
        deque.offerFirst(3);  // 添加至队首
        deque.offerFirst(1);

        /* 访问元素 */
        int peekFirst = deque.peekFirst();  // 队首元素
        int peekLast = deque.peekLast();    // 队尾元素

        /* 元素出队 */
        int pollFirst = deque.pollFirst();  // 队首元素出队
        int pollLast = deque.pollLast();    // 队尾元素出队

        /* 获取双向队列的长度 */
        int size = deque.size();

        /* 判断双向队列是否为空 */
        boolean isEmpty = deque.isEmpty();

    }
}
