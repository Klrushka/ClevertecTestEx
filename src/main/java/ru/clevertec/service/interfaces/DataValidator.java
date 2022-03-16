package ru.clevertec.service.interfaces;

public interface DataValidator {
    void addToInvalidData(String string);

    boolean isIdValid(String string);

    boolean isNameValid(String string);

    boolean isPriceValid(String string);

    boolean isQuantityValid(String string);

    boolean isValidProduct(String[] strings);
}
