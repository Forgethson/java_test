package com.leetcode.to_offer;


/**
 * @author Jianda Wang
 * @version 1.0
 * 2022/12/29
 */
public class main {
    public static void main(String[] args) {
//        int[][] a = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
//        String str = "abaccdeff";
//        int[] a = new int[]{1, 2, 1, -1, -7};
//        int[] a = new int[]{0, 1, 2, 3, 5, 6, 7, 8, 9, 10, 11};
        int[] a = new int[]{3, 4, 5, 1, 2};
        int[] b = new int[]{4, 1};
//        LinkedList linkedList = new LinkedList();
        Solution solution = new Solution();
        solution.lastRemaining(5, 1);
//        solution.isSubStructure()

        String str = "我,是,好,人,";
        for (int i = 0; i < str.length(); i++) {
            System.out.println(str.charAt(i));
        }
        String[] split = str.split(",");


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
    public int lastRemaining(int n, int m) {
        if (m==1) return n-1;
        int[] nums = new int[n];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = i;
        }
        int count = n - 1, x = 0;
        while (count > 0) {
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == -1) continue;
                if (x % m == (m - 1)) {
                    nums[i] = -1;
                    count--;
                }
                x++;
            }
        }
        for (int num : nums) {
            if (num != -1) return num;
        }
        return -1;
    }
}









