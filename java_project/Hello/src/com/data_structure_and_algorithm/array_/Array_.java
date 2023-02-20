package com.data_structure_and_algorithm.array_;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Jianda Wang
 * @version 1.0
 * 2022/12/28
 * 测试数据结构 数组
 * 构造函数解释：
 * 1 无参
 * 2 输入指定长度length
 * 3 输入指定长度length以及一个当做初始值的数组
 * （当该数组长度小于length时，只初始化数组长度的data，当该数组长度大于length时，只初始化length长度的data）
 */
public class Array_ {

    private int[] data;
    private int length;  // 最大长度
    private int size;  // 当前长度

    public Array_() {

    }

    public Array_(int[] data, int length) {
        this.length = length;
        this.size = Math.min(data.length, length);
        this.data = new int[length];
        if (length <= data.length) {
            System.arraycopy(data, 0, this.data, 0, length);
        } else {
            System.arraycopy(data, 0, this.data, 0, size);
        }
    }

    public Array_(int length) {
        this.length = length;
        this.size = 0;
        this.data = new int[length];
    }

    public int[] getData() {
        return data;
    }

    public void setData(int[] data) {
        this.data = data;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    // 扩展数组长度，增大 enlarge 的尺度
    public void arrayExtend(int enlarge) {
        int[] res = new int[length + enlarge];
        System.arraycopy(data, 0, res, 0, size);
        data = res;
        length += enlarge;
    }

    // 返回一个随机的数组元素
    public int randomAccess() {
        int randomIdx = ThreadLocalRandom.current().nextInt(0, size);
        return data[randomIdx];
    }

    // 在数组的索引 index 处插入元素 num
    public void insert(int num, int index) {
        if (index > size) {
            System.out.println("索引越界");
            return;
        }
        if (size + 1 > length) {  // 如果空间不够，先1.5倍扩容
            arrayExtend(length / 2);
        }
        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }
        data[index] = num;
        size++;
    }

    // 删除索引 index 处元素
    public void remove(int index) {
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        data[--size] = 0;
    }

    // 查找值为 num 的元素，若找到，则返回第一个找到的索引；若找不到，返回-1
    public int find(int num) {
        int res = -1;
        for (int i = 0; i < size; i++) {
            if (num == data[i]) {
                res = i;
            }
        }
        return res;
    }


}
