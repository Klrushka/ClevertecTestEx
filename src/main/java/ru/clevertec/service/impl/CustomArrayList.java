package ru.clevertec.service.impl;

import ru.clevertec.service.interfaces.CustomIterator;
import ru.clevertec.service.interfaces.ÑustomList;


import java.util.Arrays;
import java.util.Collection;

public class CustomArrayList<T> implements ÑustomList<T> {
    private T[] data;
    private int size;
    private int maxSize = 0;
    private int pointer;


    public CustomArrayList() {
        size = 10;
        pointer = size;
        data = (T[]) new Object[size];
    }

    public CustomArrayList(int size) {
        this.size = size;
        this.pointer = size;
        data = (T[]) new Object[size];
    }

    public CustomArrayList(T[] data) {
        this.data = data;
        size = data.length;
        pointer = size;
    }


    @Override
    public CustomIterator<T> getIterator() {
        return new CustomItr();
    }

    @Override
    public void setMaxSize(int maxSize) {
        if (maxSize < 0) {
            throw new IllegalArgumentException();
        }
        this.maxSize = maxSize;
        size = 5;
    }

    @Override
    public void add(T element) {
        if (pointer == maxSize) return;
        if (pointer == size) {
            data = Arrays.copyOf(data, size += size / 2);
        }
        data[pointer] = element;
        pointer++;
    }

    @Override
    public void addAll(Collection<T> collection) {
        T[] collectionArray = (T[]) collection.toArray();
        if (maxSize != 0) {
            pointer = maxSize;
            data = Arrays.copyOf(collectionArray, pointer);
        } else {
            pointer = collection.size();
            data = Arrays.copyOf(collectionArray, pointer);
        }
    }

    @Override
    public T set(int n, T element) {
        checkIndex(n);
        T oldElement = data[n];
        data[n] = element;
        return oldElement;
    }

    @Override
    public T remove(int n) {
        checkIndex(n);
        T oldElement = data[n];
        for (int i = n; i < pointer -1; i++) {
            data[i] = data[i + 1];
        }
        data[--pointer] = null;
        return oldElement;
    }

    @Override
    public void clear() {
        for (int i = 0; i < pointer; i++) {
            data[i] = null;
        }
    }

    @Override
    public int find(T element) {
        for (int i = 0; i < pointer; i++) {
            if (data[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public T get(int n) {
        checkIndex(n);
        return data[n];
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(data, pointer);
    }

    @Override
    public int size() {
        return pointer;
    }

    @Override
    public void trim() {
        for (int i = 0; i < pointer; i++) {
            if (data[i] == null) {
                remove(i);
                i--;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("[");

        for (int i = 0; i < pointer; i++) {
            if (i == pointer - 1) {
                builder.append(data[i]);
            } else
                builder.append(data[i]).append(", ");
        }

        builder.append("]");

        return builder.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    private void checkIndex(int n) {
        if (n > pointer) {
            throw new IndexOutOfBoundsException();
        }
    }

    class CustomItr implements CustomIterator<T> {

        private int cursor = 0;


        @Override
        public T next() {
            checkIndex(cursor);
            return data[cursor++];
        }

        @Override
        public boolean hasNext() {
            return cursor != pointer;
        }

        @Override
        public T remove() {
            T oldElement = data[cursor];
            CustomArrayList.this.remove(cursor--);
            return oldElement;
        }

        @Override
        public void addBefore(T element) {
            if (pointer + 1 > size) add(null);
            T[] tempArray = Arrays.copyOf(data, ++pointer);
            data[cursor] = element;
            System.arraycopy(tempArray, cursor, data, ++cursor, pointer - cursor);
        }

        @Override
        public void addAfter(T element) {
            if (pointer + 1 > size) add(null);
            T[] tempArray = Arrays.copyOf(data, size);
            data[++cursor] = element;
            System.arraycopy(tempArray, cursor, data, cursor + 1, pointer - cursor);
        }

        @Override
        public void setIteratorToStart() {
            cursor = 0;
        }
    }
}
