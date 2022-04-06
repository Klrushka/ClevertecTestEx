package ru.clevertec.models;

public class DiscountCard {
    private int id;
    private float discount;

    public DiscountCard(int id, float discount) {
        this.id = id;
        this.discount = discount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }
}
