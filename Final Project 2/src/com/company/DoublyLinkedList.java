package com.company;

import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: Yasaman
 * Date: 1/10/15 AD
 * Time: 3:45 PM
 * To change this template use File | Settings | File Templates.
 */

public class DoublyLinkedList<E> implements Iterable<E> {

    @Override
    public Iterator<E> iterator() {
        Iterator it =  new Iterator() {
            Node<E> current = head;
            @Override
            public boolean hasNext() {
                if (current.getNext() == null) return false;
                if (current.getNext().getElement() == null) return false;
                return true;
            }

            @Override
            public Object next() {
                current = current.next;
                return current.element;
            }

            @Override
            public void remove() {
                Node<E> next = current.next;
                throwUp(current);
                current = next;
            }
        };
        return it;
    }

    private void throwUp(Node<E> node) {
        this.remove(node);
    }

    public static class Node<E> {
        private E element;
        private Node<E> next,previous;

        public Node (E e,Node<E> n,Node<E> p){
            element = e;
            next = n;
            previous = p;
        }

        public E getElement(){
            return element;
        }

        public void setElement(E e){
            element = e;
        }

        public Node<E> getNext(){
            return next;
        }

        public void setNext(Node<E> n){
            next = n;
        }

        public Node<E> getPrevious() {
            return previous;
        }

        public void setPrevious(Node<E> previous) {
            this.previous = previous;
        }
    } // Node Nested Class ( Different From Other Node Class )

    Node<E> head;
    protected Node<E> tail;
    protected long size;

    public DoublyLinkedList(){
        size = 0 ;
        head = new Node<E>(null,null,null);
        tail = new Node<E>(null,head,null);
        head.setNext(tail);
    }

    public Node<E> getFirst () throws IllegalStateException {
        if ( isEmpty() )
            throw new IllegalStateException("List is Empty!");
        return head.getNext();
    }

    public Node<E> getLast () throws IllegalStateException {
        if (isEmpty())
            throw new IllegalStateException("List is Empty!");
        return tail.getPrevious();
    }

    public Node<E> getPrev ( Node<E> node ) throws IllegalArgumentException {
        if ( node == head )
            throw new IllegalArgumentException("Cannot Move Back ! The Node is Header !");
        return node.getPrevious();
    }

    public Node<E> getNext ( Node<E> node ) throws IllegalArgumentException {
        if ( node == tail )
            throw new IllegalArgumentException("Cannot Move Past the Tail of The List !");
        return node.getNext();
    }

    // Error Occurs if node1 is Header
    public void addBefore ( Node<E> node1 , Node<E> node2 ) throws IllegalArgumentException { // Inserts node2 Before node1
        Node<E> myNode = getPrev(node1);
        node2.setPrevious(myNode);
        myNode.setNext(node2);
        node2.setNext(node1);
        node1.setPrevious(node2);
        size++;
    }

    // Error Occurs if node1 is Tail
    public void addAfter ( Node<E> node1 , Node<E> node2 ) throws IllegalArgumentException { // Inserts node2 After node1
        node2.setPrevious(node1);
        node2.setNext(node1.getNext());
        node2.next.setPrevious(node2);
        node1.setNext(node2);

        size++;
    }

    public void addFirst( Node<E> node ){
        addAfter( head,node );
    }

    public void addLast( Node<E> node ){
        addBefore( tail , node );
    }

    public void remove( Node<E> node ){

        Node<E> node1 = node.getPrevious();
        Node<E> node2 = node.getNext();

        if (node1 != null)
            node1.setNext(node2);
        if (node2 != null)
            node2.setPrevious(node1);

        size--;
    }

    public boolean hasNext ( Node<E> node ) {
        return ( node != tail );
    }

    public boolean hasPrevious ( Node<E> node ) {
        return ( node != head );
    }

    public String toString () {
        String ans = "[";
        Node<E> currentNode = head.getNext();
        while ( currentNode != tail ) {
            ans += currentNode.getElement();
            currentNode = currentNode.getNext();
            if ( currentNode != tail )
                ans += " , ";
        }
        ans += " ]";
        return ans;
    }

    public long size (){
        return size;
    }

    public boolean isEmpty(){
        return ( size == 0 );
    }

}
