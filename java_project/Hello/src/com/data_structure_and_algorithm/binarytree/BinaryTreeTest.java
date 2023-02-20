package com.data_structure_and_algorithm.binarytree;

/**
 * @author Jianda Wang
 * @version 1.0
 * 2022/12/30
 */
public class BinaryTreeTest {
    public static void main(String[] args) {
//        String str = "A B # D # # C # #";
//        String str = "A B # C # D E # # # #";
        String str = "A B D # # F E # # # C G # H # # I";
        String[] s = str.split(" ");

        BinaryTree binaryTree = new BinaryTree();
        binaryTree.root = binaryTree.createTree2(s);

        System.out.println("===层序遍历===");
        binaryTree.levelOrder();
        System.out.println("===先序遍历===");
        binaryTree.preOrder(binaryTree.root);
    }
}
