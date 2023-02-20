package com.data_structure_and_algorithm.mylist;

import java.util.Arrays;

/**
 * @author Jianda Wang
 * @version 1.0
 * 2022/12/28
 * 列表（动态数组）简易实现例子（结合我自己写的Array_，看看区别与联系)
 */

public class MyList {
    private int[] nums;           // 数组（存储列表元素）
    private int capacity = 10;    // 列表容量
    private int size = 0;         // 列表长度（即当前元素数量）
    private static int extendRatio = 2;  // 每次列表扩容的倍数

    /* 构造函数 */
    public MyList() {
        nums = new int[capacity];
    }

    /* 获取列表长度（即当前元素数量）*/
    public int getSize() {
        return size;
    }

    /* 获取列表容量 */
    public int capacity() {
        return capacity;
    }

    /* 访问元素 */
    public int get(int index) {
        // 索引如果越界则抛出异常，下同
        if (index >= size)
            throw new IndexOutOfBoundsException("索引越界");
        return nums[index];
    }

    /* 修改元素 */
    public void set(int index, int num) {
        if (index >= size)
            throw new IndexOutOfBoundsException("索引越界");
        nums[index] = num;
    }

    /* 尾部添加元素 */
    public void add(int num) {
        // 元素数量超出容量时，触发扩容机制
        if (size == capacity())
            extendCapacity();
        nums[size] = num;
        // 更新元素数量
        size++;
    }

    /* 中间插入元素 */
    public void insert(int index, int num) {
        if (index >= size)
            throw new IndexOutOfBoundsException("索引越界");
        // 元素数量超出容量时，触发扩容机制
        if (size == capacity())
            extendCapacity();
        // 将索引 index 以及之后的元素都向后移动一位
        for (int j = size - 1; j >= index; j--) {
            nums[j + 1] = nums[j];
        }
        nums[index] = num;
        // 更新元素数量
        size++;
    }

    /* 删除元素 */
    public int remove(int index) {
        if (index >= size)
            throw new IndexOutOfBoundsException("索引越界");
        int num = nums[index];
        // 将索引 index 之后的元素都向前移动一位
        for (int j = index; j < size - 1; j++) {
            nums[j] = nums[j + 1];
        }
        // 更新元素数量
        size--;
        // 返回被删除元素
        return num;
    }

    /* 列表扩容 */
    public void extendCapacity() {
        // 新建一个长度为 size 的数组，并将原数组拷贝到新数组
        nums = Arrays.copyOf(nums, capacity() * extendRatio);
        // 更新列表容量
        capacity = nums.length;
    }
}
