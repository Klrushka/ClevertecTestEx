package data;

import java.util.Arrays;
import java.util.Optional;

public enum Products {
    DONEC(0,12.3, "Donec"),
    DIAM(1,1.5, "diam"),
    CURSUS(2,2.3, "cursus"),
    NULL(-1,-1,"-1");

    Products(int id, double price, String name) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    private int id;
    private double price;
    private String name;

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public static Products getProductById(int id){
        Optional<Products> product = Arrays.stream(Products.values()).filter(p -> p.id == id).findFirst();

        return product.orElse(Products.NULL);
    }

}
