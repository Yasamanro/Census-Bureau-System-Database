package com.company;

/**
 * Created with IntelliJ IDEA.
 * User: Yasaman
 * Date: 1/10/15 AD
 * Time: 2:34 PM
 * To change this template use File | Settings | File Templates.
 */

public class Node <K extends Comparable , I> {

    private K key;
    private I info;
    private Node<K, I> LeftChild,RightChild,Parent;
    private int LCHeight,RCHeight;

    public Node(K key){
        this(key,null);
    }

    public Node(K key, I info){
        this.key = key;
        this.info = info;
        this.LeftChild = null;
        this.RightChild = null;
        this.Parent = null;
        this.LCHeight = 0;
        this.RCHeight = 0;
    }

    public Node(K key, I info, Node LC , Node RC , Node Par ) {
        this.key = key;
        this.info = info;
        this.LeftChild = LC;
        this.RightChild = RC;
        this.Parent = Par;
        this.LCHeight = 0;
        this.RCHeight = 0;
    }

    public void setInfo(I info){
        this.info = info;
    }

    public K getKey() {
        return key;
    }

    public I getInfo() {
        return info;
    }

    public int getLCHeight() {
        return LCHeight;
    }

    public void setLCHeight(int LCHeight) {
        this.LCHeight = LCHeight;
    }

    public int getRCHeight() {
        return RCHeight;
    }

    public void setRCHeight(int RCHeight) {
        this.RCHeight = RCHeight;
    }

    public Node<K, I> getLeftChild() {
        return LeftChild;
    }

    public void setLeftChild(Node<K, I> leftChild) {
        LeftChild = leftChild;
    }

    public Node<K, I> getRightChild() {
        return RightChild;
    }

    public void setRightChild(Node<K, I> rightChild) {
        RightChild = rightChild;
    }

    public Node<K, I> getParent() {
        return Parent;
    }

    public void setParent(Node<K, I> parent) {
        Parent = parent;
    }

    public boolean hasBothChildren() {
        if ( getRightChild() != null && getLeftChild() != null )
            return true;
        return false;
    }

    public boolean hasAtLeasOneChild (){
        if ( getRightChild() != null || getLeftChild() != null )
            return true;
        return false;
    }

    public boolean hasLeftChild () {
        if ( getLeftChild() != null )
            return true;
        return false;
    }

    public boolean hasRightChild (){
        if ( getRightChild() != null )
            return true;
        return false;
    }

    public boolean hasParent (){
        if ( getParent() != null )
            return true;
        return false;
    }

    public boolean isExternal (){
        if ( !hasBothChildren() )             // A Node is External if it has NO Children !
            return true;
        return false;
    }

    public boolean isInternal (){
        if ( hasAtLeasOneChild() )               // A Node is Internal if it has at least one Child
            return true;
        return false;
    }

    public int getHeight (){
        return Math.max(LCHeight,RCHeight) + 1;
    }
}
