package com.data_structure_and_algorithm.arrayhashmap;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jianda Wang
 * @version 1.0
 * 2022/12/30
 */

/* 基于数组简易实现的哈希表 */
public class ArrayHashMap {
    private List<Entry> bucket;

    public ArrayHashMap() {
        // 初始化一个长度为 100 的桶（数组）
        bucket = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            bucket.add(null);
        }
    }

    /* 哈希函数 */
    private int hashFunc(int key) {
        int index = key % 100;
        return index;
    }

    /* 查询操作 */
    public String get(int key) {
        int index = hashFunc(key);
        Entry pair = bucket.get(index);
        if (pair == null) return null;
        return pair.val;
    }

    /* 添加操作 */
    public void put(int key, String val) {
        Entry pair = new Entry(key, val);
        int index = hashFunc(key);
        bucket.set(index, pair);
    }

    /* 删除操作 */
    public void remove(int key) {
        int index = hashFunc(key);
        // 置为 null，代表删除
        bucket.set(index, null);
    }
}

/* 键值对 int->String */
class Entry {
    public int key;     // 键
    public String val;  // 值

    public Entry(int key, String val) {
        this.key = key;
        this.val = val;
    }
}