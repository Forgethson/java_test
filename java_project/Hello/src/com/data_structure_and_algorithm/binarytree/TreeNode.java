package com.data_structure_and_algorithm.binarytree;

/**
 * @author Jianda Wang
 * @version 1.0
 * 2022/12/30
 */
public class TreeNode<T> {

    public T element;
    public TreeNode<T> left;
    public TreeNode<T> right;

    public TreeNode() {
    }

    public TreeNode(T element) {
        this.element = element;
    }
}
