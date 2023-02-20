package com.data_structure_and_algorithm.binary_search_tree;

/**
 * @author Jianda Wang
 * @version 1.0
 * 2022/12/30
 */
public class BinarySearchTreeTest {
    public static void main(String[] args) {

//        String str = "3 1 4 2";
        String str = "3 4 1 9 6 7 5";
        String[] s = str.split(" ");

        BinarySearchTree binarySearchTree = new BinarySearchTree();
        binarySearchTree.createTree(s);
        binarySearchTree.remove(binarySearchTree.root, 7);

        System.out.println("===先序遍历===");
        binarySearchTree.preOrder(binarySearchTree.root);
    }
}
