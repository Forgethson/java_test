package com.data_structure_and_algorithm.linkedlist;

/**
 * @author Jianda Wang
 * @version 1.0
 * 2022/12/28
 */
public class LinkedList_ {

    // 头结点
    public ListNode_ first;
    // 长度
    public int size;

    public LinkedList_() {
        first = null;
        size = 0;
    }

    // 插入一个元素 num
    public void insert(int num) {
        if (first == null) {
            first = new ListNode_(num);
            size = 1;
            return;
        }
        first = new ListNode_(num, first);
        size++;
    }

    // 删除指定val的节点
    public boolean remove(int val) {
        boolean res = false;
        // 如果为空链表
        if (first == null) {
            System.out.println("Empty linked list!");
            return false;
        }
        // 如果头结点就是要删除的
        if (first.val == val) {
            first = first.next;
            size--;
            return true;
        }
        ListNode_ node = first;
        while (node.next != null) {
            if (node.next.val == val) {
                node.next = node.next.next;
                res = true;
                size--;
                break;
            }
            node = node.next;
        }
        return res;
    }

    // 在链表中查找值为 target 的首个结点，返回其索引，找不到则返回-1
    public int find(int target) {
        int index = 0;
        ListNode_ node = first;
        while (node!=null){
            if (node.val == target)
                return index;
            node = node.next;
            index++;
        }
        return -1;
    }
}

