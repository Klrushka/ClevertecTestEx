package ru.clevertec.service.interfaces;

import java.util.Collection;

public interface CustomList<T> {
    CustomIterator getIterator();

    void setMaxSize(int n);

    void add(T item);

    void addAll(Collection<T> collection);

    T set(int n, T item);

    T remove(int n);

    void clear();

    int find(T item);

    T get(int n);

    Object[] toArray();

    int size();

    void trim();
}
