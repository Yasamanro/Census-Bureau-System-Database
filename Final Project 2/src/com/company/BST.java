package com.company;

/**
 * Created with IntelliJ IDEA.
 * User: Yasaman
 * Date: 1/10/15 AD
 * Time: 1:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class BST<K extends Comparable, V> {

    protected Node<K, V> root;
    protected int size;

    public BST(Node<K, V> node) {
        root = node;
        size = 1;
    }

    public BST() {
        root = null;
        size = 0;
    }

    public void insert(Node<K, V> node) {
        if (root == null)
            root = node;
        else
            insertaux(root, node);
    }

    public void insertaux(Node<K, V> node, Node<K, V> nodeToAdd) {

        // if NodeToAdd is less than Node then it Should Go in the Left Subtree
        if (nodeToAdd.getKey().compareTo(node.getKey()) <= 0) {

            if (node.getLeftChild() == null) {
                nodeToAdd.setParent(node);
                node.setLeftChild(nodeToAdd);
                size++;
            } else
                insertaux(node.getLeftChild(), nodeToAdd);
        }

        // if NodeToAdd is Greater than Node then it Should Go in the Right Subtree
        else if (nodeToAdd.getKey().compareTo(node.getKey()) > 0) {

            if (node.getRightChild() == null) {
                nodeToAdd.setParent(node);
                node.setRightChild(nodeToAdd);
                size++;
            } else
                insertaux(node.getRightChild(), nodeToAdd);
        }
    }

    public void InorderTraversal() {
        if (root != null) {
            Node nodeToTraverse = root;
            if (nodeToTraverse.getLeftChild() == null && nodeToTraverse.getRightChild() == null) {
                System.out.println(nodeToTraverse.getKey());
            } else {
                if (nodeToTraverse.getLeftChild() != null) {
                    InorderAux(nodeToTraverse.getLeftChild());
                }
                if (nodeToTraverse.getRightChild() != null) {
                    InorderAux(nodeToTraverse.getRightChild());
                }
            }
        }
    }

    private void InorderAux(Node<K, V> node) {
        if (node == null) return;
        if (node.getLeftChild() != null) {
            InorderAux(node.getLeftChild());
        }

        Info nodeInfo1 = (Info)node.getInfo();
        if ( nodeInfo1.isAlive() ) {
            Info nodeInfo = (Info)node.getInfo();
            System.out.println(nodeInfo.getName());
        }

        if (node.getRightChild() != null) {
            InorderAux(node.getRightChild());
        }
    }

    public void InorderTraversalList() {
        InorderAux(root);
    }

    public void PreorderTraversal() {
        if (root != null) {
            Node nodeToTraverse = root;
            if (nodeToTraverse.getLeftChild() == null && nodeToTraverse.getRightChild() == null) {
                System.out.println(nodeToTraverse.getKey());
            } else {
                PreorderAux(nodeToTraverse);
            }
        }
    }

    public void PreorderAux(Node<K, V> node) {

        System.out.println(node.getKey());

        if (node.getLeftChild() != null)
            PreorderAux(node.getLeftChild());

        if (node.getRightChild() != null)
            PreorderAux(node.getRightChild());
    }

    public void PostorderTraversal() {

        if (root != null) {
            Node nodeToTraverse = root;
            if (nodeToTraverse.getLeftChild() == null && nodeToTraverse.getRightChild() == null) {
                System.out.println(nodeToTraverse.getKey());
            } else {
                if (nodeToTraverse.getLeftChild() != null) {
                    PostorderAux(nodeToTraverse.getLeftChild());
                }
                if (nodeToTraverse.getRightChild() != null) {
                    PostorderAux(nodeToTraverse.getRightChild());
                }
            }
        }
    }

    public void PostorderAux(Node<K, V> node) {

        if (node.getLeftChild() != null)
            PreorderAux(node.getLeftChild());

        if (node.getRightChild() != null)
            PreorderAux(node.getRightChild());

        System.out.println(node.getKey());
    }

    public boolean delete(K key) {

        Node NodeToBeDeleted = find(key);

        if (NodeToBeDeleted != null) { // Then We have Found Something than we can delete !

            // Case 1 : Node has No Children
            if (!NodeToBeDeleted.hasLeftChild() && !NodeToBeDeleted.hasRightChild()) {
                if (NodeToBeDeleted == root) {
                    root = null;
                } else
                    deleteCase1(NodeToBeDeleted);
            }

            // Case 3 : Node has 2 Children
            else if (NodeToBeDeleted.hasLeftChild() && NodeToBeDeleted.hasRightChild()) {
                deleteCase3(NodeToBeDeleted);
            }

            // Case 2 : Node has one Child
            else if (NodeToBeDeleted.hasLeftChild()) { // Case 2 Where Left Child Should Be Deleted
                if (NodeToBeDeleted == root) {
                    root = NodeToBeDeleted.getLeftChild();
                } else
                deleteCase2(NodeToBeDeleted);
            } else if (NodeToBeDeleted.hasRightChild()) { // Case 2 Where Right Child Should Be Deleted
                if (NodeToBeDeleted == root) {
                    root = NodeToBeDeleted.getRightChild();
                } else
                deleteCase2(NodeToBeDeleted);
            }
        }


        return false; // We haven't Found anything to Delete !
    }

    public void deleteCase1(Node NodeToBeDeleted) {
        // Check if The Node To Be Deleted is The Left or Right Child of its Parent
        if (NodeToBeDeleted.getParent().getLeftChild() == NodeToBeDeleted) { // is Left Child
            NodeToBeDeleted.getParent().setLeftChild(null);
        } else if (NodeToBeDeleted.getParent().getRightChild() == NodeToBeDeleted) { // is Right Child
            NodeToBeDeleted.getParent().setRightChild(null);
        }

        size--;
    }

    public void deleteCase2(Node NodeToBeDeleted) {

        // Check if The Node To Be Deleted is The Left or Right Child of its Parent
        if (NodeToBeDeleted.getParent().getLeftChild() == NodeToBeDeleted) { // is Left Child
            if (NodeToBeDeleted.hasLeftChild()) {
                NodeToBeDeleted.getParent().setLeftChild(NodeToBeDeleted.getLeftChild());
            } else if (NodeToBeDeleted.hasRightChild()) {
                NodeToBeDeleted.getParent().setLeftChild(NodeToBeDeleted.getRightChild());
            }
        } else if (NodeToBeDeleted.getParent().getRightChild() == NodeToBeDeleted) { // is Right Child
            if (NodeToBeDeleted.hasLeftChild()) {
                NodeToBeDeleted.getParent().setRightChild(NodeToBeDeleted.getLeftChild());
            } else if (NodeToBeDeleted.hasRightChild()) {
                NodeToBeDeleted.getParent().setRightChild(NodeToBeDeleted.getRightChild());
            }
        }

        size--;

    }

    public void deleteCase3(Node NodeToBeDeleted) {

        Node minNode = LMCFinder(NodeToBeDeleted.getRightChild()); // We are sure that it has right child !!!!

        deleteCase2(minNode);

        minNode.setParent(NodeToBeDeleted.getParent());
        minNode.setLeftChild(NodeToBeDeleted.getLeftChild());
        minNode.setRightChild(NodeToBeDeleted.getRightChild());

        if (NodeToBeDeleted.getParent() == null) { // The Node We're Deleting is Root
            root = minNode;
        } else {

            if (NodeToBeDeleted.getParent().getLeftChild() == NodeToBeDeleted) {
                NodeToBeDeleted.getParent().setLeftChild(minNode);
            } else if (NodeToBeDeleted.getParent().getRightChild() == NodeToBeDeleted) {
                NodeToBeDeleted.getParent().setRightChild(minNode);
            }
        }

        size--;
    }

    public Node find(K key) {
        if (root != null)
            return findNode(root, key);
        return null;
    }

    public Node findNode(Node search, K key) {
        if (search == null)
            return null;
        if (search.getKey().compareTo(key) == 0) // Search key = Node key
            return search;
        else if (search.getKey().compareTo(key) > 0){
            return findNode(search.getLeftChild(), key);
        } else
            return findNode(search.getRightChild(), key);
    }

    public Node<K, V> RMCFinder(Node<K, V> node) { // Finds The Right Most Child of The Given Node

        Node<K, V> ans = node;
        while (ans.hasRightChild()) {
            ans = ans.getRightChild();
        }

        return ans;
    }

    public Node<K, V> LMCFinder(Node<K, V> node) { // Finds The Right Most Child of The Given Node

        Node<K, V> ans = node;
        while (ans.hasLeftChild()) {
            ans = ans.getLeftChild();
        }

        return ans;
    }

    public boolean isRoot ( Node<K,V> node ) {

        if ( node.equals(root) )
            return true;
        return false;
    }

    public void printTree( Node<K,V> node ) {

        if( node != null ) {
            printTree( node.getLeftChild() );
            System.out.println( node.getKey() );
            printTree( node.getRightChild() );
        }
    }

    public BST<String, Info> findByRange (K start,K end){
        BST<String, Info> ret = new BST<String, Info>();
        findByRangeaux(start, end, root, ret);

        return ret;
    }

    private void findByRangeaux (K start,K end, Node node, BST<String, Info> ret){
        if (node != null) {
            if (node.getKey().compareTo(start) >= 0 && node.getKey().compareTo(end) <= 0) {
                Info nodeInfo = (Info)node.getInfo();
                Node<String, Info> newNode = new Node<String, Info>(nodeInfo.getName(), nodeInfo);
                ret.insert(newNode);
            }

            if (start.compareTo(node.getKey()) < 0)    findByRangeaux(start, end, node.getLeftChild(), ret);
            if (end.compareTo(node.getKey()) > 0)      findByRangeaux(start, end, node.getRightChild(), ret);
        }
    }
}
