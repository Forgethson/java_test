package com.data_structure_and_algorithm.arrayqueue;

import java.util.EmptyStackException;

/**
 * @author Jianda Wang
 * @version 1.0
 * 2022/12/28
 */

/* 基于环形数组实现的队列 */
public class ArrayQueue {
    private int[] nums;     // 用于存储队列元素的数组
    private int front = 0;  // 头指针，指向队首
    private int rear = 0;   // 尾指针，指向队尾 + 1

    public ArrayQueue(int capacity) {
        // 初始化数组
        nums = new int[capacity];
    }

    /* 获取队列的容量 */
    public int capacity() {
        return nums.length;
    }

    /* 获取队列的长度 */
    public int size() {
        int capacity = capacity();
        // 由于将数组看作为环形，可能 rear < front ，因此需要取余数
        return (capacity + rear - front) % capacity;
    }

    /* 判断队列是否为空 */
    public boolean isEmpty() {
        return rear - front == 0;
    }

    /* 入队 */
    public void offer(int num) {
        if (size() == capacity()) {
            System.out.println("队列已满");
            return;
        }
        // 尾结点后添加 num
        nums[rear] = num;
        // 尾指针向后移动一位，越过尾部后返回到数组头部
        rear = (rear + 1) % capacity();
    }

    /* 出队 */
    public int poll() {
        int num = peek();
        // 队头指针向后移动一位，若越过尾部则返回到数组头部
        front = (front + 1) % capacity();
        return num;
    }

    /* 访问队首元素 */
    public int peek() {
        if (isEmpty())
            throw new EmptyStackException();
        return nums[front];
    }
}
