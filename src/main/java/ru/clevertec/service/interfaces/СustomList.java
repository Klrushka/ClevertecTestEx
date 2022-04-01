package ru.clevertec.service.interfaces;

import java.util.Collection;

public interface ÑustomList<T> {
    CustomIterator getIterator();

    void setMaxSize(int n);

    void add(T element);

    void addAll(Collection<T> collection);

    T set(int n, T element);

    T remove(int n);

    void clear();

    int find(T element);

    T get(int n);

    Object[] toArray();

    int size();

    void trim();
}
