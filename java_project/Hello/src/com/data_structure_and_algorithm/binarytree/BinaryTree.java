package com.data_structure_and_algorithm.binarytree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author Jianda Wang
 * @version 1.0
 * 2022/12/30
 */
public class BinaryTree {

    public TreeNode<String> root; // 根节点
    public int fullSize; // 扩展二叉树的节点数（将叶子结点补全为full二叉树后的节点数）

    public BinaryTree() {
        root = null;
        fullSize = 0;
    }

    // 创建一棵树（尾递归写法）
    public TreeNode<String> createTree(String[] s) {
        if (s[fullSize].equals("#")) {
            fullSize++;
            return null;
        }
        TreeNode<String> T = new TreeNode<>(s[fullSize++]);
        T.left = createTree(s);
        T.right = createTree(s);
        return T;
    }

    // 创建一棵树（利用堆栈先序遍历写法）
    public TreeNode<String> createTree2(String[] s) {
        if (s[0].equals("#")) {
            System.out.println("Empty tree sequence");
            return null;
        }
        Stack<TreeNode<String>> stack = new Stack<>();
        TreeNode<String> T, tmp_T;
        boolean dir = false; // 0向左，1向右

        // 生成根节点
        TreeNode<String> root = new TreeNode<>(s[0]);
        stack.push(root);
        T = root;
        for (int i = 1; i < s.length; i++) {
            if (s[i].equals("#")) {
                if (!stack.isEmpty())
                    T = stack.pop();
                dir = true;
            } else {
                tmp_T = new TreeNode<>(s[i]);
                stack.push(tmp_T);
                if (!dir) {  // 向左
                    T.left = tmp_T;
                    T = T.left;
                } else {  //向右
                    T.right = tmp_T;
                    T = T.right;
                    dir = false;
                }
            }
        }
        return root;
    }

    // 层序遍历（广度优先搜索）（利用队列）
    public void levelOrder() {
        if (root == null)
            return;
        Queue<TreeNode<String>> treeNodes = new LinkedList<>();
        treeNodes.offer(root);
        while (!treeNodes.isEmpty()) {
            TreeNode<String> T = treeNodes.poll();
            System.out.println(T.element);
            if (T.left != null) {
                treeNodes.offer(T.left);
            }
            if (T.right != null) {
                treeNodes.offer(T.right);
            }
        }
    }

    // 先序遍历（深度优先搜索）（利用堆栈或者递归）
    public void preOrder(TreeNode<String> T) {
        if (T == null)
            return;
        System.out.println(T.element);
        preOrder(T.left);
        preOrder(T.right);
    }

    // 先序遍历（无递归实现）
    public void preOrderNoCur(TreeNode<String> A) {
        Stack<TreeNode<String>> stackA = new Stack<>();
        while (A != null || !stackA.isEmpty()) {
            while (A != null) {
                A = stackA.push(A);
                System.out.println(A.element);
                A = A.left;
            }
            if (!stackA.isEmpty()) {
                A = stackA.pop();
                A = A.right;
            }
        }
    }

    // 中序遍历
    public void inOrder(TreeNode<String> T) {
        if (T == null)
            return;
        preOrder(T.left);
        System.out.println(T.element);
        preOrder(T.right);
    }

    // 后序遍历
    public void postOrder(TreeNode<String> T) {
        if (T == null)
            return;
        preOrder(T.left);
        preOrder(T.right);
        System.out.println(T.element);
    }


}
