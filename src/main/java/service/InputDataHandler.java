package service;

import Models.Product;

import java.util.ArrayList;
import java.util.Collections;

public class InputDataHandler {

    public static ArrayList<Product> getProducts (String[] args) {
        ArrayList<Product> products = new ArrayList<>(Collections.emptyList());

        for (String s: args){
            products.add(new Product(
                    Character.getNumericValue(s.charAt(0)),
                    Character.getNumericValue(s.charAt(2))
            ));
        }

        return products;
    }
}
