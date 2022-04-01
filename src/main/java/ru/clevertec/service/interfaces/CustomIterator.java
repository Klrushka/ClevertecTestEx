package ru.clevertec.service.interfaces;

public interface CustomIterator<T> {
    T next();

    boolean hasNext();

    T remove();

    void addBefore();

    void addAfter();
}
