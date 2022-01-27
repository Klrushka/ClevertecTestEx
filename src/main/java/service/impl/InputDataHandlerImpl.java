package service.impl;

import data.Data;
import models.DiscountCard;
import models.Product;
import service.interfaces.InputDataHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputDataHandlerImpl implements InputDataHandler {

    @Override
    public ArrayList<Product> getProducts(String[] args) {

        ArrayList<Product> products = new ArrayList<>(Collections.emptyList());

        for (String s : args) {
            if (!isCard(s) && !isFile(s)) {
                products.add(getProduct(s));
            }
        }

        return products;
    }

    @Override
    public boolean isCard(String str) {
        Pattern pattern = Pattern.compile("card-[0-9]+");
        Matcher matcher = pattern.matcher(str);

        return matcher.find();
    }

    @Override
    public boolean isFile(String str) {
        Pattern pattern = Pattern.compile(".+.csv");
        Matcher matcher = pattern.matcher(str);

        return matcher.find();
    }

    @Override
    public String[] getFile(String[] args) {

        String file1 = args[args.length - 2];
        String file2 = args[args.length - 1];


        if (isFile(file1) && isFile(file2)) {
            return new String[]{file1, file2};
        }

        return null;

    }

    @Override
    public DiscountCard getCard(String[] args) {

        for (String s : args) {
            if (isCard(s)) return Data.getCard(Integer.parseInt(s.substring(5)));
        }
        return null;
    }

    private Product getProduct(String str) {
        return new Product(str.split("-"));
    }
}
