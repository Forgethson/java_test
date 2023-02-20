package com.data_structure_and_algorithm.arrayqueue;

/**
 * @author Jianda Wang
 * @version 1.0
 * 2022/12/28
 */
public class ArrayQueueTest {
    public static void main(String[] args) {
        ArrayQueue arrayQueue = new ArrayQueue(10);
        arrayQueue.offer(1);
        arrayQueue.offer(2);
        arrayQueue.offer(3);
        arrayQueue.offer(4);
        arrayQueue.offer(5);
    }
}
