package com.data_structure_and_algorithm.binary_search_tree;

import com.data_structure_and_algorithm.binarytree.TreeNode;

/**
 * @author Jianda Wang
 * @version 1.0
 * 2022/12/30
 */
public class BinarySearchTree {
    public TreeNode<Integer> root; // 根节点


    /* 查找结点 */
    public TreeNode<Integer> search(int num) {
        TreeNode<Integer> cur = root;
        // 循环查找，越过叶结点后跳出
        while (cur != null) {
            // 目标结点在 root 的右子树中
            if (cur.element < num) cur = cur.right;
                // 目标结点在 root 的左子树中
            else if (cur.element > num) cur = cur.left;
                // 找到目标结点，跳出循环
            else break;
        }
        // 返回目标结点
        return cur;
    }

    /* 在树中插入结点 */
    public void insert(int num) {
        // 若树为空
        if (root == null) {
            root = new TreeNode<>(num);
            return;
        }
        TreeNode<Integer> cur = root, pre = null;
        // 循环查找，越过叶结点后跳出
        while (cur != null) {
            // 找到重复结点，直接返回（二叉搜索树不允许重复值）
            if (cur.element == num) return;
            pre = cur;
            if (cur.element < num) // 插入位置在 root 的右子树中
                cur = cur.right;
            else // 插入位置在 root 的左子树中
                cur = cur.left;
        }
        // 插入结点
        TreeNode<Integer> node = new TreeNode<>(num);
        if (pre.element < num)
            pre.right = node;
        else
            pre.left = node;
    }

    // 创建一颗 二叉搜索树
    public void createTree(String[] s) {
        for (String value : s) {
            insert(Integer.parseInt(value));
        }
    }

    // 找到树的最小的节点返回
    public TreeNode<Integer> findMin(TreeNode<Integer> T) {
        if (T.left != null) {
            return findMin(T.left);
        } else return T;
    }

    // 先序遍历
    public void preOrder(TreeNode<Integer> T) {
        if (T == null)
            return;
        System.out.println(T.element);
        preOrder(T.left);
        preOrder(T.right);
    }

    // 删除节点
    TreeNode<Integer> remove(TreeNode<Integer> T, int num) {
        // 若树为空，直接提前返回
        if (T == null)
            return null;
        TreeNode<Integer> tmp_T;
        if (num < T.element) {
            T.left = remove(T.left, num);
        } else if (num > T.element) {
            T.right = remove(T.right, num);
        } else {  // 找到目标节点
            if (T.right != null && T.left != null) { // 目标节点有两个子节点
                tmp_T = findMin(T.right);
                T.element = tmp_T.element;
                T.right = remove(T.right, T.element);  // 返回的是删除后的根节点！
            } else {  // 目标节点的子节点中至少一个为空
                if (T.right != null) {  // 如果右侧不为空
                    T = T.right;
                } else if (T.left != null) {  // 如果左侧不为空
                    T = T.left;
                } else {   // 如果两侧均为空
                    T = null;
                }
            }
        }
        return T;
    }


}
