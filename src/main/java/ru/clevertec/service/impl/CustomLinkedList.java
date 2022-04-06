package ru.clevertec.service.impl;

import ru.clevertec.service.interfaces.CustomIterator;
import ru.clevertec.service.interfaces.CustomList;

import java.util.Collection;

public class CustomLinkedList<T> implements CustomList<T> {

    private Node<T> firstNode;
    private Node<T> lastNode;
    private int size = 0;
    private int maxSize = 0;

    public CustomLinkedList() {
        firstNode = new Node<>(null, null, lastNode);
        lastNode = new Node<>(null, firstNode, null);
        firstNode.next = lastNode;
    }

    public CustomLinkedList(Collection<T> collection) {
        this();
        T[] collectionItems = (T[]) collection.toArray();
        for (int i = 0; i < collection.size(); i++) {
            add(collectionItems[i]);
        }
    }

    private class Node<T> {
        T item;
        Node<T> prev;
        Node<T> next;

        public Node(T item, Node<T> prev, Node<T> next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    @Override
    public CustomIterator getIterator() {
        return new Itr();
    }

    @Override
    public void setMaxSize(int n) {
        maxSize = n;
    }

    @Override
    public void add(T item) {
        if (maxSize != 0 && size + 1 > maxSize) return;
        Node<T> prev = lastNode;
        prev.item = item;
        lastNode = new Node<>(null, prev, null);
        prev.next = lastNode;
        size++;
    }

    @Override
    public void addAll(Collection<T> collection) {
        int sizeArray;
        if (maxSize != 0) {
            sizeArray = maxSize;
        } else {
            sizeArray = collection.size();
        }
        T[] tempCollection = (T[]) collection.toArray();
        for (int i = 0; i < sizeArray; i++) {
            add(tempCollection[i]);
        }
    }

    @Override
    public T set(int n, T element) {
        checkIndex(n);
        Node<T> node = getNode(n);

        T oldElement = node.item;
        node.item = element;

        return oldElement;
    }

    @Override
    public T remove(int n) {
        checkIndex(n);
        Node<T> node = getNode(n);
        node.prev.next = node.next;
        node.next.prev = node.prev;
        size--;
        return node.item;
    }

    @Override
    public void clear() {
        firstNode = new Node<>(null, null, lastNode);
        lastNode = new Node<>(null, firstNode, null);
        firstNode.next = lastNode;
        size = 0;
    }

    @Override
    public int find(T element) {
        Node<T> result = firstNode.next;
        for (int i = 0; i < size; i++) {
            if (result.item.equals(element)) {
                return i;
            }
            result = result.next;
        }
        return -1;
    }

    @Override
    public T get(int n) {
        Node<T> result = firstNode.next;
        for (int i = 0; i < n; i++) {
            result = result.next;
        }
        return result.item;
    }

    @Override
    public Object[] toArray() {
        Node<T> node = firstNode.next;
        Object[] data = new Object[size];
        for (int i = 0; i < size; i++) {
            data[i] = node.item;
            node = node.next;
        }
        return data;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void trim() {
        Node<T> node = firstNode.next;
        for (int i = 0; i < size; i++) {
            if (node.item == null) {
                remove(i);
                i--;
            }
            node = node.next;
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        Node<T> cursor = firstNode.next;

        builder.append("[");

        for (int i = 0; i < size; i++) {
            if (i == size - 1) {
                builder.append(cursor.item);
            } else {
                builder.append(cursor.item).append(", ");
            }
            cursor = cursor.next;
        }
        builder.append("]");

        return builder.toString();
    }

    private Node<T> getNode(int n) {
        Node<T> node = firstNode.next;

        for (int i = 0; i < n; i++) {
            node = node.next;
        }

        return node;
    }


    private class Itr implements CustomIterator<T> {

        private int cursor = 0;

        @Override
        public T next() {
            return get(cursor++);

        }

        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        @Override
        public T remove() {
            return CustomLinkedList.this.remove(cursor);
        }

        @Override
        public void addBefore(T element) {
            Node<T> node = getNode(cursor);
            Node<T> newNode = new Node<>(element, node.prev, node);
            node.prev.next = newNode;
            node.prev = newNode;
            size++;
            cursor++;
        }

        @Override
        public void addAfter(T element) {
            Node<T> node = getNode(cursor);
            Node<T> newNode = new Node<>(element, node, node.next);
            node.next.prev = newNode;
            node.next = newNode;
            size++;
        }

        @Override
        public void setIteratorToStart() {
            cursor = 0;
        }
    }

    private void checkIndex(int n) {
        if ((maxSize != 0 && (n > maxSize || n > size) || n > size)) {
            throw new IndexOutOfBoundsException();
        }
    }
}
