package com.jye.dataStructure;

import com.jye.tree.TreeNode;

/**
 * @author Yangen Jiang
 * @created 2020/10/9 16:40
 */
public class NormalTree {

    public static void main(String[] args) {

        BinaryTree binaryTree = new BinaryTree();

        TreeNode root = new TreeNode(0, "二叉树0");
        TreeNode node1 = new TreeNode(1, "二叉树1");
        TreeNode node2 = new TreeNode(2, "二叉树2");
        TreeNode node3 = new TreeNode(3, "二叉树3");
        TreeNode node4 = new TreeNode(4, "二叉树4");

        root.setLeft(node1);
        root.setRight(node2);
        node1.setLeft(node3);
        node2.setRight(node4);
        binaryTree.setRoot(root);

        //测试
        System.out.println("前序遍历");
        binaryTree.preOrder();

        System.out.println("中序遍历");
        binaryTree.infixOrder();

        System.out.println("后序遍历");
        binaryTree.postOrder();

    }
}

//定义二叉树
class BinaryTree{
    private TreeNode root;

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    //前序遍历
    public void preOrder(){
        if (this.root != null){
            this.root.preOrder();
        }else {
            System.out.println("二叉树为空");
        }
    }

    //中序遍历
    public void infixOrder(){
        if (this.root != null){
            this.root.infixOrder();
        }else {
            System.out.println("二叉树为空");
        }
    }

    //后序遍历
    public void postOrder(){
        if (this.root != null){
            this.root.postOrder();
        }else {
            System.out.println("二叉树为空");
        }
    }
}
