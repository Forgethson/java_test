package com.leetcode.to_offer;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Jianda Wang
 * @version 1.0
 * 2022/12/29
 */
public class main {
    public static void main(String[] args) {
        int length = 16;
        int hashcode = 102122314;
        int ans1 = hashcode % length;
        int ans2 = hashcode & (length - 1);


        System.out.println("a");
        main main = new main();
        main.f();
        main.f(1);

        ReentrantLock reentrantLock = new ReentrantLock();

    }

    void f() {
        System.out.println("void f()");
    }


    int f(int x) {
        System.out.println("int f(int x)");
        return -1;
    }

}

//class LinkedList {
//    public ListNode first;
//    public int size;
//
//    public LinkedList() {
//        first = null;
//        size = 0;
//    }
//
//    public void insert(int num) {
//        if (first == null) {
//            first = new ListNode(num);
//            size = 1;
//            return;
//        }
//        first = new ListNode(num, first);
//        size++;
//    }
//}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int x) {
        val = x;
        next = null;
    }

    ListNode(int x, ListNode next) {
        val = x;
        this.next = next;
    }

}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}


/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) return new ArrayList<>();
        ArrayList<Integer> res = new ArrayList<>();
        LinkedList<TreeNode> queqe = new LinkedList<>();
        queqe.offer(root);
        while (!queqe.isEmpty()) {
            int layerSize = queqe.size();
            int layerMax = Integer.MIN_VALUE;
            for (int i = 0; i < layerSize; i++) {
                TreeNode cur = queqe.poll();
                layerMax = Math.max(layerMax, cur.val);
                if (cur.left != null)
                    queqe.offer(cur.left);
                if (cur.right != null)
                    queqe.offer(cur.right);
                if (i == layerSize - 1) res.add(cur.val);
            }
        }
        return res;
    }
}









