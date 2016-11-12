package com.company;

/**
 * Created with IntelliJ IDEA.
 * User: Yasaman
 * Date: 1/10/15 AD
 * Time: 1:02 PM
 * To change this template use File | Settings | File Templates.
 */

public class AVL <K extends Comparable, V> extends BST {

    public AVL(Node node ) {
        root = node;
        size = 1;
    }

    public AVL(){
        root = null;
        size = 0;
    }

    @Override
    public void insert(Node node) {
        super.insert(node);
        fixTree(node);
    }

    @Override
    public boolean delete(Comparable key) {

        Node deletingNode = find(key);

        if (deletingNode != null) {

            Node parentNode = deletingNode.getParent();            // We Want To Call Fix Tree With Parent Node

            if (parentNode != null) {
                if (parentNode.getLeftChild() == deletingNode) {
                    parentNode.setLCHeight(parentNode.getLCHeight()-1);
                } else {
                    parentNode.setRCHeight(parentNode.getRCHeight()-1);
                }
            }

            super.delete(key);

            fixTree(parentNode);

            return true;              // We Successfully Deleted The Node !
        }
        return false;                 // We Don't Have a Node To Delete !
    }

    public void fixTree(Node node) {

        if (node == null)
            return;
        Node parent = node.getParent();
        if (parent == null)
            return;

        do{
            if (node == parent.getLeftChild()) {
                if (parent.getLCHeight() == parent.getRCHeight() + 2) {
                    if (node.getLCHeight() == node.getRCHeight() - 1) {
                        rotateLeft(node);
                    }
                    rotateRight(parent);
                }
            } else {
                if (parent.getLCHeight() == parent.getRCHeight() - 2) {
                    if (node.getLCHeight() == node.getRCHeight() + 1) {
                        rotateRight(node);
                    }
                    rotateLeft(parent);
                }
            }
            node = parent;
            parent = parent.getParent();
        } while ( parent != null );

    }

    public void rotateLeft ( Node<K,V> node ){
        Node<K, V> pivot = node.getLeftChild();

        node.setRightChild(pivot.getLeftChild());
        pivot.getLeftChild().setParent(node);

        pivot.setLeftChild(node);
        node.setParent(pivot);

    }

    public void rotateRight ( Node<K,V> node ){
        Node<K, V> pivot = node.getLeftChild();

        node.setLeftChild(pivot.getRightChild());
        pivot.getRightChild().setParent(node);

        pivot.setRightChild(node);
        node.setParent(pivot);

    }
}
