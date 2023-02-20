package com.data_structure_and_algorithm.linkedlist;

/**
 * @author Jianda Wang
 * @version 1.0
 * 2022/12/28
 */
public class TestLinkedList {
    public static void main(String[] args) {

        LinkedList_ linkedList = new LinkedList_();
        linkedList.insert(2);
        linkedList.insert(5);
        linkedList.insert(2);
        linkedList.insert(3);
        linkedList.insert(4);
        linkedList.insert(1);

//        System.out.println(linkedList.find(3));
//        System.out.println(linkedList.remove(1));
//        System.out.println(linkedList.remove(3));
//        System.out.println(linkedList.remove(6));
        Solution solution = new Solution();
        ListNode_ P = solution.partition(linkedList.first, 3);

    }
}


class Solution {
    ListNode_ pseudoHead = new ListNode_(0);
    ListNode_ cur = null;

    public ListNode_ partition(ListNode_ head, int x) {
        cur = pseudoHead;
//        cur.next = recur(head, x);
//        return pseudoHead.next;
//        return recur(head, x);

//        recur(head, x);
//        ListNode_ P = recur(head, x);
//        System.out.println(recur(head, x));
        cur.next = recur(head, x);
        return cur;
    }

    public ListNode_ recur(ListNode_ head, int x) {
        if (head == null) return null;
        ListNode_ res = head;
        if (head.val < x) {
            cur.next = head;
            cur = cur.next;
            res = head.next;
        }
        head.next = recur(head.next, x);
        System.out.println(head.val);
        return res;
    }

    public void printP(ListNode_ p1){
        while(p1!=null){
            System.out.print(p1.val );
            p1 = p1.next;
        }
    }
}