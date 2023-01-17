package edu.ust.cisc;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

public class CiscDoublyLinkedList<E> implements CiscList<E>{

    private Node<E> head;
    private int size;


    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        Node testNode = head;
        for(int i = 0; i < size; i++){
            if(Objects.equals(o, testNode.data)){
                return true;
            }
            testNode = testNode.next;
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new CiscDoublyLinkedListIterator();
    }

    @Override
    public Object[] toArray() {
       Object[] nodearray = new Object[size];
       for(int i = 0; i < size; i++){
           nodearray[i] = head.data;
           head = head.next;
       }
       return nodearray;
    }

    @Override
    public boolean add(E e) {
        if(size == 0){
            Node node = new Node(e, null, null);
            node.next = node;
            node.prev = node;
            head = node;
        }
        else {
            Node node = new Node(e, head, head.prev);
            head.prev.next = node;
            head.prev = node;
        }
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        Node testnode = head;
        for(int i = 0; i < size; i++){
            if(Objects.equals(o, testnode.data)){
                remove(testnode.data);
                return true;
            }
        }
        return false;

    }

    @Override
    public void clear() {
        head = null;
        size = 0;
    }

    @Override
    public E get(int index) {
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }
        Node<E> testnode = head;
        for(int i = 0; i < index; i++){
            testnode = testnode.next;
        }
        return testnode.data;
    }

    @Override
    public E set(int index, E element) {
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }
        Node<E> testnode = head;

        if(index == 0){
            E value = head.data;
            Node node = new Node(element, head.next, head.prev);
            head.next.prev = node;
            head.prev.next = node;
            head = node;
            return value;
        } else{
            for(int i = 0; i < index; i++){
                testnode = testnode.next;
            }
            E value = testnode.data;
            Node node = new Node(element, testnode.next, testnode.prev);
            testnode.next.prev = node;
            testnode.prev.next = node;
            return value;
        }
    }

    @Override
    public void add(int index, E element) {
        if(index == 0){
            if(size == 0) {
                Node newHead = new Node(element, null, null);
                newHead.next = newHead;
                newHead.prev = newHead;
                head = newHead;
                size = size +1;
            } else{
                Node newHead = new Node(element, head, head.prev);
                head.prev.next = newHead;
                head.prev = newHead;
                head = newHead;
                size = size +1;
            }
        }
        else if(index < 0 || index > size){
            throw new IndexOutOfBoundsException();
        } else{
            Node node1 = head;
            for(int i = 0; i < index; i++){
                node1 = node1.next;
            }
            Node oldNode = node1;
            Node newNode = new Node(element, oldNode, oldNode.prev);
            oldNode.prev.next = newNode;
            oldNode.prev = newNode;
            size++;
        }
    }

    @Override
    public E remove(int index) {
        if(index == 0){
            if(size == 1){
                E element = head.data;
                head = null;
                size = 0;
                return element;
            }else{
                E element = head.data;
                head.prev.next = head.next;
                head.next.prev = head.prev;
                head = head.next;
                size = size -1;
                return element;
            }
        }
        else if(index < 0 || index >= size){
           throw new IndexOutOfBoundsException();
       }
        else {
            Node<E> testnode = head;
            for (int i = 0; i < index; i++) {
                testnode = testnode.next;
            }
            E removedelement = testnode.data;
            testnode.prev.next = testnode.next;
            testnode.next.prev = testnode.prev;
            size = size -1;
            return removedelement;
        }
    }

    @Override
    public int indexOf(Object o){
        Node testnode = head;
        for(int i = 0; i < size; i++){
            if(Objects.equals(o, testnode.data)){
                return i;
            }
            testnode = testnode.next;
       }
       return -1;
    }
    private static class Node<E>{
        private E data;
        private Node<E> next;
        private Node<E> prev;

        private Node(E data, Node<E> next, Node<E> prev){
            this.data = data;
            this.next = next;
            this.prev = prev;
        }
    }
    private class CiscDoublyLinkedListIterator implements Iterator<E>{

        private Node<E> nextNode;
        private int nextindex;

        public CiscDoublyLinkedListIterator(){
            nextNode = head;
            nextindex = 0;
        }

        @Override
        public boolean hasNext() {
            return nextindex < size;
        }

        @Override
        public E next() {
            E value = nextNode.data;
            nextNode = nextNode.next;
            nextindex++;
            return value;
        }
    }
}
//get method can use other ciscdoublylinked list methods
// can call helper methods that we create, not other specified methods
